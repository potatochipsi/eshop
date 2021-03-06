package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.ProductOptionBean;
import com.bean.ProductPropertyBean;
import com.util.DBUtil;

public class ProductOptionDao {
	
	/**
	 * 添加选项
	 * 
	 * @param productOptionBean
	 * @return
	 */
	public boolean add(ProductOptionBean productOptionBean) {
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into product_type_property_option(name,product_type_property_id,sort,create_date) values('"
		+ productOptionBean.getName() + "','" + productOptionBean.getPropertyId() + "','" + productOptionBean.getSort() + "','" + createDate1.format(new Date()) + "')";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a> 0) {
			f = true;
		}
		return f;
	}
	
	/**
	 * 通过属性获取选项list
	 * 
	 * @param propertyId
	 * @return
	 */
	public List<ProductOptionBean> getOptionByProperty (int propertyId) {
		String sql = "select * from product_type_property_option where product_type_property_id='" + propertyId + "'";
		List<ProductOptionBean> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			ProductOptionBean productOptionBean = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sort = rs.getInt("sort");
				String createDate = rs.getString("create_date");
				int optionId = rs.getInt("product_type_property_id");
				productOptionBean = new ProductOptionBean(id, sort, optionId, name, createDate);
				list.add(productOptionBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}
	
	/**
	 * 通过id获取单个选项
	 * 
	 * @param optionId
	 * @return
	 */
	public ProductOptionBean getOptionById(int optionId) {
		String sql = "select * from product_type_property_option where id='" + optionId + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		ProductOptionBean productOptionBean = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int sort = rs.getInt("sort");
				int productPropertyId = rs.getInt("product_type_property_id");
				String name = rs.getString("name");
				String createDate = rs.getString("create_date");
				ProductPropertyDao productPropertyDao = new ProductPropertyDao();
				ProductPropertyBean productPropertyBean = productPropertyDao.getPropertyById(productPropertyId);
				productOptionBean = new ProductOptionBean(id, sort, productPropertyId, name, createDate);
				productOptionBean.setProductPropertyBean(productPropertyBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return productOptionBean;
	}
	
	/**
	 * 修改
	 * 
	 * @param productOptionBean
	 * @return
	 */
	public boolean update (ProductOptionBean productOptionBean) {
		String sql = "update product_type_property_option set name='" + productOptionBean.getName() + "',sort='" + productOptionBean.getSort()
		+ "',product_type_property_id='" + productOptionBean.getPropertyId() + "' where id='" + productOptionBean.getId() + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a > 0) {
			f = true;
		}
		return f;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "delete from product_type_property_option where id='" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		
		if (a == 0) {
			f = true;
		}
		return f;
	}
}
