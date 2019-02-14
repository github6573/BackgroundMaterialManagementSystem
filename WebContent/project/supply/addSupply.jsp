<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>文章列表--layui后台管理模板 2.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript" src="${path}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${path}layer/layer.js"></script>
<link rel="stylesheet" href="${path}layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${path}css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form layui-row layui-col-space10" id="supplyFrom" 
		action="">
		<div class="layui-col-md9 layui-col-xs12">
			<div class="layui-row layui-col-space10">
				<div class="layui-col-md9 layui-col-xs7">
					
				<input type="hidden" class="layui-input  id" id="id" lay-verify="id" name="id" value="-1">
					<div class="layui-form-item">
						<label class="layui-form-label">物资类型</label>
						<div class="layui-input-block">
							<input type="hidden" class="layui-input" id="typeValue" >
						 	<select name="type" id="type"  class="layui-input type" lay-filter="typeSelect"  lay-verify="required">
        				  		<option value="-1">物资类型</option>
       				 		</select>
							
						</div>
					</div>
				
				
					<div class="layui-form-item">
						<label class="layui-form-label">物资名称</label>
						<div class="layui-input-block">
							<input type="hidden" class="layui-input" id="typeNameValue" >
						 	<select name="typeName" id="typeName"  class="layui-input typeName"   lay-verify="required">
        				  		<option value="-1">物资名称</option>
       				 		</select>
							
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">物资型号</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input model" id="model"  name="model"  lay-verify="required">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">物资数量</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input number" id="number" name="number" lay-verify="required">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">附加信息</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input note" id="note" name="note" lay-verify="">
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item magt3">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="AddSupply">立即提交</button>
				</div>
			</div>


		</div>
	</form>

	<script type="text/javascript" src="${path}layui/layui.js"></script>
	<script type="text/javascript" src="addSupply.js"></script>



</body>
</html>