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
public class RoomDAO {
	Connection conn = null;
	ConnectionManager cm = new ConnectionManager();
	PreparedStatement pstmt = null;
	
	public boolean create(RoomVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "insert into Room(title, capacity, purpose, " 
				 + "beginTime, endTime) values(?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getCapacity());
			pstmt.setInt(3, vo.getPurpose());
			pstmt.setInt(4, vo.getBeginTime());
			pstmt.setInt(5, vo.getEndTime());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("RoomDAO : [ create error ]");
			e.printStackTrace();
			return false;
		} finally {
			cm.disconnect();
		}
		return true;
	}
	
	public boolean delete(String title) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "delete from Room where title=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  title);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("RoomDAO : [ delete error ]");
			e.printStackTrace();
			return false;
		} finally {
			cm.disconnect();
		}
		return true;
	}
	
	public boolean lock(RoomVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "update room set lockDown=? where title=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getLockDown());
			pstmt.setString(2, vo.getTitle());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("RoomDAO : [ lock error ]");
			e.printStackTrace();
			return false;
		}
		finally {
			cm.disconnect();
		}
		return true;
	}
	
	public RoomVO search(String title) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "select * from Room where title=?";
		RoomVO vo = new RoomVO();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			vo.setTitle(rs.getString("title"));
			vo.setCapacity(rs.getInt("capacity"));
			vo.setPurpose(rs.getInt("purpose"));
			vo.setBeginTime(rs.getInt("beginTime"));
			vo.setEndTime(rs.getInt("endTime"));
			vo.setLockDown(rs.getInt("lockDown"));
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
	
	public List<RoomVO> searchList() throws Exception {
		cm.connect();
		conn = cm.getConnection();
		List<RoomVO> list = new ArrayList<RoomVO>();
	
		String sql = "select * from Room";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				RoomVO vo = new RoomVO();
				
				vo.setTitle(rs.getString("title"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPurpose(rs.getInt("purpose"));
				vo.setBeginTime(rs.getInt("beginTime"));
				vo.setEndTime(rs.getInt("endTime"));
				vo.setLockDown(rs.getInt("lockDown"));
				list.add(vo);
			}
			rs.close();
			
		} catch(SQLException e) {
			System.out.println("Don't search list");
//			System.out.println("RoomDAO : [ searchList error ]");
//			e.printStackTrace();
		} finally {
			cm.disconnect();
		}
		return list;
	}
}
