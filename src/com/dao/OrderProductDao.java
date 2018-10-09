package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.OrderProductBean;
import com.util.DBUtil;

/**
 * 订单操作
 * @author 胡建峰
 *
 */
public class OrderProductDao {

	/**
	 * 添加订单中商品的信息
	 * 
	 * @param orderProductBean
	 */
	public void addOrderProduct(OrderProductBean orderProductBean) {
		int orderId = orderProductBean.getOrderBean().getId();
		int productId = orderProductBean.getProductBean().getId();
		float price = orderProductBean.getProductBean().getPrice() * orderProductBean.getNumber();
		int number = orderProductBean.getNumber();
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDate = createDate1.format(new Date());
		
		String sql = "insert into user_order_product(order_id, product_id,price,number,create_date) values ('" + orderId + "','" + productId + "','"
				+ price + "','" + number + "','" + createDate + "')";
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
	 * 通过订单ID得到订单中的商品信息
	 * 
	 * @param id
	 * @return
	 */
	public List<OrderProductBean> getOrderProductBeans(int id) {
		String sql = "select * from user_order_product where order_id ='" + id + "'";
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet resultSet = null;
		List<OrderProductBean> list = new ArrayList<>();
		ProductDao productDao = new ProductDao();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				OrderProductBean orderProductBean = new OrderProductBean();
				orderProductBean.setId(resultSet.getInt("id"));
				orderProductBean.setNumber(resultSet.getInt("number"));
				orderProductBean.setPrice(resultSet.getFloat("price"));
				orderProductBean.setProductBean(productDao.getProduct(resultSet.getInt("product_id")));
				list.add(orderProductBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, statement, connection);
		}
		return list;
	}
	
}
