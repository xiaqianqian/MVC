package com.xqq.mvc.dao.factory;

import java.util.HashMap;
import java.util.Map;

import com.xqq.mvc.dao.impl.CustmerDAOXmlImpl;
import com.xqq.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.xqq.mvcapp.dao.CustomerDAO;

public class CustomerDaoFactory {
	
	private Map<String, CustomerDAO> daos = new HashMap<String, CustomerDAO>();
	
	private CustomerDaoFactory(){
		daos.put("jdbc", new CustomerDAOJdbcImpl());
		daos.put("xml", new CustmerDAOXmlImpl());
	}
	
	private static CustomerDaoFactory instance = new CustomerDaoFactory();
	
	public static CustomerDaoFactory getInstance(){
		return instance;
	}
	private static String type = null;
	
	public void setType(String type){
		this.type = type;
	}
	public CustomerDAO getCustomerDao(){
		return daos.get(type);
	}
}
