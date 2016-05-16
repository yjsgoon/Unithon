package com.conting.service;

import java.util.List;

import com.conting.dao.ChatDAO;
import com.conting.vo.ChatVO;

/**
 * 
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */
public class ChatService {
	private ChatDAO dao = new ChatDAO();
	
	public boolean create(ChatVO vo) throws Exception {
		return dao.create(vo);
	}
	
	public List<ChatVO> chatList(ChatVO vo) throws Exception {
		return dao.chatList(vo);
	}
}
