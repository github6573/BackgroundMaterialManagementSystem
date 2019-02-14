package com.xinhai.backSystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.SuppliesService;
import com.xinhai.backSystem.service.TouristService;

/**
 * Servlet implementation class TouristController
 */
@WebServlet("/TouristController")
public class TouristController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TouristController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
		String suppliesName=null;
		String page=null;
		String limit=null;
		JSONObject json;
		
		suppliesName=request.getParameter("suppliesName");
		 System.out.println("nameSelect:"+suppliesName);
		page = request.getParameter("page");
		limit = request.getParameter("limit");		
			json = TouristService.SearchTouristData(Integer.parseInt(page), Integer.parseInt(limit), suppliesName);
			response.setContentType("text/javascript;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}

}
