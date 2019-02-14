<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<form class="layui-form" action="" id="SuppliesFrom">
		<div class="layui-form-item">
			<input type="hidden" name="id"  class="layui-input id" value="-1" id="id">
			<label class="layui-form-label">资产名称</label>
			<div class="layui-input-block">
				<input type="text" name="suppliesName" lay-verify="required"
					autocomplete="off" placeholder="请输入标题" class="layui-input suppliesName" style="width:90%">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">型号</label>
			<div class="layui-input-block">
				<input type="text" name="model" 
					placeholder="请输入" autocomplete="off" class="layui-input model" style="width:90%">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">收到日期</label>
			<div class="layui-input-block">
				<input type="text" name="receiveTime" 
					placeholder="请输入" autocomplete="off" class="layui-input receiveTime"  style="width:90%"  id="receiveTime">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">来源</label>
			<div class="layui-input-block">
				<input type="text" name="source" 
					placeholder="请输入" autocomplete="off" class="layui-input source" style="width:90%">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">摘要</label>
			<div class="layui-input-block">
				<input type="text" name="digest" 
					placeholder="请输入" autocomplete="off" class="layui-input digest" style="width:90%">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">数量</label>
				<div class="layui-input-inline">
					<input type="text" name="number" autocomplete="off" class="layui-input number" lay-verify="zhengshu">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">单位</label>
				<div class="layui-input-inline">
					<input type="text" name="unit" 
						autocomplete="off" class="layui-input unit">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">单价</label>
				<div class="layui-input-inline">
					<input type="text" name="price" 
						autocomplete="off" class="layui-input price"  lay-verify="money">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">金额</label>
				<div class="layui-input-inline">
					<input type="text" name="money" id="money"  autocomplete="off" class="layui-input money"        lay-verify="money">
				</div>
			</div>
			
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">位置</label>
			<div class="layui-input-block">
				<input type="text" name="place" 
					placeholder="请输入" autocomplete="off" class="layui-input place"style="width:90%">
			</div>
		</div>

		
	

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">使用人</label>
				<div class="layui-input-inline" >
					<input type="text" name="userName" placeholder=""
						autocomplete="off" class="layui-input userName">
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">组别</label>
				<div class="layui-input-inline" >
					<input type="hidden"  placeholder=""	autocomplete="off" class="layui-input groupNameValue" id="groupNameValue">
					<select name="groupNameSelect"  lay-search=""  id="groupNameSelect"  class="layui-input groupNameSelect">
        				  <option value="-1">选择组别</option>
              		 </select>
				</div>
			</div>
		
			
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<input type="text" name="note" 
					placeholder="" autocomplete="off" class="layui-input note" style="width:90%">
			</div>
		</div>
		
	

			<div class="layui-form-item magt3">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="AddSuppliesBtn">立即提交</button>
				</div>
			</div>


		
	</form>

	<script type="text/javascript" src="${path}layui/layui.js"></script>
	<script type="text/javascript" src="addSupplies.js"></script>



</body>
</html>