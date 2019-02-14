package com.xinhai.backSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.backSystem.entity.UserInfo;
import com.xinhai.backSystem.service.LogService;
import com.xinhai.backSystem.service.LoginService;

//import com.xinhai.eightTwo.entity.UserInfo;
//import com.xinhai.eightTwo.service.LogService;
//import com.xinhai.eightTwo.service.LoginService;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          //登入 登出的逻辑判断 
		System.out.println("进入");
		request.setCharacterEncoding("utf-8");
		try{
			String methods=request.getParameter("methods");
			switch (methods) {
			case "login":
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				System.out.println("username"+username+" password:"+password);
				List<UserInfo> lessonList= new ArrayList<UserInfo>();
				lessonList=LoginService.LoginValidation(username, password);
				if(null==lessonList||("[]").equals(lessonList)||lessonList.size()==0){
					//	System.out.println("1231");
					//	response.sendRedirect(request.getContextPath()+"/projectFolder/login/login.jsp");
						request.getRequestDispatcher("project/login/login.jsp").forward(request, response);	
					}
				else{
					request.getSession().setAttribute("userId", lessonList.get(0).getId());
					System.out.println("本次写入的id为"+lessonList.get(0).getId());
					int userId=lessonList.get(0).getId();
					LogService.Addlog(userId+"", "登入");
					request.setAttribute("permissions", lessonList.get(0).getPermissions());
					request.setAttribute("userId", lessonList.get(0).getId());
					request.setAttribute("userName", lessonList.get(0).getUserName());
					request.setAttribute("userWorkNumber", lessonList.get(0).getWorkNumber());
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				}
				break;
			case "logout":
				if(null!=request.getSession().getAttribute("userId")){
					System.out.println("userId 存在");
				String userid=request.getSession().getAttribute("userId").toString();
				System.out.println("userid: "+userid);
				request.getSession().removeAttribute("userId");
				if(null==request.getSession().getAttribute("userId")){
					System.out.println("现在的userid:空 ");
				}
				else{
					String useridnow=request.getSession().getAttribute("userId").toString();
					System.out.println("现在的userid: "+useridnow);
				}
				}
				else{
					System.out.println("userId 不存在,执行退出跳转1");
				}
				 response.sendRedirect(request.getContextPath() + "/project/login/login.jsp");  
				break;
//			case "longTimelogout":
//				System.out.println("longTimelogout");
//				request.setAttribute("msg","Session失效,请重新登入");
//				request.getRequestDispatcher("/project/login/login.jsp").forward(request, response);	
//				break;
			default:
				break;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
