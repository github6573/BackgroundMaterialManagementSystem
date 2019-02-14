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
	<title>数据字典</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=basePath%>css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal layui-btn-normal addDict" id="AddDictBtn">增加</a>
				<a class="layui-btn layui-btn-normal layui-btn-normal delDict" id="DelDictBtn">删除</a>
				<a class="layui-btn layui-btn-normal layui-btn-normal refresh">刷新</a>
			</div>
	</blockquote>
	<table id="dictionaryList" lay-filter="dictionaryList"></table>
	<div id="test1"></div>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	</script>
</form>
<script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
<script type="text/javascript" src="dictionary.js"></script>
</body>
</html>