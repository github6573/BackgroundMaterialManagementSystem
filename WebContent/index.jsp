<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>八分厂后台管理系统</title>
    <link rel="stylesheet" href="${path}frame/layui/css/layui.css">
    <link rel="stylesheet" href="${path}frame/static/css/style.css">
    <link rel="icon" href="${path}frame/static/image/tou.png">
</head>
<body>

<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <div class="layui-header my-header">
        <a>
           
            <div class="my-header-logo">后台员工日志管理系统</div>
        </a>
        <div class="my-header-btn">
            <button class="layui-btn layui-btn-small btn-nav"><i class="layui-icon">&#xe65f;</i></button>
        </div>
        <!-- 顶部左侧添加选项卡监听 -->
        <ul class="layui-nav" lay-filter="side-top-left">
        </ul>

        <!-- 顶部右侧添加选项卡监听 -->
        <ul class="layui-nav my-header-user-nav" lay-filter="side-top-right">
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>主题</a>
                <dl class="layui-nav-child">
                    <dd data-skin="0"><a href="javascript:;">默认</a></dd>
                    <dd data-skin="1"><a href="javascript:;">纯白</a></dd>
                    <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><img src="./frame/static/image/tou.png" alt="logo">  ${userName}</a>
                <dl class="layui-nav-child">
                    <dd><a href="HomeController?methods=logout"  target="_top"><i class="layui-icon">&#x1006;</i>退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <!-- side -->
    <div class="layui-side my-side">
        <div class="layui-side-scroll">
            <!-- 左侧主菜单添加选项卡监听 -->
            <ul class="layui-nav layui-nav-tree" lay-filter="side-main">
               <li class="layui-nav-item  layui-nav-itemed">
                    <a href="javascript:;"><i class="layui-icon">&#xe620;</i>基础功能</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" ><i class="layui-icon">&#xe621;</i>物资管理</a></dd>
                        <dd><a href="javascript:;" ><i class="layui-icon">&#xe621;</i>物资使用管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe628;</i>物资类型管理</a>
                    
                </li>
            </ul>

        </div>
    </div>
    <!-- body -->
    
    
    <div class="layui-body my-body">
        <div class="layui-tab layui-tab-card my-tab" lay-filter="card" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="1"><span><i class="layui-icon">&#xe638;</i>欢迎页</span></li>
               
            </ul>
            	
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                   <!-- <iframe id="iframe" src="demo/welcome2.html" frameborder="0"></iframe>  -->
                    <iframe id="iframe" src="project/preparePage/preparePage.jsp" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 
    <div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<ul class="layui-tab-title top_tab" id="top_tabs">
					<li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
				</ul>
				<ul class="layui-nav closeBox">
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
				    <dl class="layui-nav-child">
					  <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
				      <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
				      <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
				    </dl>
				  </li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe src="page/main.html"></iframe>
					</div>
				</div>
			</div>
		</div>
     -->
    <!-- footer -->
    <div class="layui-footer my-footer">
       <!-- 没啥用还是 根据id 再渲染菜单吧 
       <input type="hidden" value="${permissions}" id="permissions" /> 
       -->
       <input type="hidden" value="${userId}" id="userId" /> 
    </div>
</div>



<!-- 右键菜单 -->
<div class="my-dblclick-box none">
    <table class="layui-tab dblclick-tab">
        <tr class="card-refresh">
            <td><i class="layui-icon">&#x1002;</i>刷新当前标签</td>
        </tr>
        <tr class="card-close">
            <td><i class="layui-icon">&#x1006;</i>关闭当前标签</td>
        </tr>
        <tr class="card-close-all">
            <td><i class="layui-icon">&#x1006;</i>关闭所有标签</td>
        </tr>
    </table>
</div>

<script type="text/javascript" src="${path}frame/layui/layui.js"></script>
<script type="text/javascript" src="${path}frame/static/js/vip_comm.js"></script>
<script type="text/javascript">
layui.use(['layer','vip_nav'], function () {

    // 操作对象
    var layer       = layui.layer
        ,vipNav     = layui.vip_nav
        ,$          = layui.jquery;
    					   
//var permissions = document.getElementById("permissions").value;
var userId = document.getElementById("userId").value;
//alert("permissions:"+permissions);
    // 顶部左侧菜单生成 [请求地址,过滤ID,是否展开,携带参数]
//  var base=${path};
  var b='json/nav_main_admin.json';
  var c='json/nav_main.json';
   //   vipNav.top_left('./json/nav_top_left.json','side-top-left',false); // 
      // 主体菜单生成 [请求地址,过滤ID,是否展开,携带参数]
      vipNav.main('./json/nav_main_admin.json','side-main',true);//true 左侧菜单都展开
 	  // 主体菜单生成 Tony特化版[请求地址,过滤ID,是否展开,携带参数]
      //true 左侧菜单都展开
      //if(1==permissions){ vipNav.main('${path}json/nav_main_admin.json','side-main',false);}
     //vipNav.main('${path}ControllerTest?methods=SearchLeftListByUserId&&userId='+userId,'side-main',false);
      // if(1==permissions){ vipNav.main('${path}ControllerTest?permissions='+permissions,'side-main',false);}
      // if(2==permissions){vipNav.main('${path}json/nav_main.json','side-main',false);}
  
  
    // you code ...


});
</script>
</body>
</html>