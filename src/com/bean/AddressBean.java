package com.bean;

public class AddressBean {
	private int id;
	private String name;
	private String provincename;
	private String cityname;
	private String areaname;
	private String address;
	private String cellphone;
	private int userId;
	private int status;
	private String createDate;
	
	public AddressBean() {
		super();
	}

	public AddressBean(int id, String name, String provincename, String cityname, String areaname, String address,
			String cellphone, int userId, int status, String createDate) {
		super();
		this.id = id;
		this.name = name;
		this.provincename = provincename;
		this.cityname = cityname;
		this.areaname = areaname;
		this.address = address;
		this.cellphone = cellphone;
		this.userId = userId;
		this.status = status;
		this.createDate = createDate;
	}

	public AddressBean(int id, String name, String provincename, String cityname, String areaname, String address, String cellphone, int status) {
		super();
		this.id = id;
		this.name = name;
		this.provincename = provincename;
		this.cityname = cityname;
		this.areaname = areaname;
		this.address = address;
		this.cellphone = cellphone;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
