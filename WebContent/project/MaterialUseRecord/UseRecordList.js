layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    laydate.render({
        elem: '#TimeScope' //指定元素
        ,range: true
      });
    
 
    
    SearchmaterialNameData();
    function SearchmaterialNameData(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/UseRecordController?methods=getMaterialNameList",
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
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].extraField,date[i].id)); //这个兼容IE与firefox
        		
        	}
        	
        	
        	
        	
        	
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    

    
    
    
    
   

    //新闻列表
    var tableIns = table.render({
        elem: '#UseRecordList',
       // url : '../../json/newsList.json',
        url : '/BackgroundMaterialManagementSystem/UseRecordController?methods=UserRecordData',
       // where:{id:idValue,name:nameValue,workNumber:workNumberValue,tel:telvalue,department:departmentValue},
        cellMinWidth : 95,
        page : true,
     //   height : "full-125",
        limit : 15,
        limits : [10,15,20,25],
        id : "UseRecordList",
        cols:[[
        	  {type: "checkbox", fixed:"left"},
//        	  {field: 'id', title: 'ID', align:"center",width:100},
        	  {type: 'numbers',title:'序号',align:"center"},
        	  {field: 'name', title: '物资名称', align:"center",width:100},
        	  {field: 'number', title: '数量',align:"center",width:100},
        	  {field: 'unit', title: '单位',align:"center",width:100},
        	  {field: 'createTime', title: '创建时间',align:"center",width:100,templet :function (row){ return createTime(row.createTime);},width:140},       	  
        	//  {field: 'userId', title: '使用者id',align:"center"},
        	  {field: 'userName', title: '使用者名称',align:"center"},
        	  {field: 'note', title: '备注',align:"center"},
        	  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
        	  {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });


  
    
    
    
    
    
    
    
    

  $(".refresh").on("click",function(){
       //alert("刷新");
       //刷新当前的页面
       location.reload();
  });
    
 
  $(".reset_btn").click(function(){
  	$("#nameSelect").val("-1"); 
 	$("#userSelect").val("-1"); 
 	$("#TimeScope").val(""); 
  	form.render("select");  
  });
    //添加使用记录
    function addUseRecord(edit){
        var index = layui.layer.open({
            title : "编辑人员",
            type : 2,
            content : "addUseRecord.jsp",
            area: ['360px', '530px'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".materialId").val(edit.name);
                    body.find(".number").val(edit.number);
                    body.find(".unit").val(edit.unit);
                    body.find(".userId").val(edit.userId);
                    body.find(".note").val(edit.note);
                    form.render();
                	}
//                setTimeout(function(){
//                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
//                        tips: 3
//                    });
//                },500)
            }
        })
       // layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
       // $(window).on("resize",function(){
       //     layui.layer.full(index);
       // })
    }
    $("#addUseRecordBtn").click(function(){
    	addUseRecord();
    });
    
    $("#delUseRecordBtn").click(function(){
        var checkStatus = table.checkStatus('UseRecordList');
        var  data = checkStatus.data;
        if(data.length > 0) {
        	if(data.length > 1) {
        		 layer.msg("请只选中一条的数据");
        	}
        	else if(data.length=1){
        		
        		layer.confirm('确定要删除选择的记录么 ', {
        			  btn: ['确定','取消'] //按钮
        			}, function(){
        				DelUseRecord(JSON.stringify(data[0].id));
        			}, 
        			function(){
        				 layer.msg('你选择了取消');
        			});
        	}
            
        }else{
            layer.msg("请选择需要删除的物资");
        }
    });
    function DelUseRecord(id){
    	$.ajax({
    	url:"/BackgroundMaterialManagementSystem/UseRecordController?methods=delUseRecord&&id="+id,
    	type:"Post",
    	dataType:"json",
    	success:function(result){
    	var data = result.data; //返回的数据
    	var msg =result.msg;
    	layer.msg(msg);
    	table.reload('UseRecordList');
    	}
    	});
    } 
    $(".search_btn").click(function(){  	
//    	var nameValue= $("#nameSelect").val(); 
//    	var workNumberValue= $("#workNumberSelect").val(); 
//    	var telValue= $("#telSelect").val();
//    	var departmentValue= $("#departmentSelect").val();
 //   	alert("开始搜索"+"nameValue:"+nameValue+"workNumberValue:"+workNumberValue+"telValue:"+telValue+"departmentValue:"+departmentValue);
    	table.reload('UseRecordList',{
            method:'post',
            where:selectData(),
            page:{
            	curr:1
            }
        });
    });

    function selectData(){
    	return {
    		name:$('#nameSelect').val(),
       		userId:$('#userSelect').val(),
       		TimeScope:$('#TimeScope').val(),
    		//搜索的关键字
    	 }
    	} 
    
    //列表操作
    table.on('tool(UseRecordList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addUseRecord(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此文章？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
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
    
    function state(s){
    	var st=null;
    	if(s=="1"){
    		st="正常";
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