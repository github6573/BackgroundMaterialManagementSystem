<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%> 
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>后台物资管理系统</title>
<link href="${path}css/login.css" rel="stylesheet" type="text/css" />
<script>
function msg(){
	 var msg = document.getElementById("msg").value;
	 if(msg==""){
		// alert("msg为空");
	 }
	 else if(msg==null){
		 alert("msg为null");
	 }
	 else{
		 alert(msg);
	 }
	
}
</script>
</head>
<body onload="msg()" style="overflow:auto">
	<div class="login_box" >
		<div class="login_l_img">
			<img src="${path}images/login-img.png" />
		</div>
		<div class="login" >
			<div class="login_logo">
				<a href="#"><img src="${path}images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>后台物资管理系统</p>
			</div>
			<form    action="/BackgroundMaterialManagementSystem/HomeController" method="post"  onsubmit="return jiance()"     >
			 	<table width="100%">		 
				<tr><td><input  type="text"  name="username" id="username" value="" placeholder="用户名"/></td></tr>
				<tr><td><input  type="password"  name="password" id="password" value="" placeholder="密码"/></td></tr>
				<tr><td><input  type="hidden"  name="methods"  value="login" /></td></tr>
				<tr><td><input value="登入" style="width: 100%;" type="submit" /></td></tr>
				<tr ><td align="right" ><a href="${path}project/password/password.jsp">修改密码</a></td></tr>
				</table>
				<input  type="hidden"  name="msg" id="msg" value="${msg}" />		
			</form>
		</div>

	</div>
	<div style="text-align: center;">
	</div>
</body>
<script>
function jiance(){
	
	 var username = document.getElementById("username").value;
	 var password =document.getElementById("password").value;
	
	 if(username == '') {
        alert('请输入用户名');  
        return  false;
        
    }
	 else if(password=='') {
		 alert("请输入密码");
		 return false;
	 }
	
	
	
}

</script>
</html>


