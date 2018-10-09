package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bean.ProductTypeBean;
import com.util.DBUtil;

/**
* ��Ʒ����DAO
* 
* @author hjf
*
*/
public class ProductTypeDao {
	
	/**
	* ɾ����Ʒ����
	*
	* @param id
	* @return
	*/
	public boolean delete(int id) {
		boolean f = true;
		//ɾ���ӷ���
		List<ProductTypeBean> typeList = getTypeList(id);
		for(ProductTypeBean typeBean : typeList){
			boolean f1 = delete(typeBean.getId());
			if(!f1){
				f = false;
			}
		}
		String sql = "delete from product_type where id='" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
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
			f = false;
		}
		return f;
	}
	
	/**
	* ͨ��������id��ȡ�ӷ����б�
	* ProductTypeBean ֻ��װid,name
	* 
	* @return
	*/
	public List<ProductTypeBean> getTypeBeans(int parentId) {
		String sql = "select * from product_type where parent_id='"+parentId+"'";
		List<ProductTypeBean>typeBeans = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			ProductTypeBean productTypeBean = null;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				productTypeBean = new ProductTypeBean(id, name);
				typeBeans.add(productTypeBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return typeBeans;
	}
	
	/**
	* ��ӷ���
	*/
	public boolean add(ProductTypeBean productTypeBean) {
		SimpleDateFormat createDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into product_type(name,parent_id,sort,intro,create_date) values('" +
		productTypeBean.getName() + "','" + productTypeBean.getParentId() + "','" +
		productTypeBean.getSort() + "','" + productTypeBean.getIntro() + "','" + createDate1.format(new Date()) + "')";
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
		
		if (a > 0) {
			f = true;
		}
		
		return f;
	}
	
	/**
	* ��ȡһ������
	* �������еĸ���
	* ����һ������ list
	* 
	* @param id
	* @param start 
	* @param size 
	* @return
	*/
	public ProductTypeBean getType(int id) {
		List<ProductTypeBean> list = getTypeList(id);
		ProductTypeBean productTypeBean;
		
		if(id==0){
			productTypeBean = new ProductTypeBean();
			productTypeBean.setId(0);
		}else{
			productTypeBean = getTypeById(id);
		}
		productTypeBean.setChildBeans(list);
		return productTypeBean;
	}
	
	/**
	 * ͨ��id�õ���������
	 * 
	 * @param typeId
	 * @return
	 */
	public ProductTypeBean getTypeById(int typeId) {
		String sql = "select * from product_type where id='" + typeId + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		ProductTypeBean productTypeBean = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int sort = rs.getInt("sort");
				int parentId = rs.getInt("parent_id");
				String name = rs.getString("name");
				String intro = rs.getString("intro");
				String createDate = rs.getString("create_date");
				ProductTypeDao productTypeDao = new ProductTypeDao();
				ProductTypeBean parentBean = productTypeDao.getTypeById(parentId);
				productTypeBean = new ProductTypeBean(id, sort, parentBean, name, intro, createDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return productTypeBean;
	}

	/**
	* ͨ���������ȡ�ӷ����б�
	 * @param size 
	 * @param start 
	* 
	* @return
	*/
	public List<ProductTypeBean> getTypeList(int parentId) {
		String sql = "select * from product_type where parent_id='"+parentId+"'";
		List<ProductTypeBean> list = new ArrayList<>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			ProductTypeBean productTypeBean = null;
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sort = rs.getInt("sort");
				String intro = rs.getString("intro");
				String createDate = rs.getString("create_date");
				productTypeBean = new ProductTypeBean(id,name,sort,intro,createDate);
				list.add(productTypeBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}

	/**
	* ���·�������
	*
	* @param productTypeBean
	* @return
	*/
	public boolean update(ProductTypeBean productTypeBean) {
		String sql = "update product_type set name='" + productTypeBean.getName() + "', sort='" + productTypeBean.getSort()
		+ "', intro='" + productTypeBean.getIntro() + "' where id='" + productTypeBean.getId() + "'";
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
		
		if (a > 0) {
			f = true;
		}
		return f;
	}
	
	/**
	 * ��֤
	 * @param name
	 * @return
	 */
	public boolean validate(String name) {
		String sql = "select * from product_type where name='" + name + "'"; 
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		int a = 0;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				a += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		if(a > 0)
			return false;
		else
			return true;
	}

	/**
	 * ��ȡ��������
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
			rs = state.executeQuery("select count(*) count from product_type");
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
