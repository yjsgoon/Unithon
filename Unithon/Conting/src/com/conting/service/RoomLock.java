package com.conting.service;

import java.io.IOException;
import java.io.PrintWriter;

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
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class RoomLock
 */
@WebServlet("/roomlock")
public class RoomLock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerVO customerVO = new CustomerVO();
	private CustomerService customerService = new CustomerService();
    private RoomVO roomVO = new RoomVO();
    private RoomService roomService = new RoomService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomLock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin" , "*");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			customerVO = customerService.search(request.getParameter("profileName"));
			//customerVO = customerService.search(session.getAttribute("profileName").toString());
			if(customerVO.getChief()) {
				roomVO = roomService.search(customerVO.getVisit());
				roomVO.setLockDown(Integer.parseInt(request.getParameter("lock")));
				
				if(roomService.lock(roomVO)) {
					jsonObject.put("code", 212);
					jsonObject.put("msg", null);
				} else {
					jsonObject.put("code", 412);
					jsonObject.put("msg", "fail");
				}
			} else {
				jsonObject.put("code", 414);
				jsonObject.put("msg", "not chief");
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
