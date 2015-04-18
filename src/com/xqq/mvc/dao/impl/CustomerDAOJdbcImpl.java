package com.xqq.mvc.dao.impl;

import java.util.List;

import com.xqq.mvc.domain.CriteriaCustomer;
import com.xqq.mvc.domain.Customer;
import com.xqq.mvcapp.dao.CustomerDAO;
import com.xqq.mvcapp.dao.DAO;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
	public List<Customer> getAll() {
		String sql = "SELECT id, name, address, phone FROM immoc.customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT INTO immoc.customers(id, name," +
				" address, phone) VALUES(?, ?, ?, ?)";
		update(sql, customer.getId(), customer.getName(),
				customer.getAddress(), customer.getPhone());
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM immoc.customers WHERE id = ?";
		update(sql, id);
	}

	@Override
	public Customer get(String id) {
		String sql = "SELECT id, name, address," +
				" phone FROM immoc.customers WHERE id = ?";
		return get(sql, id);
	}

	@Override
	public long getCountWithName(String id) {
		String sql = "SELECT count(name) FROM immoc.customers WHERE id = ?";
	
		return getForValue(sql, id);
	}

	@Override
	public List<Customer> getForListWithCriterCustomer(CriteriaCustomer cc) {
		String sql = "SELECT id, name, address, phone FROM immoc.customers WHERE" +
				" name LIKE ? AND address LIKE ? AND phone LIKE ? AND id LIKE ?";
		return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone(), cc.getId());
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE immoc.customers SET name = ?, address = ?," +
				" phone = ? WHERE id = ?";
		update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
	}
	
}
