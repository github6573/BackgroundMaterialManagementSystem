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
<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"
	media="all" />
<script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
<link rel="stylesheet" href="${path}css/public.css" media="all" />
</head>
<body>
	<form id="file_form" action="/BackgroundMaterialManagementSystem/Test"
		enctype="multipart/form-data" method="post"
		onsubmit="return validForm(this)">
		<div class="layui-form-item">
			<label class="layui-form-label">选择模板</label>
			<div class="layui-input-block">
				<a
					href="/BackgroundMaterialManagementSystem/project/Excel/mainUpload.xls"
					download="物资批量上传模板.xls" style="color: red">点击下载用上传用模板</a>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">选择文件</label>
			<div class="layui-input-block">
				<input type="file" name="file" id="file_input" />
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">组别</label>
			<div class="layui-input-block">
				<select name="groupNameId" lay-search="" id="groupNameId"
					class="layui-input groupNameId" style="width: 80%">
					<option value="-1">选择物资的组别</option>

				</select>
			</div>
		</div>




		<div class="layui-form-item magt3">
			<div class="layui-input-block">
				<input type="submit" value="文件上传" id="file_button"
					lay-filter="file_button">
			</div>
		</div>


	</form>
	<script type="text/javascript">
    	function validForm(fm){
    		
    	var groupId=document.getElementById("groupNameId").value;
    	var fileDir =document.getElementById("file_input").value;
    	 var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
    //	alert("fileDir:"+fileDir);
    //alert("groupId:"+groupId);
    	if(groupId=="-1"){
    		alert("请先选择组别");
    		return false;
    	}
    	
    		
    	else{	
    		
    	 	if("" == fileDir||null==fileDir){    
                alert("选择需要导入的Excel文件！");
                return false;    
            } 
       		else if(".xls" != suffix && ".xlsx" != suffix ){    
       		            alert("选择Excel格式的文件导入！");
       		            return false;    
       		        }    
    		
       		 else{
       		fm.action = fm.action + "?groupNameId=" + fm['groupNameId'].value;
       		return true;
       		}
    	}
    }
	</script>
	<script>
SearchGroupListData();
function SearchGroupListData(){
	$.ajax({
    	url:"/BackgroundMaterialManagementSystem/UseRecordController?methods=getGroupList",
    	type:"Post",
    	dataType:"json",
    	success:function(result){
    	var date = result.data;    //返回的数据
    	var Select= document.getElementById("groupNameId");
    	//搜索框
    	for(var i=0;i<date.length;i++){
    		Select.options.add(new Option(date[i].name,date[i].value)); //这个兼容IE与firefox
    	}
    	//form.render("select");  
    	//form.render();
    		}
    	});
}  
</script>
</body>
</html>