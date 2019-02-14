package com.xinhai.backSystem.commom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinhai.backSystem.dao.DictionaryDAO;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;


/**
* @author Tony
* @version 创建时间：2018年9月29日 上午10:54:56
* @ClassName 类名称
* @Description 类描述
*/
public class Splicing {
	//最新的拼接sql方法SqlCreateMainWz
	  public static String SqlCreateMainWz(String[] arr11,String Stringsql,String  userIdnow,String groupNameId)  throws Exception{
		  String newStringsql="";
		  Timestamp now = new Timestamp(System.currentTimeMillis()); 
		  //(name, supplier,model,unit,number,userName,state,position,note,groupId,createTime,createId)
		  newStringsql=Stringsql+"('"+arr11[1]+"','"+arr11[2]+"','"+arr11[3]+"','"+arr11[4]+"','"+arr11[5]+"','"+arr11[6]+"','"+arr11[7]+"','"+arr11[8]+"','"+arr11[9]+"'"
		  		+ ",'"+groupNameId+"','"+now+"','"+userIdnow+"'),";
		  return newStringsql;
	  }
	
	//SplicingMainWzData  新模板拼接过程
	  public static String[] SplicingMainWzData(String columns,String Data,String[] arr11)  throws Exception{
		  //	int dictValue=0;
			 if(null!=Data){ 
			  switch(columns){
			//  String columns[] = {"serialNumber","name","supplier","model","unit","number","userName","state","position","note"};
			  case "serialNumber":
				  arr11[0]=Data;
			  break;
			  case "name":
				  arr11[1]=Data;
				  break;
			  case "supplier":
				  arr11[2]=Data;
				  break;
			  case "model":
					arr11[3]=Data;
				  break;  
			  case "unit":
				  arr11[4]=Data;
				  break; 
			  case "number":
				  arr11[5]=Data;
				  break; 
			  case "userName":
				  arr11[6]=Data;
				  break; 
			  case "state":
				  if("正常使用".equals(Data))
				  {
					arr11[7]=DictionaryDAO.SearchDictionaryDataByName("正常使用");
					}
				  else if("库存".equals(Data))
				  {
					arr11[7]=DictionaryDAO.SearchDictionaryDataByName("库存");
					}
				  else{
					  arr11[7]=DictionaryDAO.SearchDictionaryDataByName("非法状态");;
				  }
				  break; 
			  case "position":
				  arr11[8]=Data;
				  break; 
			  case "note":
				  arr11[9]=Data;
				  break; 	  
			  default:
				  break;
			 
			  }
			 }
			  //String newstr="";
			  return arr11;
		  }
	
	  public static String[] SplicingData(String columns,String Data,String[] arr13)  throws Exception{
		//  String[] arr4 =  new String[4];
		 if(null!=Data){ 
		  switch(columns){
	     //   String columns[] = {"serialNumber","name","model","receiveTime","source","digest","unit","number","price","money","place","userName","note"};
		  case "serialNumber":
			  arr13[0]=Data;
		  break;
		  case "name":
			  arr13[1]=Data;
			  break;
		  case "model":
			  arr13[2]=Data;
			  break;
		  case "receiveTime":
			  if("".equals(Data))
			  {
				arr13[3]=null;
				}
			  else{arr13[3]=Data;}
			  break;  
		  case "source":
			  arr13[4]=Data;
			  break; 
		  case "digest":
			  arr13[5]=Data;
			  break; 
		  case "unit":
			  arr13[6]=Data;
			  break; 
		  case "number":
			  arr13[7]=Data;
			  break; 
		  case "price":
			  arr13[8]=Data;
			  break; 
		  case "money":
			  arr13[9]=Data;
			  break; 
		  case "place":
			  arr13[10]=Data;
			  break; 
		  case "userName":
			  arr13[11]=Data;
			  break; 
		  case "note":
			  arr13[12]=Data;
			  break; 
		  default:
			  break;
		 
		  }
		 }
		  //String newstr="";
		  return arr13;
	  }
	  
	  
	  
	  public static String[] SplicingSupplyData(String columns,String Data,String[] arr4)  throws Exception{
			 if(null!=Data){ 
			  switch(columns){
			  case "type":
				  arr4[0]=Data;
			  break;
			  case "typeName":
				  arr4[1]=Data;
				  break;
			  case "model":
				  arr4[2]=Data;
				  break;
			  case "number":
				  arr4[3]=Data;
				  break;
			  }
			 }
			  
			  return arr4;
		  }
	  
	  
	  public static String[] SplicingSupplyUserRecordData(String columns,String Data,String[] arr7)  throws Exception{
			// {"department","userName","typeName","model","physicalAddress","note1","note2"};
		  if(null!=Data){ 
			  switch(columns){
			  case "department":
				  arr7[0]=Data;
			  break;
			  case "userName":
				  arr7[1]=Data;
				  break;
			  case "typeName":
				  arr7[2]=Data;
				  break;
			  case "model":
				  arr7[3]=Data;
				  break;
			  case "physicalAddress":
				  arr7[4]=Data;
				  break;
			  case "note1":
				  arr7[5]=Data;
				  break;
			  case "note2":
				  arr7[6]=Data;
				  break;
				  
//			  case "state":
//				  if("null".equals(Data)){
//					  arr6[5]="";  
//				  }
//				  
//				  arr6[5]=Data;
//				  break;
			  }
			 }
			  
			  return arr7;
		  }
	  
