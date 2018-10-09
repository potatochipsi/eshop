package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.OrderBean;
import com.util.DBUtil;

/**
 * 订单操作Dao
 * @author 胡建峰
 */
public class OrderDao {
	
	/**
	 * 查看订单详情
	 * 
	 * @param id
	 * @return
	 */
	public OrderBean lookById (int id) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = DBUtil.getConn();
		String sql = "select * from user_order where id='" + id + "'";
		OrderBean orderBean = new OrderBean();
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				OrderProductDao orderProductDao = new OrderProductDao();
				orderBean.setId(rs.getInt("id"));
				orderBean.setCode(rs.getString("code"));
				orderBean.setOriginal_price(rs.getFloat("original_price"));
				orderBean.setPrice(rs.getFloat("price"));
				int addressId = rs.getInt("address_id");
				orderBean.setAddressBean((new AddressDao()).getAddressById(addressId));
				int userId = rs.getInt("user_id");
				orderBean.setUserBean((new UserDao()).getById(userId));
				orderBean.setPayment_type(rs.getInt("payment_type"));
				orderBean.setStatus(rs.getInt("status"));
				orderBean.setCreate_date(rs.getString("create_date"));
				orderBean.setOrderProductBeans(orderProductDao.getOrderProductBeans(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return orderBean;
	}
	
	/**
	 * 获取订单列表
	 * 
	 * @param id
	 * @return
	 */
	public List<OrderBean> getList (int id, int start, int size) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		String sql = "select top " + size +  " * from user_order where user_id='"+id+"' and (id not in(select top " + start + " id from user_order where user_id='" + id + "'))";
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		OrderProductDao orderProductDao = new OrderProductDao();
		
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			OrderBean bean;
			while(rs.next()) {
				bean = new OrderBean();
				bean.setId(rs.getInt("id"));
				bean.setCode(rs.getString("code"));
				bean.setOriginal_price(rs.getFloat("original_price"));
				bean.setPrice(rs.getFloat("price"));
				bean.setCreate_date(rs.getString("create_date"));
				int addressId = rs.getInt("address_id");
				bean.setAddressBean((new AddressDao()).getAddressById(addressId));
				int userId = rs.getInt("user_id");
				bean.setUserBean((new UserDao()).getById(userId));
				bean.setPayment_type(rs.getInt("payment_type"));
				bean.setStatus(rs.getInt("status"));
				bean.setOrderProductBeans(orderProductDao.getOrderProductBeans(rs.getInt("id")));
				orderBeans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return orderBeans;
	}
	
	/**
	 * 删除订单
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id) {
		String sql="delete from user_order where id='"+id+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		int a = 0;
		boolean f = false;
		
		try {
			state = conn.createStatement();
			a= state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
		
		if (a>0){
			f=true;
		}
		return f;
	}
	
	/**
	 * 添加订单
	 * 
	 * @param orderBean
	 * @return
	 */
	public boolean addOrder(OrderBean orderBean) {
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="insert into user_order(code,original_price,price,user_id,create_date) values('"+orderBean.getCode()+"','"+orderBean.getOriginal_price()+"','"+orderBean.getPrice() + "','"+orderBean.getUserBean().getId()+"','" + createDate1.format(new Date()) + "')";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		try {
			state = conn.createStatement();
			a= state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
		if (a > 0){
			f = true;
		}
		return f;
	}

	/**
	 * 通过订单编号获取订单
	 * 
	 * @param code
	 * @return
	 */
	public OrderBean getOrderByCode(String code) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		OrderBean orderBean = new OrderBean();
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery("select * from user_order where code='"+code+"'");
			if(rs.next()) {
				orderBean.setId(rs.getInt("id"));
				orderBean.setCode(rs.getString("code"));
				orderBean.setOriginal_price(rs.getFloat("original_price"));
				orderBean.setPrice(rs.getFloat("price"));
				orderBean.setAddress_id(rs.getInt("address_id"));
				orderBean.setUser_id(rs.getInt("user_id"));
				orderBean.setPayment_type(rs.getInt("payment_type"));
				orderBean.setStatus(rs.getInt("status"));
				orderBean.setCreate_date(rs.getString("create_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return orderBean;
	}

	/**
	 * 更新订单
	 * 
	 * @param orderBean
	 */
	public boolean upOrder(OrderBean orderBean) {
		String sql="update user_order set original_price='" + orderBean.getOriginal_price() + "',price='" + orderBean.getPrice() + "',address_id='" + orderBean.getAddress_id() + 
				"',payment_type='"+orderBean.getPayment_type()+"' where id='"+orderBean.getId()+"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		int a = 0;
		boolean f = false;
		try {
			state = conn.createStatement();
			a= state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
		if (a>0){
			f=true;
		}
		return f;
	}

	/**
	 * 得到用户的订单总数量
	 * @param id 
	 * @return
	 */
	public int getCount(int id) {
		String sql = "select count(*) count from user_order where user_id = '" + id + "'";
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

	/**
	 * 得到所有订单的数量
	 * 
	 * @return
	 */
	public int getAllCount() {
		String sql = "select count(*) count from user_order";
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
	
	/**
	 * 得到分页的订单
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<OrderBean> getAllList(int start, int size) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		String sql = "select top " + size +  " * from user_order where (id not in(select top " + start + " id from user_order))";
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			OrderBean bean;
			while(rs.next()) {
				bean = new OrderBean();
				bean.setId(rs.getInt("id"));
				bean.setCode(rs.getString("code"));
				bean.setOriginal_price(rs.getFloat("original_price"));
				bean.setPrice(rs.getFloat("price"));
				bean.setCreate_date(rs.getString("create_date"));
				int addressId = rs.getInt("address_id");
				bean.setAddressBean((new AddressDao()).getAddressById(addressId));
				int userId = rs.getInt("user_id");
				bean.setUserBean((new UserDao()).getById(userId));
				bean.setPayment_type(rs.getInt("payment_type"));
				bean.setStatus(rs.getInt("status"));
				orderBeans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return orderBeans;
	}

	
	/**
	 * 更新订单状态
	 * 
	 * @param id
	 * @param status
	 */
	public void update(String id, String status) {
		String sql="update user_order set status='" + status + "' where id='" + id +"'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close( state, conn);
		}
	}

	/**
	 * 根据订单状态查询
	 * 
	 * @param id
	 * @param start
	 * @param size
	 * @param search
	 * @return
	 */
	public List<OrderBean> getListBySearch(int id, int start, int size, int search) {
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		String sql = "select top " + size +  " * from user_order where user_id='" + id + "' and status='" + search + "' and (id not in(select top " + start + " id from user_order where user_id='" + id + "' and status='" + search + "'))";
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		OrderProductDao orderProductDao = new OrderProductDao();
		
		try {
			conn = DBUtil.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			OrderBean bean;
			while(rs.next()) {
				bean = new OrderBean();
				bean.setId(rs.getInt("id"));
				bean.setCode(rs.getString("code"));
				bean.setOriginal_price(rs.getFloat("original_price"));
				bean.setPrice(rs.getFloat("price"));
				bean.setCreate_date(rs.getString("create_date"));
				int addressId = rs.getInt("address_id");
				bean.setAddressBean((new AddressDao()).getAddressById(addressId));
				int userId = rs.getInt("user_id");
				bean.setUserBean((new UserDao()).getById(userId));
				bean.setPayment_type(rs.getInt("payment_type"));
				bean.setStatus(rs.getInt("status"));
				bean.setOrderProductBeans(orderProductDao.getOrderProductBeans(rs.getInt("id")));
				orderBeans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return orderBeans;
	}
}
