layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //获取物资类型  名称
    selectSupplyType(); 
    function selectSupplyType(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=SelectSupplyAllDataByKeywords&&keywords=type",
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
    
    
    
    //获取物资类型  名称
    selectSupplyTypeName(); 
    function selectSupplyTypeName(){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=SelectSupplyAllDataByKeywords&&keywords=typeName",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	form.render("select");                                // 刷性select，显示出数据
        	var typeNameSelect= document.getElementById("typeName");
        	//工号搜索框
        	for(var i=0;i<date.length;i++){
        		typeNameSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    } 
    
    selectSupplyModel(); 
    function selectSupplyModel(){
    	$.ajax({															  
        	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=SelectSupplyAllDataByKeywords&&keywords=model",
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	form.render("select");                                // 刷性select，显示出数据
        	var modelSelect= document.getElementById("model");
        	//工号搜索框
        	for(var i=0;i<date.length;i++){
        		modelSelect.options.add(new Option(date[i].typeName,date[i].typeName)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    } 
    
    
    
    //新闻列表
    var tableIns = table.render({
        elem: '#supplyList',
        url : '/BackgroundMaterialManagementSystem/SupplyController?methods=supplyData',
        cellMinWidth : 95,
        page : true,
        limit : 15,
        limits : [10,15,20,25],
        id : "supplyList",
        cols:[[
      	  {type: "checkbox", fixed:"left"},
      	  {field: 'id', title: 'ID', align:"center",width:60},
      	  {field: 'type', title: '类型', align:"center",width:100},
      	  {field: 'typeName', title: '物资名称',align:"center"},
      	  {field: 'model', title: '物资型号',align:"center"},
      	  {field: 'number', title: '物资数量',align:"center"},
      	  {field: 'createtime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} },
      	  {field: 'note', title: '附加信息',align:"center"},
      	//  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
      	  {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
      ]]
    });

//    //是否置顶
//    form.on('switch(newsTop)', function(data){
//        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
//        setTimeout(function(){
//            layer.close(index);
//            if(data.elem.checked){
//                layer.msg("置顶成功！");
//            }else{
//                layer.msg("取消置顶成功！");
//            }
//        },500);
//    })
//
//    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
//    $(".search_btn").on("click",function(){
//        if($(".searchVal").val() != ''){
//            table.reload("newsListTable",{
//                page: {
//                    curr: 1 //重新从第 1 页开始
//                },
//                where: {
//                    key: $(".searchVal").val()  //搜索的关键字
//                }
//            })
//        }else{
//            layer.msg("请输入搜索的内容");
//        }
//    });
//
  //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
  $(".refresh").on("click",function(){
     //  alert("刷新");
       //刷新当前的页面
       location.reload();
  });
    
    
//    //修改物资类型
//    function addNews(edit){
//        var index = layui.layer.open({
//            title : "编辑人员",
//            type : 2,
//            content : "addSupply.jsp",
//            success : function(layero, index){
//                var body = layui.layer.getChildFrame('body', index);
//                if(edit){
//                	body.find(".id").val(edit.id);
//                 //   body.find(".department").val(edit.department);
//                 //   body.find(".workNumber").val(edit.workNumber);
//                 //   body.find(".userName").val(edit.userName);
//                 //   body.find(".createTime").val(edit.createTime);
//                 //   body.find(".permissions").val(edit.permissions);
//                 //   body.find(".state").val(edit.state);
//                 //  body.find(".openness input[name='openness'][title='"+edit.newsLook+"']").prop("checked","checked");
//                 // body.find(".newsTop input[name='newsTop']").prop("checked",edit.newsTop);
//                    form.render();
//                }
//                setTimeout(function(){
//                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
//                        tips: 3
//                    });
//                },500)
//            }
//        })
//        layui.layer.full(index);
//        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
//        $(window).on("resize",function(){
//            layui.layer.full(index);
//        })
//    }
//    $(".addNews_btn").click(function(){
//        addNews();
//    })
    
     
    //修改物资类型
    function AddSupply(edit){
        var index = layui.layer.open({
            title : "编辑物资类型",
            type : 2,
            area: ['360px', '460px'],
            skin: 'layui-layer-rim', //加上边框
            content : "addSupply.jsp",
          //  content : "newAddMaterialType.jsp",
            success : function(layer, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                	body.find("#typeValue").val(edit.type);
                    body.find("#typeNameValue").val(edit.typeName);
                    body.find("#typeName").val(edit.typeName);
                    body.find(".model").val(edit.model);
                    body.find(".number").val(edit.number);
                    body.find(".note").val(edit.note);
//              	  {field: 'id', title: 'ID', align:"center",width:60},
//              	  {field: 'typeName', title: '物资名称',align:"center"},
//              	  {field: 'model', title: '物资型号',align:"center"},
//              	  {field: 'number', title: '物资数量',align:"center"},
//              	  {field: 'createtime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} },
//              	  {field: 'note', title: '附加信息',align:"center"},
                    form.render();
                }

            }
        })
    }
    $(".AddSupplyBtn").click(function(){
    	AddSupply();
    })
    
    //撤销
   $("#DelSupplyBtn").click(function(){
       var checkStatus = table.checkStatus('supplyList');
       var  data = checkStatus.data;
       if(data.length > 0) {
       	if(data.length > 1) {
       		 layer.msg("请只选中一条的数据");
       	}
       	else if(data.length=1){
       		
       		layer.confirm('确定要删除选择物资么 ', {
       			  btn: ['确定','取消'] //按钮
       			}, function(){
       				DelSupply(JSON.stringify(data[0].id));
       			}, 
       			function(){
       				 layer.msg('你选择了取消');
       			});
       	}
           
       }else{
           layer.msg("请选择需要删除的数据");
       }
   });
   function DelSupply(id){
   	$.ajax({
   	url:"/BackgroundMaterialManagementSystem/SupplyController?methods=delSupply&&id="+id,
   	type:"Post",
   	dataType:"json",
   	success:function(result){
   	var data = result.data; //返回的数据
   	var msg =result.msg;
   	layer.msg(msg);
   	table.reload('supplyList');
   	}
   	});
   } 
   
   
   //重置搜索按钮
   $(".reset_btn").click(function(){
	   
   	$("#type").val("-1"); 
   	$("#model").val("-1"); 
   	$("#typeName").val("-1"); 
   	form.render("select");  
   });
   
   
   
   $("#BatchUploadSupplyBtn").click(function(){
	   	  batchuUpload();
	   });
	    
	    function batchuUpload(){
	     	var index = layui.layer.open({
	     		title : "批量上传模块",
	     		type : 2,
	     		//area: ['350px', '300px'],
	     		content : "batchUpload.jsp",
	     		success : function(layer, index){
	     			var body = layui.layer.getChildFrame('body', index);
	           
	     		}
	     	});
	     	  layui.layer.full(index);
	        // 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
	         $(window).on("resize",function(){
	             layui.layer.full(index);
	         })           
	                    
	      }
   //开启搜索功能
   $(".search_btn").click(function(){
     //  alert("搜索功能开启");
	  var typeValue=$("#type").val();
   	  var typeNameValue=$("#typeName").val();
   	  var modelValue=$("#model").val();
  // 	return;
       var tableIns = table.render({
           elem: '#supplyList',																																													
           url : '/BackgroundMaterialManagementSystem/SupplyController?methods=supplyDataBySelect&&type='+typeValue+'&&typeName='+typeNameValue+'&&model='+modelValue,
           cellMinWidth : 95,
           page : true,
           limit : 15,
           limits : [10,15,20,25],
           id : "supplyList",
           cols:[[
           	  {type: "checkbox", fixed:"left"},
           	  {field: 'id', title: 'ID', align:"center",width:60},
           	  {field: 'type', title: '类型', align:"center",width:100},
           	  {field: 'typeName', title: '物资名称',align:"center"},
           	  {field: 'model', title: '物资型号',align:"center"},
           	  {field: 'number', title: '物资数量',align:"center"},
           	  {field: 'createtime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} },
           	  {field: 'note', title: '附加信息',align:"center"},
           	//  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
           	  {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
           ]]
       });
       form.render();  
   });
   
//
//    //批量删除
//    $(".delAll_btn").click(function(){
//        var checkStatus = table.checkStatus('newsListTable'),
//            data = checkStatus.data,
//            newsId = [];
//        if(data.length > 0) {
//            for (var i in data) {
//                newsId.push(data[i].newsId);
//            }
//            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
//                // $.get("删除文章接口",{
//                //     newsId : newsId  //将需要删除的newsId作为参数传入
//                // },function(data){
//                tableIns.reload();
//                layer.close(index);
//                // })
//            })
//        }else{
//            layer.msg("请选择需要删除的文章");
//        }
//    })
//
    //列表操作
    table.on('tool(supplyList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	AddSupply(data);
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