package com.xqq.mvc.domain;

public class CriteriaCustomer {
	
	private String id;
	private String name;
	private String address;
	private String phone;
	
	public String getName() {
		if(name == null)
			name = "%%";
		else
			name = "%"+ name + "%";
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		if(id == null){
			id = "%%";
		}else{
			id = "%" + id + "%";
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		if(address == null)
			address = "%%";
		else
			address = "%"+ address + "%";
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		if(phone == null)
			phone = "%%";
		else
			phone = "%"+ phone + "%";
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public CriteriaCustomer(String id, String name, String address,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	
}
