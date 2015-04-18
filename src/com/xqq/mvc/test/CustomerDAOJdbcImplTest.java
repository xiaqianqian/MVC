package com.xqq.mvc.test;

import java.util.List;

import org.junit.Test;

import com.xqq.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.xqq.mvc.domain.CriteriaCustomer;
import com.xqq.mvc.domain.Customer;
import com.xqq.mvcapp.dao.CustomerDAO;

public class CustomerDAOJdbcImplTest {

	private CustomerDAO customerDAO = 
			new CustomerDAOJdbcImpl();
	@Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		System.out.println(customers);
	}

	@Test
	public void testgetForListWithCriterCustomer(){
		CriteriaCustomer cc = new CriteriaCustomer(null, null, null, null);
		List<Customer> customers = customerDAO.getForListWithCriterCustomer(cc);
		System.out.println(customers);
	}
	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setAddress("BeiJing");
		customer.setName("Mike");
		customer.setPhone("15364365");
		
		customerDAO.save(customer);
	}

	@Test
	public void testDelete() {
		customerDAO.delete("1");
	}

	@Test
	public void testGetInteger() {
		Customer customer = customerDAO.get("1");
		System.out.println(customer);
	}

	@Test
	public void testGetCountWithName() {
		Long count = customerDAO.getCountWithName("xqq");
		System.out.println(count);
	}

}
