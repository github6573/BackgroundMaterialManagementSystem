layui.use(['form','layer','layedit','laydate','upload'],function(){
        var form = layui.form,
        
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
 
   //加载表中可以选择的数据
       
//        function GetSelectList(keyword){
//        	
//        	var idValue= document.getElementById("id").value; 
//        	var keywordSelect= document.getElementById(keyword);
//        	var keywordValue= document.getElementById(keyword+"Value").value; 
//        	//alert("typeNameValue:"+typeNameValue+"id:"+idValue);
//        	if("-1"!=idValue){
//        		//是编辑的话就替换第一条
//        		$("#"+keyword).empty(); 
//        		keywordSelect.options.add(new Option(keywordValue,keywordValue));
//        	}
//        	//document.getElementById("type").value=typeValue; 
//        	form.render("select");  
//        	form.render();
//        }
//        GetSelectList("supplyType");
//        GetSelectList("supplyTypeName");
//        GetSelectList("model");
        

//        //获取物资类型 名称
//        selectSupplyType(); 
//        function selectSupplyType(){
//        	$.ajax({
//            	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=SelectSupplyAllDataByKeywords&&keywords=type",
//            	type:"Post",
//            	dataType:"json",
//            	success:function(result){
//            	var date = result.data;    //返回的数据
//            	//add_role_name给select定义的id
//            	form.render("select");                                // 刷性select，显示出数据
//            	var typeSelect= document.getElementById("supplyType");
//            	//工号搜索框
//            	for(var i=0;i<date.length;i++){
//            		typeSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
//            	}
//            	form.render("select");  
//            	form.render();
//            		}
//            	});
//        }
//        
//        form.on('select(supplyTypeSelect)', function(data){
//        	var supplyTypeSelect=$('#supplyType').val();
//        	//alert("supplyType:"+supplyTypeSelect);
//        	var supplyTypeNameSelect= document.getElementById("supplyTypeName");
//        	var modelSelect= document.getElementById("model");
//        	$("#supplyTypeName").empty(); 
//        	$("#model").empty(); 
//        	supplyTypeNameSelect.options.add(new Option("直接选择物资名称","-1"));
//        	modelSelect.options.add(new Option("直接选择物资型号","-1"));
//        	//alert("现在选中的是："+typeSelected);
//        	form.render("select");  
//        	$.ajax({
//            	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=selectSupplyNameDataByType&&type="+supplyTypeSelect,
//            	type:"Post",
//            	dataType:"json",
//            	success:function(result){
//            	var date = result.data;    //返回的数据
//            	
//            	//工号搜索框
//            	for(var i=0;i<date.length;i++){
//            		supplyTypeNameSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
//            	}
//            	form.render("select");  
//            	form.render();
//            		}
//            	});
//        	}
//        );
//        
//        
//        
//        form.on('select(supplyTypeNameSelect)', function(data){
//        	var supplyTypeNameSelect=$('#supplyTypeName').val();
//        	//alert("supplyTypeName:"+supplyTypeNameSelect);
//        	var modelSelect= document.getElementById("model");
//        	$("#model").empty(); 
//        	modelSelect.options.add(new Option("直接选择物资型号","-1"));
//        	//alert("现在选中的是："+typeSelected);
//        	form.render("select");  
//        	$.ajax({
//            	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=selectSupplyModelDataByTypeName&&typeName="+supplyTypeNameSelect,
//            	type:"Post",
//            	dataType:"json",
//            	success:function(result){
//            	var date = result.data;    //返回的数据
//            	
//            	//工号搜索框
//            	for(var i=0;i<date.length;i++){
//            		modelSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
//            	}
//            	form.render("select");  
//            	form.render();
//            		}
//            	});
//        	}
//        );
        
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

    
    


    form.on("submit(AddSupplyUserRecordFrom)",function(data){
        //截取文章内容中的一部分文字放入文章摘要
       // var abstract = layedit.getText(editIndex).substring(0,50);
    	
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	
        // 实际使用时的提交信息
       //  $.post(
        		 $.ajax({
					//	type : 'post',
						url : '/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=addSupplyUserRecordData',
						data:$("#supplyUserRecordFrom").serialize(), 
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