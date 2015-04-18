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
		
		//获取servletPath
		String servletPath = request.getServletPath();
		//提取方法名
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//利用反射找到对应的方法
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
	 * 添加联系人
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
			request.setAttribute("msg", "你没有输入任何信息，请重新输入：");
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		//检查是否重学号
		if(customerDAO.getCountWithName(id) > 0){
			request.setAttribute("msg", "学号" + id+ "已经被占用！请重新输入");
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		Customer customer = new Customer(name, address, phone);
		customer.setId(Integer.parseInt(id));
		customerDAO.save(customer);
		request.setAttribute("msg", "添加成功");
		request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
	}
	/**
	 * 删除联系人
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
	 * 查询联系人
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
	 * 更新
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
				request.setAttribute("msg", "对不起---" + name + "用户已经被占用!");
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
	 * 编辑联系人
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