	  public static String SqlCreate(String[] arr13,String Stringsql,String  userIdnow,String groupNameId)  throws Exception{
		  String newStringsql="";
		  Timestamp now = new Timestamp(System.currentTimeMillis()); 
		 // String receiveTime=null;
		//System.out.println("dd groupNameId:"+groupNameId);
		  if(null==arr13[3]){
			  //收到地址为空
			  newStringsql=Stringsql+"('"+arr13[1]+"','"+arr13[2]+"',"+arr13[3]+",'"+arr13[4]+"','"+arr13[5]+"','"+arr13[6]+"','"+arr13[7]+"','"+arr13[7]+"','"+arr13[8]+"','"+arr13[9]+"','"+arr13[10]+"','"+arr13[11]+"','"+arr13[12]+"','"+now+"','"+userIdnow+"','"+groupNameId+"',1),";
			  
		  }
		  else{
		  newStringsql=Stringsql+"('"+arr13[1]+"','"+arr13[2]+"','"+arr13[3]+"','"+arr13[4]+"','"+arr13[5]+"','"+arr13[6]+"','"+arr13[7]+"','"+arr13[8]+"','"+arr13[9]+"','"+arr13[10]+"','"+arr13[11]+"','"+arr13[12]+"','"+now+"','"+userIdnow+"','"+groupNameId+"',1),";
		  }
		  return newStringsql;
		  

	  }
	  
	  public static String SqlCreateSupply(String[] arr4,String Stringsql,String userIdnow)  throws Exception{
		  String newStringsql="";
		  Timestamp now = new Timestamp(System.currentTimeMillis()); 	
		  newStringsql=Stringsql+"('"+arr4[0]+"','"+arr4[1]+"','"+arr4[2]+"','"+arr4[3]+"','"+now+"',"+userIdnow+",1),";
		  return newStringsql;
		  

	  }
	  
	//SqlCreateSupplyUserRecord  
	  public static String SqlCreateSupplyUserRecord(String[] arr7,String Stringsql,String userIdnow)  throws Exception{
		  String newStringsql="";
		  Timestamp now = new Timestamp(System.currentTimeMillis()); 	
		 
		   if("null".equals(arr7[5])==true){
			 // System.out.println("字符串空");
			  arr7[5]=""; 
		  }
		   
		   if("null".equals(arr7[6])==true){
				 // System.out.println("字符串空");
				  arr7[6]=""; 
			  }
		  newStringsql=Stringsql+"('"+arr7[0]+"','"+arr7[1]+"','"+arr7[2]+"','"+arr7[3]+"','"+arr7[4]+"','"+arr7[5]+"','"+arr7[6]+"','"+now+"',"+userIdnow+",1),";
		  return newStringsql;
		  

	  }
	  
	  //List<SupplyTypeInfo> morelessonList= new ArrayList<SupplyTypeInfo>();
	  //验重
	  public static  List<SupplyTypeInfo>  yanchong( List<SupplyTypeInfo> lessonList,String str)  throws Exception{
		 // List<MouldInfo> lessonList = new ArrayList<MouldInfo>();
		 // lessonList=MouldDAO.SearchAllDrawingNoMachinedPartNameData();
		  SupplyTypeInfo st = new SupplyTypeInfo();
		  st.setTypeName(str);
		  lessonList.add(st);
		return lessonList;
	  }
	  
	  
	  
	  public static  List<SupplyInfo>  yanchongSupply( List<SupplyInfo> lessonList,String str1,String str2)  throws Exception{
			  SupplyInfo si = new SupplyInfo();
			  si.setModel(str1+str2);
			  lessonList.add(si);
			return lessonList;
		  }
	  public static  List<SupplyUserRecordInfo>  yanchongSupplyUserRecord( List<SupplyUserRecordInfo> lessonList,String str1,String str2,String str3,String str4 ,String str5,String str6)  throws Exception{
		  SupplyUserRecordInfo surr = new SupplyUserRecordInfo();
		  surr.setModel(str1+str2+str3+str4+str5+str6);
			  lessonList.add(surr);
			return lessonList;
		  }
	//  List<MouldInfo> lessonList = new ArrayList<MouldInfo>();
//		lessonList=SearchAllDrawingNoMachinedPartNameData();
}
