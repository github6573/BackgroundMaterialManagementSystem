layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
       
        
        selectTrueType();
        function selectTrueType(){
        	var typeValue= document.getElementById("typeValue").value; 
        	var idValue= document.getElementById("id").value; 
        	var typeSelect= document.getElementById("type");
        	//alert("typeNameValue:"+typeNameValue+"id:"+idValue);
        	if("-1"!=idValue){
        		//是编辑的话就替换第一条
        		$("#type").empty(); 
        		typeSelect.options.add(new Option(typeValue,typeValue));
        	}
        	//document.getElementById("type").value=typeValue; 
        	form.render("select");  
        	form.render();
        }
        
        
        
        
        
        selectTrueTypeName();
        function selectTrueTypeName(){
        	var typeNameValue= document.getElementById("typeNameValue").value; 
        	var idValue= document.getElementById("id").value; 
        	var typeNameSelect= document.getElementById("typeName");
        	//alert("typeNameValue:"+typeNameValue+"id:"+idValue);
        	
        	if("-1"!=idValue){
        		//是编辑的话就替换第一条
        		$("#typeName").empty(); 
        		typeNameSelect.options.add(new Option(typeNameValue,typeNameValue));
        	}
        	//document.getElementById("typeName").value=typeNameValue; 
        	form.render("select");  
        	form.render();
        }
      
        //获取物资类型 名称
        selectSupplyType(); 
        function selectSupplyType(){
        	$.ajax({
            	url:"/BackgroundMaterialManagementSystem/SupplyTypeController?methods=selectSupplyTypeDataByKeywords&&keywords=type",
            	type:"Post",
            	dataType:"json",
            	success:function(result){
            	var date = result.data;    //返回的数据
            	//add_role_name给select定义的id
            	form.render("select");                                // 刷性select，显示出数据
            	var typeSelect= document.getElementById("type");
            	//工号搜索框
            	for(var i=0;i<date.length;i++){
            		typeSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
            	}
            	form.render("select");  
            	form.render();
            		}
            	});
        }
        
   
        
        
        form.on('select(typeSelect)', function(data){
        	var typeSelected=$('#type').val();
        	var typeNameSelect= document.getElementById("typeName");
        	$("#typeName").empty(); 
        	typeNameSelect.options.add(new Option("直接选择物资名称","-1"));
        	//alert("现在选中的是："+typeSelected);
        	form.render("select");  
        	$.ajax({
            	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=selectSupplytypeNameDataByType&&type="+typeSelected,
            	type:"Post",
            	dataType:"json",
            	success:function(result){
            	var date = result.data;    //返回的数据
            	
            	//工号搜索框
            	for(var i=0;i<date.length;i++){
            		typeNameSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
            	}
            	form.render("select");  
            	form.render();
            		}
            	});
        	}
        );
        
        
//        //获取物资类型 名称
//        selectSupplyTypeName(); 
//        function selectSupplyTypeName(){
//        	$.ajax({
//            	url:"/BackgroundMaterialManagementSystem/SupplyTypeController?methods=selectSupplyTypeDataByKeywords&&keywords=typeName",
//            	type:"Post",
//            	dataType:"json",
//            	success:function(result){
//            	var date = result.data;    //返回的数据
//            	//add_role_name给select定义的id
//            	form.render("select");                                // 刷性select，显示出数据
//            	var typeNameSelect= document.getElementById("typeName");
//            	//工号搜索框
//            	for(var i=0;i<date.length;i++){
//            		typeNameSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
//            	}
//            	form.render("select");  
//            	form.render();
//            		}
//            	});
//        }
//        
        
  
        
        
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

    
    


    form.on("submit(AddSupply)",function(data){
        //截取文章内容中的一部分文字放入文章摘要
       // var abstract = layedit.getText(editIndex).substring(0,50);
    	
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	
        // 实际使用时的提交信息
       //  $.post(
        		 $.ajax({
					//	type : 'post',
						url : '/BackgroundMaterialManagementSystem/SupplyController?methods=addSupply',
						 data:$("#supplyFrom").serialize(), 
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