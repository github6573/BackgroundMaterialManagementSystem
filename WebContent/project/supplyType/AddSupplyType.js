layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
       
//        selectState(); 
//        function selectState(){  
//        	//var stateValue	=document.getElementById("stateValue").innerHTML;
//        var stateValue= document.getElementById("stateValue").value; 
//       // var stateValue=$("#stateValue").val();
//       alert(stateValue);
//       // top.layer.msg("stateValue:"+stateValue);
//        
//        var state_btn = document.getElementById("state"); 
//        state_btn.value=stateValue;
//      //  state_btn.value="1";
//    	form.render("select");  
//        } 	
        	
//      selectState(); 
//      function selectState(){  
//    	 // var stateValue=$("#stateValue").val();
//    	 // alert("stateValue:"+stateValue);
//    	$("#state").val(1);
//      //  $("#state").val(option[1]);
//        form.render("select");   
//	} 
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

    
    


    form.on("submit(AddSupplyType)",function(data){
        //截取文章内容中的一部分文字放入文章摘要
       // var abstract = layedit.getText(editIndex).substring(0,50);
    	
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	
        // 实际使用时的提交信息
       //  $.post(
        		 $.ajax({
					//	type : 'post',
						url : '/BackgroundMaterialManagementSystem/SupplyTypeController?methods=addSupplyType',
						 data:$("#supplyTypeFrom").serialize(), 
					//	data:body.find('').serialize(),//表单数据 
						cache : false,
						dataType : 'json',
						success : function(data) {
							var status=null;
							if (data.success) {
								var msg=data.msg;
								//alert(msg);
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
								//alert(msg);
							}
						}
					})		 
       //  )
        return false;//如果不加这句，则ajax的回调函数不执行
    });
    
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