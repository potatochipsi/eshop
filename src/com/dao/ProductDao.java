package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.ProductBean;
import com.bean.ProductTypeBean;
import com.util.DBUtil;

/**
 * 商品Dao
 * 
 * @author 胡建峰
 *
 */
public class ProductDao {
	
	/**
	 * 添加
	 * 
	 * @param productBean
	 * @return
	 */
	public boolean add (ProductBean productBean) {
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into product(name,original_price,price,intro,product_type_id,number,pic,product_properties,create_date) values('"
				+ productBean.getName() + "','" + productBean.getOriginalPrice() + "','" + productBean.getPrice() + "','" + productBean.getIntro()
				+ "','" + productBean.getProductTypeId() + "','" + productBean.getNumber() + "','" + productBean.getPic() + "','" + productBean.getProductProperties()
				+ "','" + createDate1.format(new Date()) + "')";
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
	 * 获取所有商品
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<ProductBean> getList(int start, int size) {
		String sql = "select top " + size + " * from product where(id not in(select top " + start + " id from product))";
		List<ProductBean> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			ProductBean productBean = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				productBean = new ProductBean(id, name);
				list.add(productBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}
	
	/**
	 * 修改商品
	 * 
	 * @param productBean
	 */
	public boolean update(ProductBean productBean) {
		String sql = "update product set ";
		if (productBean.getName() != null) {
			sql += "name='" + productBean.getName() + "'";
		}
		if (productBean.getOriginalPrice() != 0) {
			sql += ", original_price='" + productBean.getOriginalPrice() + "'";
		}
		if (productBean.getPrice() != 0) {
			sql += ", price='" + productBean.getPrice() + "'";
		}
		if (productBean.getIntro() != null) {
			sql += ", intro='" + productBean.getIntro() + "'";
		}
		if (productBean.getProductTypeId() != 0) {
			sql += ", product_type_id='" + productBean.getProductTypeId() + "'";
		}
		if (productBean.getNumber() != 0) {
			sql += ", number='" + productBean.getNumber() + "'";
		}
		if (productBean.getPic() != null) {
			sql += ", pic='" + productBean.getPic() + "'";
		}
		if (productBean.getProductProperties() != null) {
			sql += ", product_properties='" + productBean.getProductProperties() + "'";
		}
		sql += " where id='" + productBean.getId() + "'";
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
	 * 通过ID获取商品详情
	 * 
	 * @param id
	 * @return
	 */
	public ProductBean getProduct(int id) {
		String sql = "select * from product where id = '" + id + "'";
		ProductBean productBean = null;
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				float originalPrice = rs.getFloat("original_price");
				float price = rs.getFloat("price");
				String intro = rs.getString("intro");
				int typeId = rs.getInt("product_type_id");
				int number = rs.getInt("number");
				int sellNumber = rs.getInt("sell_number");
				String pic = rs.getString("pic");
				String productProperties = rs.getString("product_properties");
				String createDate = rs.getString("create_date");
				productBean = new ProductBean(id, name, originalPrice, price, intro, typeId, number, sellNumber, pic, productProperties, createDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return productBean;
	}
	
	/**
	 * 删除商品
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete (int id) {
		boolean f = false;
		String sql = "delete from product where id='" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
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
	 * 搜索所选分类及其子类的所有商品
	 * 
	 * @param type_id
	 * @return
	 */
	public List<ProductBean> getListById(int type_id) {
		List<ProductBean> list = new ArrayList<>();
		ProductTypeDao productTypeDao = new ProductTypeDao();
		List<ProductTypeBean> typeList = productTypeDao.getTypeBeans(type_id);
		for (ProductTypeBean type : typeList) {
			List<ProductBean> list2 = getListById(type.getId());
			list.addAll(list2);
		}
		
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		String sql = "select * from product where product_type_id='" + type_id + "'";
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			ProductBean productBean = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int number = rs.getInt("number");
				String pic = rs.getString("pic");
				productBean = new ProductBean(id, name, price, number, pic);
				list.add(productBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}

	/**
	 * 搜索关键字得到有关商品
	 * 
	 * @param key
	 * @return
	 */
	public List<ProductBean> getListByKey(String key) {
		List<ProductBean> list = new ArrayList<>();
		String sql = "select * from product where name like '%" + key + "%'";
		Connection conn = DBUtil.getConn(); 
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String pic = rs.getString("pic");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int number = rs.getInt("number");
				ProductBean productBean = new ProductBean(id, name, price, number, pic);
				list.add(productBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return list;
	}

	/**
	 * 得到商品总量
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
			rs = state.executeQuery("select count(*) count from product");
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
}
