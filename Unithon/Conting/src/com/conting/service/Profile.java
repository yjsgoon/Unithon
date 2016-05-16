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
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class name
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerService service = new CustomerService();
    private CustomerVO vo = new CustomerVO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		if (session.getAttribute("profileName") != null) {
			jsonObject.put("code", 200);
			jsonObject.put("name", session.getAttribute("profileName"));
		} else {
			jsonObject.put("code", 401);
			jsonObject.put("msg", "session expiration");
		}
		
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin" , "*");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		try {
			vo.setProfileName(request.getParameter("profileName"));
			if(service.search(vo.getProfileName()) != null) {
				jsonObject.put("code", 401);
				jsonObject.put("msg", "name duplication");
			} else {
				session.setAttribute("profileName", vo.getProfileName());
				service.create(vo);
				jsonObject.put("code", 200);
				jsonObject.put("msg", null);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.println(jsonObject.toString());
			out.flush();
			out.close();
		}
	}
}
