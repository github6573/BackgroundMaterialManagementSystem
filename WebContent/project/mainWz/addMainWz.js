layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
       
        //日期
        laydate.render({
          elem: '#receiveTime'
        });
        
        
        
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
    form.verify({
    	shebei : function(val){
    		if(val == '-1'){
    			return "请选择设备状态";
    		}
    	},
    	position : function(val){
    		if(val == '-1'){
    			return "请选择物资位置";
    		}
    	}
    });
    
    form.verify({
		money: function(val, item) {
			if(!/^\d+(\.\d{0,2})?$/.test(val)){
				return '请输入正确的格式'
			}
		},
		//zhengshu
		zhengshu : function(val){
			if(!/^[+]{0,1}(\d+)$/.test(val)){
				return '请输入正确的格式'
			}
	        }
	});
    
    
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
        	var Select= document.getElementById("groupNameSelect");
        	var groupId= document.getElementById("groupId").value;
        	
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].value)); //这个兼容IE与firefox
        		//alert("groupName:"+groupName +"date[i].value:"+date[i].value);
        		if(groupId==date[i].value){
        			Select.options[i+1].selected = 'selected';
        		}
        		
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    
    
    SearchTypeListData("state");    
    function SearchTypeListData(type){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/DictionaryController?methods=getTypeList&&type="+type,
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	var typeSelect=type+"Select";
        	var Select= document.getElementById(typeSelect);
        	var typeValue= document.getElementById(type).value;
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].value)); //这个兼容IE与firefox
        		if(type=="state"&&null!=typeValue&&typeValue.length>0){
        				if(typeValue==date[i].value){
        			Select.options[i+1].selected = 'selected';
        			}       		
        		}       		
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    SearchPositionListData();
    function SearchPositionListData(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/DictionaryController?methods=getTypeList&&type=position",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//var typeSelect=type+"Select";
        	var Select= document.getElementById("positionSelect");
        	var typeValue= document.getElementById("position").value;
        
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].name)); //这个兼容IE与firefox
        		//alert("typeValue:"+typeValue+"date[i].value:"+date[i].name);
        		//alert("groupName:"+groupName +"date[i].value:"+date[i].value);
        		if(null!=typeValue){     		
        			if(typeValue===date[i].name){
        			//	alert("匹配");
            			Select.options[i+1].selected = 'selected';
        			}	
        		}	
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    
    form.on("submit(AddMainWzBtn)",function(data){
    	//alert("id:"+idValue+"name:"+nameValue);
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        		 $.ajax({
        			 	type : 'post',
        			 	url : '/BackgroundMaterialManagementSystem/MainWzController?methods=addMainWz',
						data:$("#MainWzFrom").serialize(), 
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