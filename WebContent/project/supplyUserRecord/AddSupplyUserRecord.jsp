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
	<form class="layui-form layui-row layui-col-space10" id="supplyUserRecordFrom" 
		action="">
		<div class="layui-col-md9 layui-col-xs12">
			<div class="layui-row layui-col-space10">
				<div class="layui-col-md9 layui-col-xs10">
					
				<input type="hidden" class="layui-input  id" id="id" lay-verify="id" name="id" value="-1">
				
						<div class="layui-form-item">
						<label class="layui-form-label">部门</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input  department"
								 id="department"   name="department">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">使用者</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input  userName"
						 id="userName"  name="userName">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">种类名称</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input supplyTypeNameValue" id="supplyTypeNameValue"   name="supplyTypeName" >
						</div>
					</div>

					<div class="layui-form-item ">
						<label class="layui-form-label">型号</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input  modelValue" id="modelValue"  name="model" >
						</div>
					</div>
					
					
					<div class="layui-form-item ">
						<label class="layui-form-label">物理地址</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input  physicalAddressValue" id="physicalAddressValue"  name="physicalAddress" >
						</div>
					</div>
					
					
						<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注一</label>
							<div class="layui-input-block"  >
							<textarea  class="layui-textarea note1Value"   id="note1Value" name="note1"  style="width:100%" ></textarea>
							</div>
						</div>
					
					
					
						<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注二</label>
							<div class="layui-input-block"  >
							<textarea  class="layui-textarea note2Value"   id="note2Value" name="note2"  style="width:100%" ></textarea>
							</div>
						</div>
				</div>
			</div>
			
			
			<div class="layui-form-item magt3">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="AddSupplyUserRecordFrom">立即提交</button>
				</div>
			</div>


		</div>
	</form>

	<script type="text/javascript" src="${path}layui/layui.js"></script>
	<script type="text/javascript" src="AddSupplyUserRecord.js"></script>



</body>
</html>