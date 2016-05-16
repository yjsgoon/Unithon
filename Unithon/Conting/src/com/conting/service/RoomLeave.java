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

/**
 * 
 * @since 	2016. 2. 14.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class RoomLeave
 */
@WebServlet("/roomleave")
public class RoomLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CustomerVO vo = new CustomerVO();
    CustomerService service = new CustomerService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomLeave() {
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
			vo.setProfileName(session.getAttribute("profileName").toString());
			vo.setVisit(null);
			if(service.update(vo)) {
				jsonObject.put("code", 210);
				jsonObject.put("msg", null);
			} else {
				jsonObject.put("code", 410);
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
		doGet(request, response);
	}

}
