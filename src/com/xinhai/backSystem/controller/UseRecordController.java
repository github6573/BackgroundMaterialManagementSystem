package com.xinhai.backSystem.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.DictionaryService;
import com.xinhai.backSystem.service.MemberService;
import com.xinhai.backSystem.service.SuppliesService;
import com.xinhai.backSystem.service.UseRecordService;
import com.xinhai.backSystem.service.UserService;

import javafx.beans.binding.StringBinding;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/UseRecordController")

public class UseRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UseRecordController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methods= request.getParameter("methods");
		String page;
		String limit;
		JSONObject json = null;

		String userId=null;
		String id=null;
		String type=null;
		String value=null;
		String name=null;
		String note=null;
		String TimeScope=null;
		String number=null;
		String unit=null;
		String nameId=null;
		String nameSelect=null;
		request.setCharacterEncoding("utf-8");
		try {
			if(null==request.getSession().getAttribute("userId")){
				//无法获取用户userId 用户Session已经失效,返回登入界面
				//重定向至登入页面
				 response.sendRedirect(request.getContextPath() + "/project/login/login.jsp");  
			}
			else{
				userId=request.getSession().getAttribute("userId").toString();
			
			switch (methods) {
			case "UserRecordData":
				
				
				name=request.getParameter("name");
				userId=request.getParameter("userId");
				TimeScope=request.getParameter("TimeScope");
				System.out.println("name:"+name+" userId:"+userId+" TimeScope:"+TimeScope);
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				
				json = UseRecordService.SearchUseRecord(Integer.parseInt(page), Integer.parseInt(limit),name,userId,TimeScope);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "delUseRecord":
				id=request.getParameter("id");
				json=UseRecordService.DelUseRecord(userId,id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
				
			case "getMaterialNameList":
				
				
				type=request.getParameter("type");
				json = UseRecordService.SearchMaterialUseRecordNameList();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);					
		    break;
			case "addUseRecord":
				id=request.getParameter("id");
				nameSelect=request.getParameter("nameSelect");
				number=request.getParameter("number");
				unit=request.getParameter("unit");
				nameId=request.getParameter("userSelect");
				note=request.getParameter("note");
				if("-1".equals(id)==true){
					json = UseRecordService.AddUseRecord(nameSelect,number,unit,nameId,note,userId);	
				}
				else if("-1".equals(id)==false){
					json = UseRecordService.UpdateUseRecord(nameSelect,number,unit,nameId,note,userId,id);
				}
				else{
					//
					json=DictionaryService.error();
				}
				//json = MemberService.AddMember(name,workNumber,tel,department,userId);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "getGroupList":
				json = UseRecordService.GetGroupList();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				
				
				break;
			
				default:
				break;
			}
			
			}} catch (Exception e) {
		e.printStackTrace();
		}
	}

}
