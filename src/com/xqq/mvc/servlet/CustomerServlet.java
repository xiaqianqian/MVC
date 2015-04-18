package com.xqq.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqq.mvc.dao.factory.CustomerDaoFactory;
import com.xqq.mvc.domain.CriteriaCustomer;
import com.xqq.mvc.domain.Customer;
import com.xqq.mvcapp.dao.CustomerDAO;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CustomerDAO customerDAO = CustomerDaoFactory.getInstance().getCustomerDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡservletPath
		String servletPath = request.getServletPath();
		//��ȡ������
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//���÷����ҵ���Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	/**
	 * �����ϵ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		if(id == "" && name == "" && address == "" && phone == ""){
			request.setAttribute("msg", "��û�������κ���Ϣ�����������룺");
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		//����Ƿ���ѧ��
		if(customerDAO.getCountWithName(id) > 0){
			request.setAttribute("msg", "ѧ��" + id+ "�Ѿ���ռ�ã�����������");
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		Customer customer = new Customer(name, address, phone);
		customer.setId(Integer.parseInt(id));
		customerDAO.save(customer);
		request.setAttribute("msg", "��ӳɹ�");
		request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
	}
	/**
	 * ɾ����ϵ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			String id = request.getParameter("id");
			customerDAO.delete(id);
			response.sendRedirect("query.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ѯ��ϵ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		CriteriaCustomer cc = new CriteriaCustomer(id, name, address, phone);
		List<Customer> customers = customerDAO.getForListWithCriterCustomer(cc);
		
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	/**
	 * ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String oldName = request.getParameter("oldName");
		String id = request.getParameter("id");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		if(!name.equalsIgnoreCase(oldName)){
			if(customerDAO.getCountWithName(name) > 0){
				request.setAttribute("msg", "�Բ���---" + name + "�û��Ѿ���ռ��!");
				System.out.println();
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}
		}
		Customer customer = new Customer(name, address, phone);
		customer.setId(Integer.parseInt(id));
		customerDAO.update(customer);
		response.sendRedirect("query.do");
	}
	/**
	 * �༭��ϵ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String forwardPath = "/error.jsp";
		Customer customer = customerDAO.get(id);
		if(customer != null){
			forwardPath = "/updatecustomer.jsp";
			request.setAttribute("customer", customer);
		}
		request.getRequestDispatcher(forwardPath).forward(request, response);
		System.out.println(customer);
	}
}
