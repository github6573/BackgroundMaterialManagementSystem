layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;       

        
        
        SearchGroupListData();
	    function SearchGroupListData(){
	    	$.ajax({
	        	url:"/BackgroundMaterialManagementSystem/UseRecordController?methods=getGroupList",
	        	type:"Post",
	        	dataType:"json",
	        	success:function(result){
	        	var date = result.data;    //返回的数据
	        	//add_role_name给select定义的id
	        	//form.render("select");                           
	        	var Select= document.getElementById("groupNameId");
	        	//搜索框
	        	for(var i=0;i<date.length;i++){
	        		Select.options.add(new Option(date[i].name,date[i].value)); //这个兼容IE与firefox
	        	}
	        	form.render("select");  
	        	form.render();
	        		}
	        	});
	    }  

    function createTime(v){
    	var date = new Date(v);
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        m = m<10?'0'+m:m;
        var d = date.getDate();
        d = d<10?("0"+d):d;
        var h = date.getHours();
        h = h<10?("0"+h):h;
        var M = date.getMinutes();
        M = M<10?("0"+M):M;
        var str = y+"-"+m+"-"+d+" "+h+":"+M;
        return str;
    }
   
    
   
    
 

});