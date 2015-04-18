package com.xqq.mvcapp.dao;

import java.util.List;

import com.xqq.mvc.domain.CriteriaCustomer;
import com.xqq.mvc.domain.Customer;

public interface CustomerDAO {
	public List<Customer> getAll();
	
	public void save(Customer customer);
	
	public void delete(String id);
	
	public Customer get(String id);
	
	public long getCountWithName(String id);
	
	public List<Customer> getForListWithCriterCustomer(CriteriaCustomer cc);
	
	public void update(Customer customer);
}
