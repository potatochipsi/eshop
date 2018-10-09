package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.CommentBean;
import com.util.DBUtil;

public class ProductCommentDao {

	/**
	 * 得到评论列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CommentBean> getComsByProduct(int id) {
		String sql = "select * from user_product_comment where product_id ='" + id + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CommentBean> list = new ArrayList<>();
		UserDao userDao = new UserDao();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CommentBean commentBean = new CommentBean();
				commentBean.setId(resultSet.getInt("id"));
				commentBean.setScore(resultSet.getInt("score"));
				commentBean.setContent(resultSet.getString("content"));
				commentBean.setCreate_date(resultSet.getString("create_date"));
				commentBean.setUser_id(resultSet.getInt("user_id"));
				commentBean.setUserBean(userDao.getById(resultSet.getInt("user_id")));
				list.add(commentBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, statement, connection);
		}
		return list;
	}

	/**
	 * 添加评论
	 * 
	 * @param commentBean
	 */
	public void add(CommentBean commentBean) {
		String sql = "insert into user_product_comment(score,content,user_id,product_id,create_date) values('"
				+ commentBean.getScore() + "','" + commentBean.getContent() + "','" + commentBean.getUser_id() + "','" + commentBean.getProduct_id()+ "','" + commentBean.getCreate_date() + "')";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
	}

}
