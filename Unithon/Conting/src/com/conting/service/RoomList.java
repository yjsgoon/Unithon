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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.conting.vo.RoomVO;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */

/**
 * Servlet implementation class RoomList
 */
@WebServlet("/roomlist")
public class RoomList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<RoomVO> list = new ArrayList<RoomVO>();
	RoomService service = new RoomService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomList() {
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
		JSONArray jsonArray = new JSONArray();
		
		try {
			if(service.searchList() != null) {
				list = service.searchList();
				for(int i=0; i<list.size(); i++) {
					JSONObject temp = new JSONObject();
					temp.put("title", list.get(i).getTitle());
					temp.put("capacity", list.get(i).getCapacity());
					temp.put("purpose", list.get(i).getPurpose());
					temp.put("beginTime", list.get(i).getBeginTime());
					temp.put("endTime", list.get(i).getEndTime());
					temp.put("lock", list.get(i).getLockDown());
					jsonArray.add(temp);
				}
				jsonObject.put("code", 204);
				jsonObject.put("roomList", jsonArray);
			} else {
				jsonObject.put("code", 404);
				jsonObject.put("msg", "not exist");
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
