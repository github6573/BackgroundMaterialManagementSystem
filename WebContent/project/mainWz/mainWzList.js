layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;


    
    SearchmaterialNameData();
    function SearchmaterialNameData(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/MainWzController?methods=nameList",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	//form.render("select");                           
        	var Select= document.getElementById("nameSelect");
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].name)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    } 
    
    
    
    
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
    
    //
    SearchPositionListData();
    function SearchPositionListData(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/DictionaryController?methods=getPositionList",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	//form.render("select");                           
        	var Select= document.getElementById("position");
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].name,date[i].name)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }   
    
    
	SearchUserListData(); 
	function SearchUserListData(){
		$.ajax({
		url:"/BackgroundMaterialManagementSystem/MainWzController?methods=UserList",
		type:"Post",
		dataType:"json",
    	success:function(result){
    	var date = result.data;    //返回的数据
    	form.render("select");                           
    	var Select= document.getElementById("userSelect");
    	//搜索框
    	for(var i=0;i<date.length;i++){
    		Select.options.add(new Option(date[i].userName,date[i].userName)); //这个兼容IE与firefox	
    	}
    	form.render("select");  
    	form.render();
    		}
    	});
}

	
//	 SearchmaterialNameData();
//	    function SearchmaterialNameData(){
//	    	$.ajax({
//	        	url:"/BackgroundMaterialManagementSystem/MainWzController?methods=nameList",
//	        	type:"Post",
//	        	dataType:"json",
//	        	success:function(result){
//	        	var date = result.data;    //返回的数据
//	        	//add_role_name给select定义的id
//	        	//form.render("select");                           
//	        	var Select= document.getElementById("nameSelect");
//	        	//搜索框
//	        	for(var i=0;i<date.length;i++){
//	        		Select.options.add(new Option(date[i].name,date[i].name)); //这个兼容IE与firefox
//	        	}
//	        	form.render("select");  
//	        	form.render();
//	        		}
//	        	});
//	    } 
	
	
	
    
    var tableIns = table.render({
        elem: '#mainWzList',
        url : '/BackgroundMaterialManagementSystem/MainWzController?methods=mainWzData',
        cellMinWidth : 95,
        page : true,
        limit : 15,
        limits : [10,15,20,25],
        id : "mainWzList",
        cols:[[
      	  {type: "checkbox", fixed:"left"},
      
      	  {type: 'numbers',title:'序号',align:"center"},
      	  {field: 'name', title: '资产名称', align:"center",width:120},
      	  {field: 'supplier', title: '供应商', align:"center",width:120},
      	  {field: 'model', title: '型号', align:"center",width:120},
      	  {field: 'unit', title: '计量单位',align:"center"},
      	  {field: 'number', title: '数量',align:"center"},
      	  {field: 'userName', title: '使用人',align:"center"},
      	  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
      	  {field: 'position', title: '位置',align:"center"},
      	  {field: 'note', title: '备注',align:"center"},
      	 // {field: 'groupId', title: '组别',align:"center"},    	
      	  {field: 'groupName', title: '组别名称',align:"center"},    	
    	  {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
      ]]
    });


  
  $(".refresh").on("click",function(){
     //  alert("刷新");
       //刷新当前的页面
       location.reload();
  });
    
    

   
    //添加修改物资类型
    function AddMainWz(edit){
        var index = layui.layer.open({
            title : "编辑物资类型",
            type : 2,
            area: ['700px', '500px'],
            skin: 'layui-layer-rim', //加上边框
            content : "addMainWz.jsp",
            success : function(layer, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                	body.find(".name").val(edit.name);
                	body.find(".supplier").val(edit.supplier);
                    body.find(".model").val(edit.model);
                    body.find(".unit").val(edit.unit);
                    body.find(".number").val(edit.number);
                    body.find(".userName").val(edit.userName);
                    body.find(".state").val(edit.state);
                    body.find(".position").val(edit.position);
                    body.find(".note").val(edit.note);
                    body.find(".groupId").val(edit.groupId);
                    body.find(".userName").val(edit.userName);
                    form.render();
                }

            }
        })
    }
    $(".AddMainWzBtn").click(function(){
    	AddMainWz();
    })
    
    //撤销
   $("#DelMainWzBtn").click(function(){
       var checkStatus = table.checkStatus('mainWzList');
       var  data = checkStatus.data;
       if(data.length > 0) {
       	if(data.length > 1) {
       		 layer.msg("请只选中一条的数据");
       	}
       	else if(data.length=1){
       		
       		layer.confirm('确定要删除选择物资么 ', {
       			  btn: ['确定','取消'] //按钮
       			}, function(){
       				DelMainWz(JSON.stringify(data[0].id));
       			}, 
       			function(){
       				 layer.msg('你选择了取消');
       			});
       	}
           
       }else{
           layer.msg("请选择需要删除的物资");
       }
   });
   function DelMainWz(id){
   	$.ajax({
   	url:"/BackgroundMaterialManagementSystem/MainWzController?methods=delMainWz&&id="+id,
   	type:"Post",
   	dataType:"json",
   	success:function(result){
   	var data = result.data; //返回的数据
   	var msg =result.msg;
   	layer.msg(msg);
   	table.reload('mainWzList');
   	}
   	});
   } 
   
   
   //重置搜索按钮
   $(".reset_btn").click(function(){
	   
	   $('#nameSelect').val("-1"); 
	   $('#groupNameId').val("-1"); 
	   $('#position').val("-1"); 
	   $('#userSelect').val("-1"); 
   	form.render("select");  
   });
   
   
   
   $("#BatchUploadMainWzBtn").click(function(){
	   	  batchuUpload();
	   });
	    
	    function batchuUpload(){
	     	var index = layui.layer.open({
	     		title : "批量上传模块",
	     		type : 2,
	     		area: ['350px', '300px'],
	     		content : "batchUpload.jsp",
	     		success : function(layer, index){
	     			var body = layui.layer.getChildFrame('body', index);
	           
	     		}
	     	});
	      }
	    
	    
	   
	    
	    
   $(".search_btn").click(function(){  	
   	table.reload('mainWzList',{
           method:'post',
           where:selectData(),
           page:{
           	curr:1
           }
       });
   });
   function selectData(){
   	return {
   		nameSelect:$('#nameSelect').val(),
   		groupIdSelect:$('#groupNameId').val(),
   		userSelect:$('#userSelect').val(),
   		positionSelect:$('#position').val()
   		//搜索的关键字
   	 }
   	} 
   

   
 //批量删除
 $("#DeleteMoreBtn").click(function(){
     var checkStatus = table.checkStatus('mainWzList'),
         data = checkStatus.data,
         newsId = [];
     if(data.length > 0) {
         for (var i in data) {
             newsId.push(data[i].id);
         //    alert("获得id："+data[i].id);
          //   alert("newsId："+newsId);
         }
         layer.confirm('确定要删除选中的物资么 ', {
  			  btn: ['确定','取消'] //按钮
  			}, function(){
  			//	alert(JSON.stringify(newsId));
  				$.ajax({
  					url:"/BackgroundMaterialManagementSystem/MainWzController?methods=DeleteMore",
  					type:"Post",
  					dataType:"json",
  					traditional: true,
  					data:{"WzId":JSON.stringify(newsId),"msg":"111"},
  			    	success:function(result){
  			    		var data = result.data; //返回的数据
  			    	   	var msg =result.msg;
  			    	   	layer.msg(msg);
  			    	   	table.reload('mainWzList');
  			    		
  			    		}
  			    	});
  				
  				
  				
  			}, 
  			function(){
  				 layer.msg('你选择了取消');
  			});
         
     }else{
         layer.msg("请选择需要删除的文章");
     }
 })

   
   
    //列表操作
    table.on('tool(mainWzList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	AddMainWz(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此文章？',{icon:3, title:'提示信息'},function(index){
                    tableIns.reload();
                    layer.close(index);
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });
    
    function createTime(v){
    	if(null!=v){
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
        	}
        	else{
        		str="";
        	}
            return str;
    }
    
    function state(s){
    	var st=null;
    	if(s=="1"){
    		st="正常使用";
    	}
    	else if (s=="2"){
    		st="库存";
    	}
    	else if (s=="0"){
    		st="禁用";
    	}
    	else{
    		st="异常";
    	}
    	return st;
    }
    

})