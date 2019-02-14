package com.xinhai.backSystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.LogService;
import com.xinhai.backSystem.service.SupplyTypeService;

/**
 * Servlet implementation class SupplyTypeController
 */
@WebServlet("/SupplyTypeController")
public class SupplyTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplyTypeController() {
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
		String type;
		String typeName;
		String userId;
		String id;
		String keywords;
		try {
			if(null==request.getSession().getAttribute("userId")){
				//无法获取用户userId 用户Session已经失效,返回登入界面
				//重定向至登入页面
				 response.sendRedirect(request.getContextPath() + "/project/login/login.jsp");  
			}
			else{
			 userId=request.getSession().getAttribute("userId").toString();
			System.out.println("当前的用户id："+userId);
			switch (methods) {
			case "supplyTypeData":
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				json = SupplyTypeService.SelectSupplyTypeData(Integer.parseInt(page), Integer.parseInt(limit));
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
				
				//SelectSupplyTypeDataBySelect
			case "supplyTypeDataBySelect":
				type=request.getParameter("type");
				typeName=request.getParameter("typeName");
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				json = SupplyTypeService.SelectSupplyTypeDataBySelect(Integer.parseInt(page), Integer.parseInt(limit),type,typeName);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
			case "addSupplyType":
				type=request.getParameter("type");
				typeName=request.getParameter("typeName");
				id=request.getParameter("id");
				if("-1".equals(id)){
				json = SupplyTypeService.AddSupplyType(type, typeName, userId);
				}
				else{
					json=SupplyTypeService.UpdateSupplyTypeData(type, typeName, userId, id);
				}
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			
			case "delSupplyType":
				id=request.getParameter("id");
				json=SupplyTypeService.DelSupplyType(userId,id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
//			case "updateSupplyType":
//				break;
			case "selectSupplyTypeDataByKeywords":
				 keywords=request.getParameter("keywords");
				json = SupplyTypeService.SelectSupplyTypeAllDataByMethods(keywords);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
			case "batchIncreaseSupplyType":
			break;	
				
				
			default:
				break;
			}
			}	
			} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
