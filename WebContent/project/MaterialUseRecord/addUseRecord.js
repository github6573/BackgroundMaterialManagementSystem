layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
       

    form.verify({
    	department : function(val){
            if(val == ''){
                return "部门不能为空";
            }
        },
        workNumber : function(val){
            if(val == ''){
                return "工号不能为空";
            }
        }
    });
    
    
    SearchmaterialNameData();
    function SearchmaterialNameData(){
    	$.ajax({
//        	url:"/BackgroundMaterialManagementSystem/UseRecordController?methods=getMaterialNameList",
        	url:"/BackgroundMaterialManagementSystem/MainWzController?methods=nameList",
    		
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	//form.render("select");                           
        	var Select= document.getElementById("nameSelect");
        	var materialId= document.getElementById("materialId").value;
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].name)); //这个兼容IE与firefox
        		if(materialId==date[i].name){
        			Select.options[i+1].selected = 'selected';
        		}
        		
        		
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    	SearchListData("name"); 
    	function SearchListData(type){
    		$.ajax({
    			url:"/BackgroundMaterialManagementSystem/MemberController?methods=getList",
    			type:"Post",
    			dataType:"json",
    			data:{"type":type},
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	form.render("select");                           
        	var Select= document.getElementById("userSelect");
        	var userIdValue= document.getElementById("userId").value;
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].extraField,date[i].id)); //这个兼容IE与firefox
        		if(userIdValue==date[i].id){
        			Select.options[i+1].selected = 'selected';
        		}
        		
        		
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }    
    
    
    	
//    		selectUser(); 
//    	function selectUser(){  
//    		var userIdValue= document.getElementById("userId").value; 
//    		//var userSelect = document.getElementById("userSelect"); 
//    		alert("userIdValue:"+userIdValue);
//    		document.getElementById('userSelect').value=userIdValue;
//    		form.render("select");  
//      } 	  
    
    
    
    
    
    
    
    form.on("submit(AddUseRecord)",function(data){

        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息

        		 $.ajax({
        			 	type : 'post',
        			 	url : '/BackgroundMaterialManagementSystem/UseRecordController?methods=addUseRecord',
						 data:$("#UseRecordFrom").serialize(), 
						cache : false,
						dataType : 'json',
						success : function(data) {
							var status=null;
							if (data.success) {
								var msg=data.msg;
								 setTimeout(function(){
						         top.layer.close(index);
						         top.layer.msg(msg);
						          layer.closeAll("iframe");
						            //刷新父页面
						            parent.location.reload();
						        },500);									
							} else {
								var msg=data.msg;
								setTimeout(function(){
							         top.layer.close(index);
							         top.layer.msg(msg);
							          layer.closeAll("iframe");
							            //刷新父页面
							            parent.location.reload();
							        },500);
							}
						}
					})		 
        return false;//如果不加这句，则ajax的回调函数不执行
    });
});