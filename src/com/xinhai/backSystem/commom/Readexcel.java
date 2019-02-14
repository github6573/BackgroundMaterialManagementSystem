package com.xinhai.backSystem.commom;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.backSystem.entity.SupplyInfo;
import com.xinhai.backSystem.entity.SupplyTypeInfo;
import com.xinhai.backSystem.entity.SupplyUserRecordInfo;
import com.xinhai.backSystem.sql.MainWzSql;
import com.xinhai.backSystem.sql.SuppliesSql;
import com.xinhai.backSystem.sql.SupplySql;
import com.xinhai.backSystem.sql.SupplyTypeSql;
import com.xinhai.backSystem.sql.SupplyUserRecordSql;

/**
* @author Tony
* @version 创建时间：2018年9月28日 下午2:56:30
* @ClassName 类名称
* @Description 类描述
*/
public class Readexcel {
	public static JSONObject Read(String path,String userIdnow,String groupNameId) throws Exception{
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String newStringsql="";
        Map<String, Object> msgmap = new LinkedHashMap<String, Object>();
       // List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
        String filePath = path;
        JSONObject json=null;
        //System.out.println("key:"+key);
       // switch (key) {
//			 List<SupplyTypeInfo> lessonList= new ArrayList<SupplyTypeInfo>();
//			lessonList=SupplyTypeSql.SelectSupplyTypeAllDataByMethods("typeName");
//	        String columns[] = {"serialNumber","name","model","receiveTime","source","digest","unit","number","price","money","place","userName","note"};
	    //新模板上传字段    
        String columns[] = {"serialNumber","name","supplier","model","unit","number","userName","state","position","note"};
	        wb = readExcel(filePath);
	        if(wb != null){
	            //用来存放表中数据
	            list = new ArrayList<Map<String,String>>();
	            //获取第一个sheet
	            sheet = wb.getSheetAt(0);
	            //获取最大行数
	            int rownum = sheet.getPhysicalNumberOfRows();
	            //获取第4行
	            row = sheet.getRow(3);
	            //获取最大列数
	            int colnum = row.getPhysicalNumberOfCells();
	            for (int i = 3; i<rownum; i++) {
	                Map<String,String> map = new LinkedHashMap<String,String>();
	                row = sheet.getRow(i);
	                if(row !=null){
	                    for (int j=0;j<colnum;j++){
	                        cellData = (String) getCellFormatValue(row.getCell(j));
	                        map.put(columns[j],cellData+"");
	                      // System.err.println(columns[j]+"--"+cellData+"");
	                    }
	                }else{
	                    break;
	                }
	                list.add(map);
	            }
	        }
	        
	        
	        
	        
	//        String[] arr13 =  new String[13];
	        String[] arr11 =  new String[11];
	        for (Map<String, String> m : list)  
	        {  
	          for (String k : m.keySet())  
	          {  
	        	  arr11=Splicing.SplicingMainWzData(k,m.get(k),arr11);
	          }
	          for(int i=0;i<arr11.length;i++){
	        	  System.out.println("本次捕获的值为："+arr11[i]);
	          }	          	
	          //拼合sql语句
	          //暂时不验重
	         // newStringsql=Splicing.SqlCreate(arr11,newStringsql,userIdnow,groupNameId);
	          newStringsql=Splicing.SqlCreateMainWz(arr11,newStringsql,userIdnow,groupNameId);
	        
	        }
	        
	        
	        newStringsql = newStringsql.substring(0, newStringsql.length()-1);
	        MainWzSql.BatchUploadMainWzDataByStringsql(newStringsql);
	        msgmap.put("msg", "数据以上传,开始插入");
//	        List<String> typeNames=new ArrayList<String>();
//	        //遍历最后的list
//	        String typeName="";
//	        for(int i=0;i<lessonList.size();i++){
//	        typeName=lessonList.get(i).getTypeName();
//	        typeNames.add(typeName);
//	        System.out.println("所有的物资名称"+typeName);
//	        }
//	       
//	        String typeNamesList="";
//			if (CollectionUtils.isNotEmpty(typeNames)) {
//				Set<String> unRepeatNames = new HashSet<String>();
//				for (String userName : typeNames) {
//					boolean isRepeat = unRepeatNames.add(userName);
//					if (!isRepeat) {
//						// 存在重复案例 停止插入 返回插入失败以及 msg
//						System.out.println("重复物资类型：" + userName);
//						typeNamesList= typeNamesList + " " + userName + " ";
//					}
//				}
//				if ("".equals(typeNamesList)==false) {
//					msgmap.put("code", 500);
//					msgmap.put("msg", "存在重复物资类型：" + typeNamesList);
//					json = JSONObject.parseObject(JSON.toJSONString(msgmap));
//				} else {
//					msgmap.put("code", 0);
//					msgmap.put("msg", "验证通过,开始插入");
//					json = JSONObject.parseObject(JSON.toJSONString(msgmap));
//					//执行批量插入
//					//newStringsql
//					newStringsql = newStringsql.substring(0, newStringsql.length()-1);
//					//调用批量插入服务
//					System.out.println("开始调用批量插入服务");
//					System.out.println("newStringsql:"+newStringsql);
//					SupplyTypeSql.AddSelectSupplyTypeDataByStringsql(newStringsql);
//				}
//			}
//	        else{
//	        	 msgmap.put("code",500);
//				 msgmap.put("msg", "列表为空");
//				 json = JSONObject.parseObject(JSON.toJSONString(msgmap)); 
//	        }
	        json = JSONObject.parseObject(JSON.toJSONString(msgmap)); 
			return json;
	        
    }	
		
	
		
	
	
	
	
	
	
	
	
	
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
   
	
	//对excel表中的值做一定的过滤，long类型的数值自动去小数点
	public static Object getCellFormatValue(Cell cell){
		String cellValue = null;
		if(cell!=null){
        switch (cell.getCellTypeEnum()) {
            case STRING:  
            	cellValue = cell.getRichStringCellValue().getString();  
                break;  
            case NUMERIC:
                 cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());  
                 break;
            case BLANK:
            	cellValue = "";
            	break;
            default: 
            	cellValue = cell.getRichStringCellValue().getString();  
            	break;
           } 
		}
		
    else{
    	
    }
    return cellValue;
}

	
	

	public static void main(String[] args) {
        //D:/workspace/upload/WebContent/Excel/15381227276671.xlsx

		try {
			//Read("D:/workspace/upload/WebContent/Excel/15381227276671.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
