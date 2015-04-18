package com.xqq.mvc.dao.impl;

import java.util.List;

import com.xqq.mvc.domain.CriteriaCustomer;
import com.xqq.mvc.domain.Customer;
import com.xqq.mvcapp.dao.CustomerDAO;

public class CustmerDAOXmlImpl implements CustomerDAO {

	@Override
	public List<Customer> getAll() {
		System.out.println("getAll");
		return null;
	}

	@Override
	public void save(Customer customer) {
		System.out.println("save");
	}

	@Override
	public void delete(String id) {
		System.out.println("delete");
	}

	@Override
	public Customer get(String id) {
		System.out.println("get");
		return null;
	}

	@Override
	public long getCountWithName(String name) {
		System.out.println("getCountWithName");
		return 0;
	}

	@Override
	public List<Customer> getForListWithCriterCustomer(CriteriaCustomer cc) {
		System.out.println("getForListWithCriterCustomer");
		return null;
	}

	@Override
	public void update(Customer customer) {
		System.out.println("update");
	}

}
