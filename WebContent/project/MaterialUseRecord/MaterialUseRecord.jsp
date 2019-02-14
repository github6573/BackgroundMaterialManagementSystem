<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"
	media="all" />
<link rel="stylesheet" href="<%=basePath%>css/public.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<fieldset class="layui-elem-field">
			<legend>功能区</legend>
			<div class="layui-field-box">
				<a class="layui-btn layui-btn-normal layui-btn-normal refresh">刷新</a>
				<a class="layui-btn layui-btn-normal layui-btn-normal addUseRecord"
					id="addUseRecordBtn">新增</a> <a
					class="layui-btn layui-btn-normal layui-btn-normal delUseRecord"
					id="delUseRecordBtn">禁用</a>
			</div>
		</fieldset>
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<select name="nameSelect" lay-search="" id="nameSelect"
							class="layui-input nameSelect">
							<option value="-1">选择物资名称</option>
						</select>
					</div>

					<div class="layui-input-inline">
						<select name="userSelect" lay-search="" id="userSelect"
							class="layui-input userSelect">
							<option value="-1">使用者</option>
						</select>
					</div>

					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="TimeScope"
							name="TimeScope">
					</div>
					<div class="layui-input-inline">
						<a class="layui-btn search_btn" data-type="reload">搜索</a> <a
							class="layui-btn  reset_btn" data-type="">重置</a>
					</div>

				</div>
			</div>
		</blockquote>

		<table id="UseRecordList" lay-filter="UseRecordList"></table>
		<div id="test1"></div>
		<!--操作-->
		<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	</script>
	</form>
	<script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
	<script type="text/javascript" src="UseRecordList.js"></script>
</body>
</html>