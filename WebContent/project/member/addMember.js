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
    form.on("submit(AddMember)",function(data){
//    	var idValue= document.getElementById("id").value; 
//    	var nameValue= document.getElementById("name").value; 
//    	var workNumberValue= document.getElementById("workNumber");
//    	var telvalue= document.getElementById("tel");
//    	var departmentValue= document.getElementById("department");
    	//alert("id:"+idValue+"name:"+nameValue);
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息

        		 $.ajax({
        			 	type : 'post',
        			 	url : '/BackgroundMaterialManagementSystem/MemberController?methods=addMember',
						 data:$("#MemberFrom").serialize(), 
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