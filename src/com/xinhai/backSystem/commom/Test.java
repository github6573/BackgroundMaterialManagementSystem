package com.xinhai.backSystem.commom;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
		
		
		try {
			
			//判断用户id、
			if(null==request.getSession().getAttribute("userId")){
				System.out.println("无法获取用户id");
				
				//request.getRequestDispatcher("LoginServlet?methods=longTimelogout").forward(request, response);
				}

			else{
			String	userIdnow=request.getSession().getAttribute("userId").toString();
			String groupNameId=request.getParameter("groupNameId");
			System.out.println("userIdnow:"+userIdnow+"groupNameId:"+groupNameId);
			JSONObject json=null;
			System.out.println("进入");
			request.setCharacterEncoding("utf-8");		
			response.setCharacterEncoding("utf-8");		
			response.setContentType("text/html;charset=utf-8");//更改响应字符流使用的编码，还能告知浏览器用什么编码进行显示
	        PrintWriter out = response.getWriter();
	   //     System.out.println(request.getContentLength());
	   //     System.out.println(request.getContentType());
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 允许设置内存中存储数据的门限，单位：字节
	        factory.setSizeThreshold(4096);
	        // 如果文件大小大于SizeThreshold，则保存到临时目录
	     //  factory.setRepository(new File("D:/workspace/BackgroundMaterialManagementSystem/WebContent/Excel"));  //本地
	     //  http://111.231.206.138:8443/
	       factory.setRepository(new File("/BackgroundMaterialManagementSystem/Excel"));    //服务器 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        // 最大上传文件，单位：字节
	        upload.setSizeMax(1000000);
	        try {
	            List fileItems = upload.parseRequest(request);
	            Iterator iter = fileItems.iterator();
	            if (iter.hasNext()) {
	                FileItem item = (FileItem) iter.next();
	                String fileName = item.getName();
	                // 忽略其他不是文件域的所有表单信息
	                if (!item.isFormField()) {
	                        try {
	                            //取得文件名
	                            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
	                            // 保存上传的文件到指定的目录指定文件名中
	                           // item.write(new File("F:\\",fileName));
	                            String NewfileName=System.currentTimeMillis()+fileName;//生成新文件名字
	                            System.out.println("NewfileName:"+NewfileName); 
	                            
	                           String tempPath = request.getServletContext().getRealPath("/upload"); 
	                            item.write(new File(tempPath,NewfileName));						//服务器
	                            String path=tempPath+"/"+NewfileName;							//服务器访问地址生成
	                           System.out.println("path:"+path);
	                            
	                            
	                            
	                            
//	                         item.write(new File("D:/workspace/BackgroundMaterialManagementSystem/WebContent/Excel",NewfileName));   //本地
//	                         String path="D:/workspace/BackgroundMaterialManagementSystem/WebContent/Excel"+"/"+NewfileName;   		//本体				
//	                           
	                          
	                           //获取结果
	                           json=Readexcel.Read(path,userIdnow,groupNameId);
	                           System.out.println(json);
	                            

	                            java.io.File file = new java.io.File(tempPath,NewfileName); //服务器
	                        //    java.io.File file = new java.io.File("D:/workspace/BackgroundMaterialManagementSystem/WebContent/Excel",NewfileName); //本地
	                            boolean del = file.delete(); 
	                          //  path.delete(); 
	                           System.out.println(del);
	                       
	                           response.setContentType("text/javascript;charset=utf-8");
	                           response.setCharacterEncoding("utf-8");
	                           response.getWriter().print(json);	
//	                           System.out.println(json.get("code")+"---"+json.getString("msg"));
	                           
	                      
//	                           request.getRequestDispatcher("project/login/login.jsp").forward(request, response);	
	                           
	                           // System.out.println(request.getContextPath() + "/project/Excel");
	                           // item.write(new File(request.getContextPath() + "/project/Excel",fileName));
	                        } catch (Exception e) {
	                            out.println(e);
	                        }
	                } else {
	                        throw new IOException("fail to upload");
	                }
            }}
	         catch (FileUploadException e) {
	            out.println(e);
	        }  
	        
	        
			
			
//			//从request中获取文本输入流信息
//			InputStream fileSourceStream = request.getInputStream();
//			String tempFileName = "F:/tempFile";
//			
//			//设置临时文件，保存待上传的文本输入流
//			File tempFile = new File(tempFileName);
//			
//			
//			//outputStram文件输出流指向这个tempFile
//			FileOutputStream outputStream = new FileOutputStream(tempFile);
//			
//			//读取文件流	
//			byte temp[] = new byte[1024];		
//			int n;	
//			while(( n = fileSourceStream.read(temp)) != -1){
//				outputStream.write(temp, 0, n);		}	
//			outputStream.close();	
//			fileSourceStream.close();	
//			//获取上传文件的名称 		
//			RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");	
//			randomFile.readLine();  		String str = randomFile.readLine();	
//			int start = str.lastIndexOf("=") + 2;	
//			int end = str.lastIndexOf("\"");	
//			String filename = str.substring(start, end);
//			//定位文件指针到文件头		
//			randomFile.seek(0);		
//			long startIndex = 0;	
//			int i = 1;		
//			//获取文件内容的开始位置	
//			while(( n = randomFile.readByte()) != -1 && i <=4){		
//				if(n == '\n'){				
//					startIndex = randomFile.getFilePointer();	
//					i ++;		
//					}		}	
//			startIndex = startIndex -1; 
//			//这里一定要减1，因为前面多读了一个，这里很容易忽略		
//			//获取文件内容结束位置		
//			randomFile.seek(randomFile.length());	
//			long endIndex = randomFile.getFilePointer();	
//			int j = 1;		
//			while(endIndex >=0 && j<=2){	
//				endIndex--;		
//				randomFile.seek(endIndex);	
//				if(randomFile.readByte() == '\n'){
//					j++;			}		}		
//			//设置保存上传文件的路径		
//			String realPath =  "F:/file";	
//			File fileupload = new File(realPath);	
//			if(!fileupload.exists()){			
//				fileupload.mkdir();		}	
//			File saveFile = new File(realPath,filename);		
//			RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");	
//			//根据起止位置从临时文件中读取文件内容		
//			randomFile.seek(startIndex);	
//			while(startIndex < endIndex){	
//				randomAccessFile.write(randomFile.readByte());	
//				startIndex = randomFile.getFilePointer();	
//				}	
//			//关闭输入输出流并 删除临时文件	
//			randomAccessFile.close();
//			randomFile.close();		
//			tempFile.delete();			
//			request.setAttribute("result", "文件上传成功");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);
			

			

			

			
			
			
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// TODO Auto-generated method stub
		//doGet(request, response);
//			try {
//		    String fileName = request.getContextPath()+"/Excel/demoExcel.xlsx"; 
//		    System.out.println("fileName:"+fileName);
//		    File file = new File(fileName); 
//		    System.out.println("file:"+file);
//		    // 设置读文件编码
//		    WorkbookSettings setEncode = new WorkbookSettings();
//		    //setEncode.setEncoding("GB2312");
//		    setEncode.setEncoding("utf-8");
//		    // 从文件流中获取Excel工作区对象（WorkBook）
//	//	    Workbook wb = Workbook.getWorkbook(file,setEncode); 
//		    Workbook wb = Workbook.getWorkbook(file,setEncode); 
//		    // 从工作区中取得页（Sheet）,默认单独一页，第一页
//		    Sheet sheet = wb.getSheet(0); 
//		    // 测试：循环打印Excel表中的内容
//		    for (int i = 0; i < 50; i++) { 
//		        for (int j = 0; j < sheet.getColumns(); j++) {
//		                    Cell cell = sheet.getCell(j, i);
//		        System.out.print(cell.getContents());
//		                }
//		                System.out.println();
//		            }
//		        } catch (BiffException e) {
//		            e.printStackTrace();
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }

//		 response.setCharacterEncoding("utf-8");
//	        response.setContentType("application/json");
//	        PrintWriter out = response.getWriter();
//	        FileItemFactory factory = new DiskFileItemFactory();
//	        ServletFileUpload upload = new ServletFileUpload(factory);
//	        upload.setHeaderEncoding(request.getCharacterEncoding());
	       // ExcelHelper helper = new ExcelHelper();
	
	}

	
}
