package com.conting.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.conting.vo.ChatVO;

/**
 * 
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class RoomChat
 */
@WebServlet("/roomchat")
public class RoomChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChatVO vo = new ChatVO();
    private List<ChatVO> list = new ArrayList();
    private ChatService service = new ChatService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomChat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin" , "*");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		try {
			vo.setTitle(request.getParameter("title"));
			vo.setChatTime(Integer.parseInt(request.getParameter("chatTime")));
			if(service.chatList(vo) != null) {
				list = service.chatList(vo);
				for(int i=0; i<list.size(); i++) {
					JSONObject temp = new JSONObject();
					temp.put("name", list.get(i).getProfileName());
					System.out.println(list.get(i).getProfileName());
					System.out.println(list.get(i).getChatContent());
					System.out.println(list.get(i).getProfileName());
					temp.put("chat", list.get(i).getChatContent());
					temp.put("time", list.get(i).getChatTime());
					jsonArray.add(temp);
				}
				jsonObject.put("code", 200);
				jsonObject.put("chatList", jsonArray);
			} else {
				jsonObject.put("code", 400);
				jsonObject.put("msg", "fail");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.print(jsonObject.toString());
			out.flush();
			out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		try {
			vo.setTitle(request.getParameter("title"));
			vo.setChatContent(request.getParameter("chatContent"));
			vo.setProfileName(session.getAttribute("profileName").toString());
			vo.setChatTime(Integer.parseInt(request.getParameter("chatTime")));
			
			service.create(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
