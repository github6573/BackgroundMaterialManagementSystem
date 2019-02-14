package com.xinhai.backSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.commom.WriteExcel;
import com.xinhai.backSystem.dao.SupplyUserRecordDAO;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;
import com.xinhai.backSystem.service.SupplyTypeService;
import com.xinhai.backSystem.service.SupplyUserRecordService;

/**
 * Servlet implementation class SupplyUseRecordController
 */
@WebServlet("/SupplyUseRecordController")
public class SupplyUseRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplyUseRecordController() {
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
		String page=null;
		String limit=null;
		JSONObject json;
		String userId=null;
		String id=null;
		String department=null;
		String userName=null;
	//	String	supplyType=null;
		String	supplyTypeName=null;
		String	model=null;
	//	String	note=null;
		String keywords;
		String physicalAddress=null;
		String note1=null;
		String note2=null;
		String path=null;
		try {
			if(null==request.getSession().getAttribute("userId")){
				//无法获取用户userId 用户Session已经失效,返回登入界面
				//重定向至登入页面
				 response.sendRedirect(request.getContextPath() + "/project/login/login.jsp");  
			}
			else{
			 userId=request.getSession().getAttribute("userId").toString();
			switch (methods) {
			case "supplyUserRecordData":
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				json = SupplyUserRecordService.SelectSupplyUserRecordData(Integer.parseInt(page), Integer.parseInt(limit));
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
			case "addSupplyUserRecordData":
				department=request.getParameter("department");
				userName=request.getParameter("userName");
			//	supplyType=request.getParameter("supplyType");
				supplyTypeName=request.getParameter("supplyTypeName");
				model=request.getParameter("model");
				physicalAddress=request.getParameter("physicalAddress");
				note1=request.getParameter("note1");
				note2=request.getParameter("note2");
				id=request.getParameter("id");
				if("-1".equals(id)){
				json = SupplyUserRecordService.AddSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2);		
						}
				else{
					json=SupplyUserRecordService.UpdateSupplyUserRecordData( department, userName, supplyTypeName, model, userId, physicalAddress, note1, note2, id);
				}
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "delSupplyUserRecord":
				id=request.getParameter("id");
				json=SupplyUserRecordService.DelSupplyUserRecordData(userId, id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
			case "selectSupplyUserRecordByKeywords":
				 keywords=request.getParameter("keywords");
				json = SupplyUserRecordService.SelectSupplyUserRecordByKeywords(keywords);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
			case "supplyUserRecordDataBySelect":
				page=request.getParameter("page");
				limit=request.getParameter("limit");
				department=request.getParameter("department");
				userName=request.getParameter("userName");
			//	supplyType=request.getParameter("supplyType");
				supplyTypeName=request.getParameter("supplyTypeName");
				model=request.getParameter("model");
				System.out.println(page);
				System.out.println(limit);
				System.out.println(department);
				System.out.println(userName);
			//	System.out.println(supplyType);
				System.out.println(supplyTypeName);
				System.out.println(model);
				json = SupplyUserRecordService.supplyUserRecordDataBySelect(Integer.parseInt(page), Integer.parseInt(limit),department,userName,supplyTypeName,model);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				
				break;
			case "makeExcel":
				//path
				System.out.println("进入makeExcel");
				String tempPath = request.getServletContext().getRealPath("/project/outExcel"); 
				System.out.println("tempPath:"+tempPath);
				json = SupplyUserRecordService.getExcelpath(tempPath);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
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
