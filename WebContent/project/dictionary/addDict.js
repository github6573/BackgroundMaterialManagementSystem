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
        },
        dictType:function(val){
            if(val == '-1'||val == ''){
                return "请选择字典值类型";
            }
        }
    });
    
    
    
    
    SearchDictType();
    function SearchDictType(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/DictionaryController?methods=dictType",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	//form.render("select");                           
        	var typeValue= document.getElementById("type").value;
        	var Select= document.getElementById("typeSelect");
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].value)); //这个兼容IE与firefox
        		if(typeValue==date[i].value){
        			Select.options[i+1].selected = 'selected';
        		}
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    
    
    
    form.on("submit(AddDict)",function(data){
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        		 $.ajax({
        			 	type : 'post',
        			 	url : '/BackgroundMaterialManagementSystem/DictionaryController?methods=addDict',
						 data:$("#DictFrom").serialize(), 
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