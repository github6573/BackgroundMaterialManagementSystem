<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>  
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script> 
</head>
<body>
  	<form id="file_form" action="/BackgroundMaterialManagementSystem/Test?key=type" enctype="multipart/form-data" method="post">
        <a href="/BackgroundMaterialManagementSystem/project/Excel/type.xls"  download="物资类型上传模板.xls">点击下载上传用模板</a>
        <input type="file" name="file" id="file_input" /> 
        <input type="submit" value="文件上传"   id="file_button"  lay-filter="file_button">
    </form>
<script type="text/javascript" src="${path}frame/layui/layui.js"></script>
</body>
</html>