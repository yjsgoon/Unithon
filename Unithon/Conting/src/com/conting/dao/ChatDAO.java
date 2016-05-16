package com.conting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conting.vo.ChatVO;


/**
 * 
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */
public class ChatDAO {
	Connection conn = null;
	ConnectionManager cm = new ConnectionManager();
	PreparedStatement pstmt = null;
	
	public boolean create(ChatVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		
		String sql = "insert into chat(title, chatContent, profileName, chatTime) values(?, ?, ?, ?)"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getChatContent());
			pstmt.setString(3, vo.getProfileName());
			pstmt.setInt(4, vo.getChatTime());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("ChatDAO : [ create error ]");
			e.printStackTrace();
			return false;
		} finally {
			cm.disconnect();
		}
		return true;
	}
	
	public List<ChatVO> chatList(ChatVO vo) throws Exception {
		cm.connect();
		conn = cm.getConnection();
		List<ChatVO> list = new ArrayList<ChatVO>();
	
		String sql = "select * from chat where title=? and chatTime<=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getChatTime());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ChatVO temp = new ChatVO();
				
				temp.setTitle(rs.getString("title"));
				temp.setChatContent(rs.getString("chatContent"));
				temp.setProfileName(rs.getString("profileName"));
				temp.setChatTime(rs.getInt("chatTime"));
				list.add(temp);
			}
			rs.close();
			
		} catch(SQLException e) {
			System.out.println("Don't chat list");
//			System.out.println("RoomDAO : [ searchList error ]");
			e.printStackTrace();
		} finally {
			cm.disconnect();
		}
		return list;
	}
}
