package com.conting.service;

import java.util.List;

import com.conting.dao.RoomDAO;
import com.conting.vo.RoomVO;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class RoomService {
	RoomDAO dao = new RoomDAO();
	
	public boolean create(RoomVO vo) throws Exception {
		return dao.create(vo);
	}
	
	public boolean delete(String title) throws Exception {
		return dao.delete(title);
	}
	
	public boolean lock(RoomVO vo) throws Exception {
		return dao.lock(vo);
	}
	
	public RoomVO search(String title) throws Exception {
		return dao.search(title);
	}
	
	public List<RoomVO> searchList() throws Exception {
		return dao.searchList();
	}
}
