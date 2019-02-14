package com.xinhai.backSystem.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.MainWzService;
import com.xinhai.backSystem.service.SuppliesService;
import com.xinhai.backSystem.service.SupplyService;
import com.xinhai.backSystem.service.SupplyTypeService;

/**
 * Servlet implementation class SupplyController
 */
@WebServlet("/MainWzController")
public class MainWzController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainWzController() {
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
		JSONObject json = null;
		String userId;
		String name=null;
		String supplier=null;
		String model=null;
		String unit=null;
		String number=null;
		String userName=null;
		String state=null;
		String position=null;
		String note=null;
		String groupId=null;
		String id;
		
		String userValue=null;
		String nameValue=null;
		String groupIdValue=null;
		String positionValue=null;
//		String typeName;
//		String model;
//		String number;
//		String note=null;
//		String id;
//		String type;
//		String keywords;
//		String serialNumber=null;
//		String suppliesName=null;
//		String receiveTime=null;
//		String source=null;
//		String digest=null;
//		String unit=null;
//		String price=null;
//		String money=null;
//		String place=null;
//		String userName=null;
//		String groupName=null;
		try {
			System.out.println("进入");
			if(null==request.getSession().getAttribute("userId")){
				//无法获取用户userId 用户Session已经失效,返回登入界面
				//重定向至登入页面
				System.out.println("重定向至登入页面");
				 response.sendRedirect(request.getContextPath() + "/project/login/login.jsp");  
			}
			else{
				userId=request.getSession().getAttribute("userId").toString();
				System.out.println("当前的用户id："+userId);
			switch (methods) {
			case "mainWzData":
				 nameValue=request.getParameter("nameSelect");				
				 groupIdValue=request.getParameter("groupIdSelect");
				 positionValue=request.getParameter("positionSelect");
				 userValue=request.getParameter("userSelect");
				 System.out.println("nameSelect:"+nameValue+"groupIdName:"+groupIdValue+" position:"+positionValue+" userNameValue:"+userValue);
				 page = request.getParameter("page");
				 limit = request.getParameter("limit");
				 json = MainWzService.SearchMainWzDate(Integer.parseInt(page), Integer.parseInt(limit), nameValue,positionValue,groupIdValue,userValue);
				 System.out.println("json:"+json);
				 response.setContentType("text/javascript;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 response.getWriter().print(json);
				 break;
			case "addMainWz":
				 name=request.getParameter("name");
				 supplier=request.getParameter("supplier");
				 model=request.getParameter("model");
				 unit=request.getParameter("unit");
				 number=request.getParameter("number");
				 userName=request.getParameter("userName");
				 state=request.getParameter("stateSelect");
				 position=request.getParameter("positionSelect");
				 note=request.getParameter("note");
				 groupId=request.getParameter("groupNameSelect");
				 id=request.getParameter("id");
				System.out.println("name:"+name+" supplier:"+supplier+" model:"+model+"unit:"+unit+" number:"+number+" userName:"+userName+" state:"+state+" position:"+position+" note:"+note+" groupId:"+groupId+" id:"+id);
				if("-1".equals(id)){
					System.out.println("新增");
					json = MainWzService.AddMainWzData(name, supplier, model, unit,number, userName,Integer.parseInt(state), position, note, Integer.parseInt(groupId), userId);
				}
				else{
					System.out.println("更新" +"groupName:"+groupId);
					json=MainWzService.UpdateMainWzData(name, supplier, model, unit, number, userName, Integer.parseInt(state), position, note, Integer.parseInt(groupId), userId, id);
				}
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			
			case "delMainWz":
				id=request.getParameter("id");
				json=MainWzService.DelMainWzData(userId, id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
			case "nameList":
				id=request.getParameter("id");
				json=MainWzService.SearchMainWzDateAllName();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "UserList":
				id=request.getParameter("id");
				json=MainWzService.SearchUserList();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			case "DeleteMore":
				String uids = request.getParameter("WzId");
				//System.out.println("uids:"+uids);
				uids=uids.substring(1,uids.length()-1);
				//System.out.println("uids:"+uids);
				String[] uid = uids.split(",");
				for(int i=0;i<uid.length;i++){
				//System.out.println("uid["+i+"]="+uid[i]);
					MainWzService.DelMainWzData(userId, uid[i]);
					}
				json=MainWzService.Normal();
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
//				json=MainWzService.SearchUserList();
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
				break;
			default:
				break;
			}
			}	
		} catch (Exception e) {
		e.printStackTrace();
			//	// TODO: handle exception
		}
	
	}

}
