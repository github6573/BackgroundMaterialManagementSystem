package com.xinhai.backSystem.commom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xinhai.backSystem.dao.SuppliesDAO;
import com.xinhai.backSystem.dao.UseRecordDAO;
import com.xinhai.backSystem.entity.DictionaryEntity;
import com.xinhai.backSystem.entity.SuppliesInfo;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;

/**
* @author Tony
* @version 创建时间：2018年11月6日 下午3:55:43
* @ClassName 类名称
* @Description 类描述
*/
public class WriteExcel {
//	  public static void createExcelTest()  throws Exception{
//		  CreateExcel createExcel = new CreateExcel();
//		  String path = "C:\\Users\\dell\\Desktop\\test.xlsx";//路径可随意替换 
//		  try { //随意创建一个Excel
//          createExcel.createExcel(path); //读取上一行创建的Excel
//          createExcel.getExcel(path); System.out.println("----------我是分割线----------"); //创建Excel的表头
//          createExcel.createExcelTop(path); //读取上一行创建的Excel
//          createExcel.getExcel(path); } catch (Exception e) { e.printStackTrace(); }
//
//		  
//		  
//		  
//	  }
//	//删除单个excel文件  提供路径
//	 public static void delExcelTest(String NewfileName)  throws Exception{
//		 String path="D:/workspace/BackgroundMaterialManagementSystem/WebContent/Excel";
//		 java.io.File file = new java.io.File(path,NewfileName); //本地
//		 file.delete(); 
//	 }
//
	
	
	
	
//	  public static String MakeExcelTestAll(String tempPath)  throws Exception{
//			List<DictionaryEntity> groupLessonList= new ArrayList<DictionaryEntity>();
//			groupLessonList=UseRecordDAO.GetGroupList();
//			
//			 for (int i = 0; i < groupLessonList.size(); i++) {
//				 String groupName=groupLessonList.get(i).getName();
//				 int groupNameValue=groupLessonList.get(i).getValue();
//				//第一步，创建一个workbook对应一个excel文件
//			       HSSFWorkbook workbook = new HSSFWorkbook();
//			     //第二部，在workbook中创建一个sheet对应excel中的sheet
//			      HSSFSheet sheet = workbook.createSheet(groupName);
//			      //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
//			      
//			        HSSFRow row = sheet.createRow(0);
//			        HSSFCell cell = row.createCell(0);
//			        cell.setCellValue("序号");
//			        cell = row.createCell(1);
//			        cell.setCellValue("名称");
//			        cell =row.createCell(2);
//			        cell.setCellValue("型号");
//			        cell=row.createCell(3);
//			        cell.setCellValue("收到日期");
//			        cell=row.createCell(4);
//			        cell.setCellValue("来源");
//			        cell=row.createCell(5);
//			        cell.setCellValue("摘要");
//			        cell=row.createCell(6);
//			        cell.setCellValue("单位");
//			        cell=row.createCell(7);
//			        cell.setCellValue("数量");
//			        cell=row.createCell(8);
//			        cell.setCellValue("单价");
//			        cell=row.createCell(9);
//			        cell.setCellValue("金额（含税）");
//			        cell=row.createCell(10);
//			        cell.setCellValue("位置");
//			        cell=row.createCell(11);
//			        cell.setCellValue("使用人");
//			        cell=row.createCell(12);
//			        cell.setCellValue("备注");
//			        List<SuppliesInfo> sulessonList= new ArrayList<SuppliesInfo>();
//					 sulessonList=SuppliesDAO.SelectSuppliesDataByGroupId(groupNameValue);
//					 //写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
//				       // List<User> users = getUsers();
//				        for (int j = 0; j < sulessonList.size(); j++) {
//				            HSSFRow row1 = sheet.createRow(j + 1);
//				            SupplyUserRecordInfo lessonList=sulessonList.get(j);
//				            //User user = users.get(i);
//				            //创建单元格设值
//				            row1.createCell(0).setCellValue(lessonList.getDepartment());
//				            row1.createCell(1).setCellValue(lessonList.getUserName());
//				            row1.createCell(2).setCellValue(lessonList.getSupplyTypeName());
//				            row1.createCell(3).setCellValue(lessonList.getModel());
//				            row1.createCell(4).setCellValue(lessonList.getPhysicalAddress());
//				            row1.createCell(5).setCellValue(lessonList.getNote1());
//				            row1.createCell(6).setCellValue(lessonList.getNote2());
//				            
//				        }
//				        
//			        
//			 }
//		  
//			
//	        
//	     
//	      //将文件保存到指定的位置
//	        try {
//	        	
//	        	
//	        	String fileName = "UserRecorditem";
//	        //	 NewfileName=fileName+System.currentTimeMillis();//生成新文件名字
//	        	 NewfileName=fileName;
//	        	 //生成新地址
//	       // 	path="D:\\workspace\\BackgroundMaterialManagementSystem\\WebContent\\project\\outExcel\\"+NewfileName+".xls";//本地
//	        	path=tempPath+"/UserRecorditem.xls";
//	       // 	path="http://111.231.206.138:18080/BackgroundMaterialManagementSystem/project/outExcel/UserRecorditem.xls";      //服务器
//	        	//new File(tempPath,NewfileName)
//	        	FileOutputStream fos = new FileOutputStream(path);
//	            workbook.write(fos);
//	            System.out.println("写入成功");
//	            fos.close();
//	            workbook.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//			return path;
//
//	  }
	  
	  
	  
	
	  public static String MakeExcelTest(List<SupplyUserRecordInfo> lessonLists,String tempPath)  throws Exception{
		  String NewfileName="";
		  String path=null;
		  //第一步，创建一个workbook对应一个excel文件
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        //第二部，在workbook中创建一个sheet对应excel中的sheet
	        HSSFSheet sheet = workbook.createSheet("用户表一");
	        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
	        HSSFRow row = sheet.createRow(0);
	        //第四步，创建单元格，设置表头
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("部门");
	        cell = row.createCell(1);
	        cell.setCellValue("使用者");
	        cell =row.createCell(2);
	        cell.setCellValue("物资名称");
	        cell=row.createCell(3);
	        cell.setCellValue("型号");
	        cell=row.createCell(4);
	        cell.setCellValue("物理地址");
	        cell=row.createCell(5);
	        cell.setCellValue("备注一");
	        cell=row.createCell(6);
	        cell.setCellValue("备注二");
	        
	      //写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
	       // List<User> users = getUsers();
	        for (int i = 0; i < lessonLists.size(); i++) {
	            HSSFRow row1 = sheet.createRow(i + 1);
	            SupplyUserRecordInfo lessonList=lessonLists.get(i);
	            //User user = users.get(i);
	            //创建单元格设值
	            row1.createCell(0).setCellValue(lessonList.getDepartment());
	            row1.createCell(1).setCellValue(lessonList.getUserName());
	            row1.createCell(2).setCellValue(lessonList.getSupplyTypeName());
	            row1.createCell(3).setCellValue(lessonList.getModel());
	            row1.createCell(4).setCellValue(lessonList.getPhysicalAddress());
	            row1.createCell(5).setCellValue(lessonList.getNote1());
	            row1.createCell(6).setCellValue(lessonList.getNote2());
	            
	        }
	        
	      //将文件保存到指定的位置
	        try {
	        	
	        	
	        	String fileName = "UserRecorditem";
	        //	 NewfileName=fileName+System.currentTimeMillis();//生成新文件名字
	        	 NewfileName=fileName;
	        	 //生成新地址
	       // 	path="D:\\workspace\\BackgroundMaterialManagementSystem\\WebContent\\project\\outExcel\\"+NewfileName+".xls";//本地
	        	path=tempPath+"/UserRecorditem.xls";
	       // 	path="http://111.231.206.138:18080/BackgroundMaterialManagementSystem/project/outExcel/UserRecorditem.xls";      //服务器
	        	//new File(tempPath,NewfileName)
	        	FileOutputStream fos = new FileOutputStream(path);
	            workbook.write(fos);
	            System.out.println("写入成功");
	            fos.close();
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return path;

	  } 
	  
	  
	  
	public static void main(String[] args) {
		
//		List<SupplyUserRecordInfo> lessonList= new ArrayList<SupplyUserRecordInfo>();
//		try {
//			lessonList=SupplyUserRecordDAO.SelectSupplyUserRecordData(1, 15);
//			String  path=MakeExcelTest(lessonList);
//			System.out.println("path:"+path);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
}
