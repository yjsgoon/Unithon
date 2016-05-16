package com.conting.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.conting.vo.CustomerVO;
import com.conting.vo.RoomVO;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class Room
 */
@WebServlet("/roomcreate")
public class RoomCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerVO customerVO = new CustomerVO();
	CustomerService customerService = new CustomerService();
    RoomVO roomVO = new RoomVO();
    RoomService roomService = new RoomService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin" , "*");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		HttpSession session = request.getSession();
		
		try {
			customerVO.setProfileName(request.getParameter("profileName"));
			//customerVO.setProfileName(session.getAttribute("profileName").toString());
			customerVO.setVisit(request.getParameter("title"));
			customerVO.setChief(true);
			customerService.update(customerVO);
			
			roomVO.setTitle(request.getParameter("title"));
			roomVO.setCapacity(Integer.parseInt(request.getParameter("capacity")));
			roomVO.setPurpose(Integer.parseInt(request.getParameter("purpose")));
			//roomVO.setBeginTime(null);//vo.setBeginTime(request.getParameter("beginTime"));
			//roomVO.setEndTime(null);//vo.setEndTime(request.getParameter("endTime"));
			roomVO.setBeginTime(Integer.parseInt(request.getParameter("beginTime")));
			roomVO.setEndTime(Integer.parseInt(request.getParameter("endTime")));
			
			if(roomService.search(roomVO.getTitle()) != null) {
				jsonObject.put("code", 402);
				jsonObject.put("msg", "title duplication");
			} else {
				roomService.create(roomVO);
				jsonObject.put("code", 202);
				jsonObject.put("msg", "create success");
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
		doGet(request, response);
	}

}
