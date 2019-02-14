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
	<form class="layui-form" action="" id="MainWzFrom">
		<div class="layui-form-item">
			<input type="hidden" name="id" class="layui-input id" value="-1"
				id="id"> <label class="layui-form-label">资产名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" lay-verify="required"
					autocomplete="off" placeholder="请输入资产名称" class="layui-input name"
					style="width: 90%">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">供应商</label>
			<div class="layui-input-block">
				<input type="text" name="supplier" autocomplete="off"
					placeholder="供应商" class="layui-input supplier" style="width: 90%">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">型号</label>
			<div class="layui-input-block">
				<input type="text" name="model" placeholder="请输入" autocomplete="off"
					class="layui-input model" style="width: 90%">
			</div>
		</div>



		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">数量</label>
				<div class="layui-input-inline">
					<input type="text" name="number" autocomplete="off"
						class="layui-input number" lay-verify="zhengshu">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">单位</label>
				<div class="layui-input-inline">
					<input type="text" name="unit" autocomplete="off"
						class="layui-input unit">
				</div>
			</div>


		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">使用人</label>
				<div class="layui-input-inline">
					<input type="text" name="userName" placeholder=""
						autocomplete="off" class="layui-input userName">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">组别</label>
				<div class="layui-input-inline">
					<input type="hidden" placeholder="" autocomplete="off"
						class="layui-input groupId" id="groupId"> 
				<select	 name="groupNameSelect" lay-search="" id="groupNameSelect"
						class="layui-input groupNameSelect">
						<option value="-1">选择组别</option>
					</select>
				</div>
			</div>


		</div>
		
		
		
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">设备状态</label>
				<div class="layui-input-inline">
					<input type="hidden" name="state" placeholder="" autocomplete="off"id="state"
					class="layui-input state" style="width: 90%">
					<select	 name="stateSelect" lay-search="" id="stateSelect"
						class="layui-input stateSelect"   lay-verify="shebei">
						<option value="-1">设备状态</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">位置</label>
				<div class="layui-input-inline">
					<input type="hidden" name="position" placeholder="" autocomplete="off"
					class="layui-input position" style="width: 90%"  id="position">
					<select	 name="positionSelect" lay-search="" id="positionSelect"
						class="layui-input positionSelect" lay-verify="position">
						<option value="-1">位置</option>
					</select>
				</div>
			</div>


		</div>
		
		
		
	
		
		
		<div class="layui-form-item">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<input type="text" name="note" placeholder="" autocomplete="off"
					class="layui-input note" style="width: 90%">
			</div>
		</div>



		<div class="layui-form-item magt3">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="AddMainWzBtn">立即提交</button>
			</div>
		</div>



	</form>

	<script type="text/javascript" src="${path}layui/layui.js"></script>
	<script type="text/javascript" src="addMainWz.js"></script>



</body>
</html>