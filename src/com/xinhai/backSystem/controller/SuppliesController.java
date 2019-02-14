package com.xinhai.backSystem.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.service.SuppliesService;
import com.xinhai.backSystem.service.SupplyService;
import com.xinhai.backSystem.service.SupplyTypeService;

/**
 * Servlet implementation class SupplyController
 */
@WebServlet("/SuppliesController")
public class SuppliesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppliesController() {
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
		String userId;
		String typeName;
		String model;
		String number;
		String note=null;
		String id;
		String type;
		String keywords;
		String serialNumber=null;
		String suppliesName=null;
		String receiveTime=null;
		String source=null;
		String digest=null;
		String unit=null;
		String price=null;
		String money=null;
		String place=null;
		String userName=null;
		String groupName=null;
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
			case "suppliesData":
				String nameSelect=request.getParameter("nameSelect");
				String groupIdName=request.getParameter("groupIdName");
				System.out.println("nameSelect:"+nameSelect+"groupIdName:"+groupIdName);
				page = request.getParameter("page");
				limit = request.getParameter("limit");
				json = SuppliesService.SelectSuppliesData(Integer.parseInt(page), Integer.parseInt(limit), nameSelect,groupIdName);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
				
		
			case "addSupplies":
				 suppliesName=request.getParameter("suppliesName");
				 model=request.getParameter("model");
				 receiveTime=request.getParameter("receiveTime");
				 
				// System.out.println("receiveTime:"+receiveTime);
				 source=request.getParameter("source");
				 digest=request.getParameter("digest");
				 number=request.getParameter("number");
				 unit=request.getParameter("unit");
				 price=request.getParameter("price");
				 money=request.getParameter("money");
				 place=request.getParameter("place");
				 userName=request.getParameter("userName");
				 groupName=request.getParameter("groupNameSelect");
				 id=request.getParameter("id");
				 note=request.getParameter("note");
				
				if("-1".equals(id)){
					System.out.println("新增");
					json = SuppliesService.AddSupplies( suppliesName, model, receiveTime, source,  digest, number,  unit, price, money, place, userName, note, groupName, userId);
				}
				else{
					
					System.out.println("更新" +"groupName:"+groupName);
					json=SuppliesService.UpdateSuppliesData(suppliesName, model, receiveTime, source,  digest, number,  unit, price, money, place, userName, note, groupName, userId,id);
				}
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;
			
			case "delSupplies":
				id=request.getParameter("id");
				json=SuppliesService.DelSupplies(userId,id);
				response.setContentType("text/javascript;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				break;	
//			case "delSupply":
//				id=request.getParameter("id");
//				json=SupplyService.DelSupply(userId,id);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;	
//			case "supplyDataBySelect":
//				page = request.getParameter("page");
//				limit = request.getParameter("limit");
//				type=request.getParameter("type");
//				typeName=request.getParameter("typeName");
//				model=request.getParameter("model");
//				json = SupplyService.SelectSupplyDataBySelect(Integer.parseInt(page), Integer.parseInt(limit),type,typeName,model);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;
//			case "SelectSupplyAllDataByKeywords":
//				keywords=request.getParameter("keywords");
//				json = SupplyService.SelectSupplyAllDataByMethods(keywords);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;
//				//SelectSupplyAllDataByMethods
//			case "selectSupplytypeNameDataByType":
//				type=request.getParameter("type");
//				json = SupplyService.selectSupplytypeNameDataByType(type);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;
//			case "selectSupplyNameDataByType":
//				type=request.getParameter("type");
//				json = SupplyService.selectSupplyNameDataByType(type);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;
//			//selectSupplyModelDataByType	
//			case "selectSupplyModelDataByTypeName":
//				typeName=request.getParameter("typeName");
//				json = SupplyService.selectSupplyModelDataByTypeName(typeName);
//				response.setContentType("text/javascript;charset=utf-8");
//				response.setCharacterEncoding("utf-8");
//				response.getWriter().print(json);
//				break;	
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
