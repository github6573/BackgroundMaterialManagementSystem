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
import com.xinhai.backSystem.service.UserService;

import javafx.beans.binding.StringBinding;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/DictionaryController")
public class DictionaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DictionaryController() {
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
			case "dictionaryData":
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				
				
				
				json = DictionaryService.SearchDictionaryData(Integer.parseInt(page), Integer.parseInt(limit));
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "addDict":
				id=request.getParameter("id");
				type=request.getParameter("typeSelect");
				value=request.getParameter("value");
				name=request.getParameter("name");
				note=request.getParameter("note");
				//department=request.getParameter("department");
				if("-1".equals(id)==true){
					json = DictionaryService.AddDict(type,value,name,note,userId);	
				}
				else if("-1".equals(id)==false){
					json = DictionaryService.UpdateDict(type,value,name,note,userId,id);
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
			case "delDict":
				id=request.getParameter("id");
				json=DictionaryService.DelDict(userId,id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
				
//				case "getList":
//					type=request.getParameter("type");
//					//System.out.println("type:"+type);
//					json = MemberService.SearchListData(type);
//					response.setContentType("text/javascript;charset=utf-8");
//					response.setCharacterEncoding("utf-8");
//					response.getWriter().print(json);					
//			    break;
			case "getPositionList":
				json = DictionaryService.GetPositionList();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
			break;
			
			case "getTypeList":
				type=request.getParameter("type");
				json = DictionaryService.GetTypeList(type);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "dictType":
				json = DictionaryService.GetDictTypeList();
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
