package com.bean;

public class CommentBean {
	private int id;
	private int score;
	private String content;
	private int user_id;
	private UserBean userBean;
	private int product_id;
	private String create_date;
	
	public CommentBean(int score, String content, int user_id, int product_id, String create_date) {
		super();
		this.score = score;
		this.content = content;
		this.user_id = user_id;
		this.product_id = product_id;
		this.create_date = create_date;
	}
	
	public CommentBean() {
	}

	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
}
