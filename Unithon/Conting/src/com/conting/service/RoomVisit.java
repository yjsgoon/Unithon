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

import com.conting.vo.CustomerVO;
import com.conting.vo.RoomVO;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class RoomInsert
 */
@WebServlet("/roomvisit")
public class RoomVisit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerVO customerVO = new CustomerVO();
	private List<CustomerVO> list = new ArrayList<>();
	private CustomerService customerService = new CustomerService();
	private RoomVO roomVO = new RoomVO();
	private RoomService roomService = new RoomService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomVisit() {
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
		JSONArray jsonArray = new JSONArray();

		try {
			customerVO.setProfileName(session.getAttribute("profileName").toString());
			customerVO.setVisit(request.getParameter("visit"));
			customerService.update(customerVO);
			roomVO = roomService.search(customerVO.getVisit());
			if(roomVO.getLockDown() == 1) {
				if(customerService.searchVisitor(customerVO.getVisit()) != null) {
					list = customerService.searchVisitor(customerVO.getVisit());
					for(int i=0; i<list.size(); i++) {
						JSONObject temp = new JSONObject();
						temp.put("profileName", list.get(i).getProfileName());
						jsonArray.add(temp);
					}
					jsonObject.put("code", 206);
					jsonObject.put("roomVisitor", jsonArray);
				} else {
					jsonObject.put("code", 406);
					jsonObject.put("msg", "not exist");
				}
			} else {
				jsonObject.put("code", 407);
				jsonObject.put("msg", "the room was looked");
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
