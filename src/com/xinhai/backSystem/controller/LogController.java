package com.xinhai.backSystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.LogService;


/**
 * Servlet implementation class LogController
 */
@WebServlet("/LogController")
public class LogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogController() {
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
	String methods= request.getParameter("methods");
	String page;
	String limit;
	JSONObject json;
	try {
		
		
		switch (methods) {
		case "logData":
			page = request.getParameter("page");
			limit = request.getParameter("limit");
			json = LogService.SearchMainLogData(Integer.parseInt(page), Integer.parseInt(limit));
			response.setContentType("text/javascript;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
			break;

		default:
			break;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	}

}
