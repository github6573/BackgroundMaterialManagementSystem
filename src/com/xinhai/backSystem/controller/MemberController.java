package com.xinhai.backSystem.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.MemberService;
import com.xinhai.backSystem.service.UserService;

import javafx.beans.binding.StringBinding;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
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
		String id=null;
		
		String name=null;
		String workNumber=null;
		String tel=null;
		String department=null;
		String userId=null;
		String type=null;
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
			case "memberData":
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				//id=request.getParameter("id");
				name=request.getParameter("name");
				workNumber=request.getParameter("workNumber");
				tel=request.getParameter("tel");
				department=request.getParameter("department");
				//System.out.println("name:"+name+"workNumber:"+workNumber+"tel:"+tel+"department:"+department+"page:"+page+"limit:"+limit);
				json = MemberService.SelectMemberData(Integer.parseInt(page), Integer.parseInt(limit),name,workNumber,tel,department);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "addMember":
				id=request.getParameter("id");
				name=request.getParameter("name");
				workNumber=request.getParameter("workNumber");
				tel=request.getParameter("tel");
				department=request.getParameter("department");
				if("-1".equals(id)==true){
					json = MemberService.AddMember(name,workNumber,tel,department,userId);	
				}
				else if("-1".equals(id)==false){
					json = MemberService.UpdateMember(name,workNumber,tel,department,userId,id);
				}
				else{
					json=MemberService.error();
				}
				//json = MemberService.AddMember(name,workNumber,tel,department,userId);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				case "getList":
					type=request.getParameter("type");
					//System.out.println("type:"+type);
					json = MemberService.SearchListData(type);
					response.setContentType("text/javascript;charset=utf-8");
					response.setCharacterEncoding("utf-8");
					response.getWriter().print(json);					
			    break;
				case "delmember":
					id=request.getParameter("id");
					json = MemberService.delMemberById(userId,id);
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
