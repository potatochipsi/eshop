package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.UserBean;
import com.util.DBUtil;
import com.util.MD5;

public class UserDao {

	/**
	 * 注册用户
	 * 
	 * @param userBean
	 */
	public void add(UserBean userBean) {
		String sql = "insert into [user](username,password,salt,status,create_date,sex,nickname,truename,pic) values('" + userBean.getUsername() + "','" 
				+ userBean.getPassword() + "','" + userBean.getSalt() + "','1','" + userBean.getCreatedate() + "','" + userBean.getSex() + "','" + userBean.getNickname()
				+ "','" + userBean.getTruename() + "','" + userBean.getPic() + "')";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
	}

	/**
	 * 检验用户名是否重复
	 * 
	 * @param username
	 * @return
	 */
	public boolean validate(String username) {
		boolean f = true;
		// 查询用户是否已存在
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select username from [user]");
			while (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					f = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return f;
	}

	/**
	 * 登录检测
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserBean checkLogin(String username, String password) {
		Connection conn = DBUtil.getConn();
		UserBean userBean = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			state = conn.createStatement();
			rs = state.executeQuery("select * from [user] where username='" + username + "'");
			if (rs.next()) {
				// 如果有结果，是认为是通过验证了
				if (rs.getString("password").equals(MD5.GetMD5Code(password+rs.getString("salt")))) {
					userBean = new UserBean();
					userBean.setId(rs.getInt("id"));
					userBean.setUsername(rs.getString("username"));
					userBean.setPassword(rs.getString("password"));
					userBean.setSalt(rs.getString("salt"));
					userBean.setCreatedate(rs.getString("create_date"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return userBean;
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserBean> getList(int start, int size) {
		String sql = "select top " + size + " * from [user] where(id not in(select top " + start + " id from [user]))";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		List<UserBean> userBeans = new ArrayList<>();
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			UserBean userBean = new UserBean();
			while(rs.next()) {
				int id = rs.getInt("id");
				String pic = rs.getString("pic");
				String username = rs.getString("username");
				String nickname = rs.getString("nickname");
				String truename = rs.getString("truename");
				int sex = rs.getInt("sex");
				int status = rs.getInt("status");
				userBean = new UserBean(id, username, status, sex, nickname, truename, pic);
				userBeans.add(userBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return userBeans;
	}

	/**
	 * 更新用户状态
	 * 
	 * @param id
	 * @param status
	 */
	public void update(int id, int status) {
		String sql = "update [user] set status='" + status + "' where id = '" + id + "'";
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
	
	/**
	 * 通过id获取UserBean
	 * 
	 * @param id
	 * @return
	 */
	public UserBean getById(int id) {
		String sql = "select * from [user] where id = '" + id + "'";
		UserBean userBean = null;
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				String pic = rs.getString("pic");
				String username = rs.getString("username");
				String nickname = rs.getString("nickname");
				String truename = rs.getString("truename");
				String password = rs.getString("password");
				int sex = rs.getInt("sex");
				int status = rs.getInt("status");
				String createDate = rs.getString("create_date");
				userBean = new UserBean(id, username, password, status, createDate, sex, nickname, truename, pic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return userBean;
	}

	/**
	 * 通过登录名获取id
	 * 
	 * @param name
	 * @return
	 */
	public int getIdByName(String name) {
		String sql = "select id from [user] where username='" + name + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return id;
	}

	/**
	 * 查看是否冻结
	 * 
	 * @param username
	 * @return
	 */
	public boolean checkDong(String username) {
		boolean f = false;
		String sql = "select status from [user] where username='" + username + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		int status = 0;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				status = rs.getInt("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		if(status == 0)
			f = true;
		return f;
	}

	/**
	 * 得到用户总数
	 * @return
	 */
	public int getCount() {
		String sql = "select count(*) count from [user]";
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		int size = 0;
		
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				size = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return size;
	}
	
	public void update1(UserBean userBean,int id)
	{
		String sql = "update [user] set username='"+userBean.getUsername()+"',password='"+userBean.getPassword()+"',nickname='"+userBean.getNickname()+"',salt='"+userBean.getSalt()+"' where id='"+ id+"'";
		Connection con = DBUtil.getConn();
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(st, con);
		}
	}
	
	
	public void updatePic(UserBean userBean,int id)
	{

		String sql = "update [user] set pic='"+userBean.getPic()+"' where id='"+ id+"'";
		Connection con = DBUtil.getConn();
		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(st, con);
		}
	}
}
