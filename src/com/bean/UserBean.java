package com.bean;

public class UserBean {
	private int id;
	private String username;
	private String password;
	private String salt;
	private int status;
	private String createdate;
	private int sex;
	private String nickname;
	private String truename;
	private String pic;
	
	public UserBean(int id, String username, String password, String salt, int status, String createdate, int sex,
			String nickname, String truename, String pic) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.status = status;
		this.createdate = createdate;
		this.sex = sex;
		this.nickname = nickname;
		this.truename = truename;
		this.pic = pic;
	}
	
	public UserBean() {
	}

	public UserBean(int id, String username, int status, int sex, String nickname, String truename, String pic) {
		this.id = id;
		this.username = username;
		this.status = status;
		this.sex = sex;
		this.nickname = nickname;
		this.truename = truename;
		this.pic = pic;
	}

	public UserBean(int id, String username, int status, String createdate, int sex, String nickname, String truename,
			String pic) {
		this.id = id;
		this.username = username;
		this.status = status;
		this.createdate = createdate;
		this.sex = sex;
		this.nickname = nickname;
		this.truename = truename;
		this.pic = pic;
	}
	
	public UserBean(int id, String username, String password, int status, String createdate, int sex, String nickname,
			String truename, String pic) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.createdate = createdate;
		this.sex = sex;
		this.nickname = nickname;
		this.truename = truename;
		this.pic = pic;
	}

	//get/set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
