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
    
    
    
    
    
    var tableIns = table.render({
        elem: '#suppliesList',
        url : '/BackgroundMaterialManagementSystem/SuppliesController?methods=suppliesData',
        cellMinWidth : 95,
        page : true,
        limit : 15,
        limits : [10,15,20,25],
        id : "suppliesList",
        cols:[[
      	  {type: "checkbox", fixed:"left"},
      
      	  {type: 'numbers',title:'序号',align:"center"},
      	  {field: 'suppliesName', title: '资产名称', align:"center",width:120},
      	  {field: 'model', title: '型号', align:"center",width:120},
      	  {field: 'receiveTime', title: '收到日期',align:"center",templet :function (row){ return createTime(row.receiveTime);} ,width:150},
      	  {field: 'source', title: '来源',align:"center",width:120},
      	  {field: 'digest', title: '摘要',align:"center"},
      	  {field: 'unit', title: '计量单位',align:"center"},
      	  {field: 'number', title: '数量',align:"center"},
      	 // {field: 'inventory', title: '库存',align:"center"},
      	  {field: 'price', title: '单价',align:"center"},
      	  {field: 'money', title: '金额',align:"center"},
      	  {field: 'place', title: '位置',align:"center",width:180},
      	  {field: 'userName', title: '使用人',align:"center"},
      	  {field: 'note', title: '备注',align:"center"},
      	  {field: 'createTime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} ,width:150},
      	  {field: 'groupName', title: '组别',align:"center"},
      	  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
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
    function AddSupplies(edit){
        var index = layui.layer.open({
            title : "编辑物资类型",
            type : 2,
            area: ['700px', '680px'],
            skin: 'layui-layer-rim', //加上边框
            content : "addSupplies.jsp",
          //  content : "newAddMaterialType.jsp",
            success : function(layer, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                	body.find(".suppliesName").val(edit.suppliesName);
                    body.find(".model").val(edit.model);
                    body.find(".receiveTime").val(edit.receiveTime);
                    body.find(".source").val(edit.source);
                    body.find(".digest").val(edit.digest);
                    body.find(".number").val(edit.number);
                    body.find(".unit").val(edit.unit);
                    body.find(".price").val(edit.price);
                    body.find(".money").val(edit.money);
                    body.find(".place").val(edit.place);
                    body.find(".userName").val(edit.userName);
                    body.find(".groupNameValue").val(edit.groupName);
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
    $(".AddSuppliesBtn").click(function(){
    	AddSupplies();
    })
    
    //撤销
   $("#DelSuppliesBtn").click(function(){
       var checkStatus = table.checkStatus('suppliesList');
       var  data = checkStatus.data;
       if(data.length > 0) {
       	if(data.length > 1) {
       		 layer.msg("请只选中一条的数据");
       	}
       	else if(data.length=1){
       		
       		layer.confirm('确定要删除选择物资么 ', {
       			  btn: ['确定','取消'] //按钮
       			}, function(){
       				DelSupplies(JSON.stringify(data[0].id));
       			}, 
       			function(){
       				 layer.msg('你选择了取消');
       			});
       	}
           
       }else{
           layer.msg("请选择需要删除的物资");
       }
   });
   function DelSupplies(id){
   	$.ajax({
   	url:"/BackgroundMaterialManagementSystem/SuppliesController?methods=delSupplies&&id="+id,
   	type:"Post",
   	dataType:"json",
   	success:function(result){
   	var data = result.data; //返回的数据
   	var msg =result.msg;
   	layer.msg(msg);
   	table.reload('suppliesList');
   	}
   	});
   } 
   
   
   //重置搜索按钮
   $(".reset_btn").click(function(){
	   
	   $('#nameSelect').val("-1"); 
	   $('#groupNameId').val("-1"); 
   	form.render("select");  
   });
   
   
   
   $("#BatchUploadSupplyBtn").click(function(){
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
//	     	  layui.layer.full(index);
//	        // 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
//	         $(window).on("resize",function(){
//	             layui.layer.full(index);
//	         })           
	                    
	      }

	    //<a class="layui-btn layui-btn-normal layui-btn-normal BatchUploadSupplyBtn" id="BatchUploadSupplyBtn">批量上传</a>
	    $("#BatchUploadSupplyBtn").click(function(){
	    	BatchUpload();
		   }); 
	    function BatchUpload(){
	       
	    	
	      } 
	    
	    
   $(".search_btn").click(function(){  	
   	table.reload('suppliesList',{
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
   		groupIdName:$('#groupNameId').val(),
   		//搜索的关键字
   	 }
   	} 
   
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
    table.on('tool(suppliesList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	
        	AddSupplies(data);
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