<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物资管理游客页面</title>
<link href="${path}css/drop-down.css" rel="stylesheet" type="text/css">
<link href="${path}css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path}js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${path}js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${path}js/select-widget-min.js"></script>
	<link rel="stylesheet" href="${path}layui/css/layui.css" media="all" />
<script type="text/javascript">
$(document).ready(function(){		
	$(".ui-select").selectWidget({
		change: function (changes) {
			return changes;
		},
		effect: "slide",
		keyControl: true,
		speed: 200,
		scrollHeight: 250
	});
});		
//底部
$(document).ready(function(){
    if(document.documentElement.clientHeight > document.documentElement.offsetHeight){    
		$(".nyh_footer").css({"position":"fixed","bottom":0});
	}
});
$(document).ready(function(){
	$(".select_city").hide();
	$(".jq").click(function(){
		$(".select_city").fadeToggle();
	});
});
</script></head>




<body >
<div class="top"></div>
<div class="header">
	<div class="nayouhuo_logo"><img src="${path}images/logo.png" width="435" height="89"></div>
    <div class="search">
    	<form action="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html" method="get">
        	<div class="search_input">
                <input name="wzName" type="text" class="search_url" placeholder="请输入需要查询的物资！" id="wzName">
                <input name="" type="button" class="search_submit" value="查询" style="background-image: url(${path}/images/iconfont-sousuo.png);" id="searchBtn">
            </div>
        </form>
    </div>
</div>

<div style="padding-left: 15%;padding-right: 15%">
<table class="layui-hide" id="test" ></table>              
</div>
<script type="text/javascript" src="${path}layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  //alert("--"+suppliesName);
  table.render({
    elem: '#test'
    ,id:"test"
    ,url:'/BackgroundMaterialManagementSystem/TouristController'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,page : true
    ,limit : 15
    ,cols:[[
    	 // {type: "checkbox", fixed:"left"},
    
    //	  {type: 'numbers',title:'序号',align:"center"},
    	  {field: 'name', title: '资产名称', align:"center",width:120},
    	  {field: 'supplier', title: '供应商', align:"center",width:120},
    //	  {field: 'model', title: '型号', align:"center",width:120},
    //	  {field: 'unit', title: '计量单位',align:"center"},
    	  {field: 'number', title: '数量',align:"center"},
    //	  {field: 'userName', title: '使用人',align:"center"},
    	//  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
    	  {field: 'position', title: '位置',align:"center"},
    	  {field: 'note', title: '备注',align:"center"},
    	 // {field: 'groupId', title: '组别',align:"center"},    	
    	  {field: 'groupName', title: '组别名称',align:"center"},    	
  	  //{title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
    ]]
  });
  
  
  $("#searchBtn").click(function(){  
		//alert($('#wzName').val());
		alert(suppliesName);
	   	table.reload('test',{
	           method:'post',
	           where:selectData(),
	           page:{
	           	curr:1
	           }
	       });
	   });
  
  
  function selectData(){
	   	return {
	   		suppliesName:$('#wzName').val(),
	   		
	   		//搜索的关键字
	   	 }
	   	} 
  
});

  
//$('#wzName').val();searchName


</script>


<!--

<div class="show_content" id="show">
	<div class="location"><span>四川-乐山-市中区</span><h1>京东商城哪有货，查询京东商城商品是否有货？来【一起惠 · 哪有货】查一查！</h1></div>
    <div class="show_list">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody><tr>
            <td height="40" align="center" valign="middle" class="title_bg">商城</td>
            <td height="40" align="center" valign="middle" class="title_bg">图片</td>
            <td height="40" align="center" valign="middle" class="title_bg">原价</td>
            <td height="40" align="center" valign="middle" class="title_bg">价格</td>
            <td height="40" align="center" valign="middle" class="title_bg">地区</td>
            <td height="40" align="center" valign="middle" class="title_bg">是否有货</td>
            <td height="40" align="center" valign="middle" class="title_bg">库存</td>
            <td height="40" align="center" valign="middle" class="title_bg">操作</td>
          </tr>
          <tr>
            <td height="40" align="center">京东商城</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank"><img src="./557ec99aN8591c53c.jpg" width="50" height="50" border="0"></a></td>
            <td height="40" align="center">15.00</td>
            <td height="40" align="center">6.00</td>
            <td height="40" align="center" class="chengse">北京</td>
            <td height="40" align="center" class="luse">现货</td>
            <td height="40" align="center">---</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank">去看看</a></td>
          </tr>
          <tr>
            <td height="40" align="center">京东商城</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank"><img src="./557ec99aN8591c53c.jpg" width="50" height="50" border="0"></a></td>
            <td height="40" align="center">15.00</td>
            <td height="40" align="center">6.00</td>
            <td height="40" align="center" class="chengse">上海</td>
            <td height="40" align="center" class="luse">现货</td>
            <td height="40" align="center" class="chengse">5</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank">去看看</a></td>
          </tr>
          <tr>
            <td height="40" align="center">京东商城</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank"><img src="./557ec99aN8591c53c.jpg" width="50" height="50" border="0"></a></td>
            <td height="40" align="center">15.00</td>
            <td height="40" align="center">6.00</td>
            <td height="40" align="center" class="chengse">天津</td>
            <td height="40" align="center" class="huise">无货</td>
            <td height="40" align="center">---</td>
            <td height="40" align="center"><a href="http://www.17sucai.com/preview/1983/2015-06-29/nayouhuo/index.html#" target="_blank">去看看</a></td>
          </tr>
        </tbody></table>
    </div>
</div>-->
<div class="nyh_footer">
	<div class="nyh_footer_nav">
    	<a href="http://news.178hui.com/2839.html" target="_blank">关于我们</a><a href="http://cha.178hui.com/" target="_blank">新海物资</a><a href="http://www.178hui.com/" target="_blank">一起惠返利网</a><a href="http://mall.178hui.com/" target="_blank">商城返利</a><a href="http://baoliao.178hui.com/0-0-youzhi.html" target="_blank">优质爆料</a><a href="http://www.178hui.com/index.php?mod=bijia&amp;act=index" target="_blank">比价网</a><a href="http://www.yqhlm.com/" target="_blank">CPS广告联盟</a>

    </div>
    <div class="nyh_copyright">Copyright © 2019  蜀ICP备xxxxx号-1 客服电话：18395806960</div>
</div>

</body></html>