package com.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conting.vo.CustomerVO;
import com.conting.vo.RoomVO;


/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class CustomerDAO {
	Connection conn = null;
	ConnectionManager cm = new ConnectionManager();
	PreparedStatement pstmt = null;
	
	public boolean create(CustomerVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "insert into customer values(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getProfileName());
			pstmt.setString(2, vo.getVisit());
			pstmt.setBoolean(3, vo.getChief());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("CustomerDAO : [ create error ]");
			e.printStackTrace();
			return false;
		} finally {
			cm.disconnect();
		}
		return true;
	}
	
	public boolean delete(String profileName) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "delete from Customer where profileName=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  profileName);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("CustomerDAO : [ delete error ]");
			e.printStackTrace();
			return false;
		} finally {
			cm.disconnect();
		}
		return true;
	}
	
	public boolean update(CustomerVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "update customer set visit=?, "
				+ "chief=? where profileName=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getVisit());
			pstmt.setBoolean(2, vo.getChief());
			pstmt.setString(3, vo.getProfileName());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("CustomerDAO : [ update error ]");
			e.printStackTrace();
			return false;
		}
		finally {
			cm.disconnect();
		}
		return true;
	}
	
	public CustomerVO search(String profileName) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "select * from Customer where profileName=?";
		CustomerVO vo = new CustomerVO();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profileName);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			vo.setProfileName(rs.getString("profileName"));
			vo.setVisit(rs.getString("visit"));
			vo.setChief(rs.getBoolean("chief"));
			rs.close();
		} catch(SQLException e) {
			System.out.println("Don't search");
//			System.out.println("CustomerDAO : [ search error ]");
//			e.printStackTrace();
			return null;
		} finally {
			cm.disconnect();
		}
		return vo;
	}
	
	public List<CustomerVO> searchVisitor(String visit) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		List<CustomerVO> list = new ArrayList<CustomerVO>();
	
		String sql = "select * from customer where visit=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, visit);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerVO vo = new CustomerVO();
				
				vo.setProfileName(rs.getString("profileName"));
				vo.setVisit(rs.getString("visit"));
				vo.setChief(rs.getBoolean("chief"));
				list.add(vo);
			}
			rs.close();
			
		} catch(SQLException e) {
			System.out.println("Don't search visitor");
//			System.out.println("RoomDAO : [ searchList error ]");
//			e.printStackTrace();
		} finally {
			cm.disconnect();
		}
		return list;
	}
}
