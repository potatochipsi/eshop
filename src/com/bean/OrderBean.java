package com.bean;

import java.util.List;

public class OrderBean {
	private int id;
	private String code;
	private float original_price;
	private float price;
	private int address_id;
	private AddressBean addressBean;
	private int user_id;
	private UserBean userBean;
	private int payment_type;
	private int status;
	private String create_date;
	private List<OrderProductBean> orderProductBeans;
	
	public OrderBean() {
	}

	public OrderBean(String code, UserBean userBean) {
		this.code = code;
		this.userBean = userBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(float original_price) {
		this.original_price = original_price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public AddressBean getAddressBean() {
		return addressBean;
	}

	public void setAddressBean(AddressBean addressBean) {
		this.addressBean = addressBean;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public List<OrderProductBean> getOrderProductBeans() {
		return orderProductBeans;
	}

	public void setOrderProductBeans(List<OrderProductBean> orderProductBeans) {
		this.orderProductBeans = orderProductBeans;
	}
}
