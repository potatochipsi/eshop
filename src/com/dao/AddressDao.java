package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.AddressBean;
import com.util.DBUtil;

/**
 * 地址操作
 */
public class AddressDao {
	
	private static Connection con;
	private static Statement state;
	private static ResultSet rs;
	
	/**
	 * 通过用户的ID得到地址列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<AddressBean> getAddressList(String userId) {
		List<AddressBean> list = new ArrayList<>();
		UserDao userDao = new UserDao();
		String sql = "select * from user_address where user_id='" + userDao.getIdByName(userId) + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		AddressDao addressDao = new AddressDao();
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String provincename = addressDao.getProvinceById(rs.getInt("province"));
				String cityname = addressDao.getCityById(rs.getInt("city"));
				String areaname = addressDao.getAreaById(rs.getInt("region"));
				String address = rs.getString("address");
				String cellphone = rs.getString("cellphone");
				int status = rs.getInt("status");
				AddressBean addressBean = new AddressBean(id, name, provincename, cityname, areaname, address, cellphone, status);
				list.add(addressBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}

	/**
	 * 通过id得到Area
	 * 
	 * @param areaId
	 * @return
	 */
	private String getAreaById(int areaId) {
		String areaName = null;
		String sql = "select name from area where id = '" + areaId + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				areaName = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return areaName;
	}

	/**
	 * 通过ID得到城市
	 * 
	 * @param cityId
	 * @return
	 */
	private String getCityById(int cityId) {
		String cityName = null;
		String sql = "select name from city where id = '" + cityId + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				cityName = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return cityName;
	}

	/**
	 * 通过ID得到省名
	 * 
	 * @param pId
	 * @return
	 */
	private String getProvinceById(int pId) {
		String pName = null;
		String sql = "select name from province where id = '" + pId + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				pName = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}
		return pName;
	}

	/**
	 * 通过ID获取地址
	 * 
	 * @param addressId
	 * @return
	 */
	public AddressBean getAddressById(int id) {
		String sql = "select * from user_address where id = '" + id + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		AddressDao addressDao = new AddressDao();
		AddressBean addressBean = null;
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				String provincename = addressDao.getProvinceById(rs.getInt("province"));
				String cityname = addressDao.getCityById(rs.getInt("city"));
				String areaname = addressDao.getAreaById(rs.getInt("region"));
				String address = rs.getString("address");
				String cellphone = rs.getString("cellphone");
				int status = rs.getInt("status");
				addressBean = new AddressBean(id, name, provincename, cityname, areaname, address, cellphone, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, conn);
		}

		return addressBean;
	}
	
	public AddressBean getAddress(int id) throws SQLException{
		String sql = "select * from [user_address] where id ='"+id+"'";
		AddressBean addressBean=null;
		Connection con2=DBUtil.getConn();
		Statement state2=con2.createStatement();
		ResultSet rs2=state2.executeQuery(sql);
		try {
			while(rs2.next())
			{
				
				addressBean = new AddressBean();
				addressBean.setId(rs2.getInt("id"));
				addressBean.setCellphone(rs2.getString("cellphone"));
				addressBean.setAddress(rs2.getString("address"));
				addressBean.setName(rs2.getString("name"));
				addressBean.setStatus(rs2.getInt("status"));
				
				Connection con3=DBUtil.getConn();
				Statement state3=con3.createStatement();
				String sql2="select P.name province ,C.name city,A.name area from province P join city C on P.id=C.province_id join area A on C.id=A.city_id where  A.id='"+rs2.getInt("region")+"'";
				ResultSet rs3=state3.executeQuery(sql2);
				while(rs3.next())
				{
					addressBean.setAreaname(rs3.getString("area"));
					addressBean.setProvincename(rs3.getString("province"));
					addressBean.setCityname(rs3.getString("city"));
				}
				DBUtil.close(rs3, state3, con3);
			}
//		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs2, state2, con2);
		}
		
		return addressBean;
	}
	
	public List<AddressBean> getprovince(){
		String sql = "select * from province ";
		List<AddressBean> provinces = new ArrayList<AddressBean>();
		try {
			con = DBUtil.getConn();
			state = con.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next())
			{
				AddressBean province=new AddressBean();
				province.setName(rs.getString("name"));
				province.setId(rs.getInt("id"));
				provinces.add(province);
			}
//
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, con);
		}
		
		return provinces;
	}
	
	public List<AddressBean> getcity(int id){
		String sql = "select * from city where province_id='"+id+"'";
		List<AddressBean> citys = new ArrayList<AddressBean>();
		try {
			con = DBUtil.getConn();
			state = con.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next())
			{
				AddressBean city=new AddressBean();
				city.setName(rs.getString("name"));
				city.setId(rs.getInt("id"));
				citys.add(city);
			}
//
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, con);
		}
		
		return citys;
	}
	
	public List<AddressBean> getarea(int id){
		String sql = "select * from area where city_id='"+id+"'";
		List<AddressBean> areas = new ArrayList<AddressBean>();
		try {
			con = DBUtil.getConn();
			state = con.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next())
			{
				AddressBean area=new AddressBean();
				area.setName(rs.getString("name"));
				area.setId(rs.getInt("id"));
				areas.add(area);
			}
//
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, state, con);
		}
		
		return areas;
	}
	
	public void add(AddressBean address,int province,int city,int area)
	{
		String sql = "insert into [user_address] (name,user_id,cellphone,province,city,region,address,create_date) values('"
				+address.getName()+ "','"+address.getUserId()+"','"+address.getCellphone()+"','"+province
				+ "','"+city+ "','"+area+ "','"+address.getAddress()+ "',"+" getdate() "+
				")";
		Connection con = DBUtil.getConn();
		Statement state = null;
		try{
			state = con.createStatement();
			state.execute(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(state, con);
		}
	}
	
	public void delete(int id)
	{
		String sql = "delete from user_address where id='"+id+"'";
		Connection con = DBUtil.getConn();
		Statement state = null;
		try{
			state = con.createStatement();
			state.execute(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(state, con);
		}
	}
	
	public void setdefault(int user_id,int id)
	{
		String sql = "update user_address set status ='0' where user_id='"+user_id+"'"
					 +"update user_address set status ='1' where id='"+id+"'";
		Connection con = DBUtil.getConn();
		Statement state = null;
		try{
			state = con.createStatement();
			state.execute(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(state, con);
		}
	}
}
