package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.AdminBean;
import com.util.DBUtil;
import com.util.MD5;

/**
 * admin�����ݿ����
 * 
 * @author ������
 *
 */
public class AdminDao {

	/**
	 * ��¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminBean checkLogin(String username, String password) {
		Connection conn = DBUtil.getConn();
		AdminBean adminBean = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from admin where username='" + username + "'");
			if (rs.next()) {
				// ����н��������Ϊ��ͨ����֤��
				if (rs.getString("password").equals(MD5.GetMD5Code(password+rs.getString("salt")))) {
					adminBean = new AdminBean();
					adminBean.setId(rs.getInt("id"));
					adminBean.setUsername(rs.getString("username"));
					adminBean.setPassword(rs.getString("password"));
					adminBean.setSalt(rs.getString("salt"));
					adminBean.setCreateDate(rs.getString("create_date"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminBean;
	}

	/**
	 * ����Ƿ���ڴ��û�
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkReg(String name) {
		boolean flag = true;
		// ��ѯ�û��Ƿ��Ѵ���
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select username from admin");
			while (rs.next()) {
				if (name.equals(rs.getString("username"))) {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return flag;
	}
	
	/**
	* ��ӹ���Ա
	* 
	* @param adminBean
	*/
	public void save(AdminBean adminBean) {
		String sql = "insert into admin(username,password,salt,create_date) values('" + adminBean.getUsername() + "','" 
				+ adminBean.getPassword() + "','" + adminBean.getSalt() + "','" + adminBean.getCreateDate() + "')";
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
	* �鿴����Ա
	*
	* @return
	*/
	public List<AdminBean> list() {
		String sql = "select * from admin";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			AdminBean adminBean;
			while (resultSet.next()) {
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassword(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
				adminBeans.add(adminBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, statement, connection);
		}
		
		return adminBeans;
	}

	/**
	* ͨ�� id ��ȡ adminBean ����
	*
	* @param id
	* @return
	*/
	public AdminBean getById(int id) {
		String sql = "select * from admin where id =" + id;
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		AdminBean adminBean = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));
				adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassword(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, statement, connection);
		}
		return adminBean;
	}
	
	/**
	* �޸Ĺ���Ա
	*
	* @param adminBean
	*/
	public void update(AdminBean adminBean) {
		String sql = "update admin set username='" + adminBean.getUsername() + "',password='" + adminBean.getPassword()
		+ "',salt='" + adminBean.getSalt() + "' where id='" + adminBean.getId() + "'";
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
	* ͨ�� id ɾ��
	*
	* @param id
	*/
	public void delete(int id) {
		String sql = "delete from admin where id = " + id;
		Connection conn = DBUtil.getConn();
		Statement state = null;
		try {
			state = conn.createStatement();state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
	}
	
	/**
	* ��ȡ���ݱ�����������
	* 
	* @return
	*/
	public int getCount() {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		int size = 0;
		
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery("select count(*) count from admin");
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
	
	/**
	* ��ȡÿһ����ҳ������
	* 
	* @param start
	* @param size
	* @return
	*/
	public List<AdminBean> getListByPage(int start, int size) {
		String sql = "select top " + size + " * from admin where(id not in(select top " + start + " id from admin))";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			AdminBean adminBean;
			while (resultSet.next()) {
				adminBean = new AdminBean();
				adminBean.setId(resultSet.getInt("id"));
				adminBean.setUsername(resultSet.getString("username"));
				adminBean.setPassword(resultSet.getString("password"));
				adminBean.setSalt(resultSet.getString("salt"));
				adminBean.setCreateDate(resultSet.getString("create_date"));
				adminBeans.add(adminBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, statement, connection);
		}
		return adminBeans;
	}
}