layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    
    
    
    //获取5个搜索框的列表
   // selectGetByKey(); 
    function selectGetByKey(keyword){
    	$.ajax({
        	url:"/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=selectSupplyUserRecordByKeywords&&keywords="+keyword,
        	type:"Post",
        	dataType:"json",
        	success:function(result){
        	var date = result.data;    //返回的数据
        	//add_role_name给select定义的id
        	form.render("select");                                // 刷性select，显示出数据
        //	var newSelectValue= keyword+"Select";
        	var newSelect= document.getElementById(keyword);
        	//工号搜索框
        	for(var i=0;i<date.length;i++){
        		newSelect.options.add(new Option(date[i].supplyTypeName,date[i].supplyTypeName)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    } 
    selectGetByKey("department");
    selectGetByKey("userName");
 //   selectGetByKey("supplyType");
    selectGetByKey("supplyTypeName");
    selectGetByKey("model");
    
      var departmentValue=$("#department").val();
 	  var userNameValue=$("#userName").val();
 	  var supplyTypeNameValue=$("#supplyTypeName").val();
 	  var modelValue=$("#model").val();
    //新闻列表
    var tableIns = table.render({
        elem: '#supplyUserRecordList',
//        url : '/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=supplyUserRecordData',
        url : '/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=supplyUserRecordDataBySelect&&department='+departmentValue+'&&userName='+userNameValue+'&&supplyTypeName='+supplyTypeNameValue+'&&model='+modelValue,
        cellMinWidth : 95,
        page : true,
        limit : 15,
        limits : [10,15,20,25],
        id : "supplyUserRecordList",
        cols:[[
      	  {type: "checkbox", fixed:"left"},
      	  {field: 'id', title: 'ID', align:"center",width:60},
      	  {field: 'department', title: '部门',align:"center"},
      	  {field: 'userName', title: '使用者',align:"center"},
      	 // {field: 'supplyType', title: '物品名称',align:"center"},
      	  {field: 'supplyTypeName', title: '物品名称',align:"center"},
      	  {field: 'model', title: '型号',align:"center"},
      	  {field: 'physicalAddress', title: '物理地址',align:"center"},
      	  {field: 'note1', title: '备注一',align:"center"},
      	  {field: 'note2', title: '备注二',align:"center"},
      	  {field: 'createTime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} },
      	 
      	 // {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
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
    
    
    //修改物资类型
    function AddSupplyUserRecord(edit){
        var index = layui.layer.open({
            title : "编辑物资使用",
            type : 2,
            area: ['300px', '620px'],
            content : "AddSupplyUserRecord.jsp",
            success : function(layer, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                   // body.find(".department").val(edit.department);
                   // body.find(".workNumber").val(edit.workNumber);
                  //  body.find(".supplyTypeValue").val(edit.supplyType);
                    body.find(".supplyTypeNameValue").val(edit.supplyTypeName);
                    body.find(".modelValue").val(edit.model);
                    body.find(".department").val(edit.department);
                    body.find(".userName").val(edit.userName);
                    body.find(".physicalAddressValue").val(edit.physicalAddress);
                    body.find(".note1Value").val(edit.note1);
                    body.find(".note2Value").val(edit.note2);
                   // body.find(".createTime").val(edit.createTime);
                   // body.find(".permissions").val(edit.permissions);
                 //   body.find(".state").val(edit.state);
                 //  body.find(".openness input[name='openness'][title='"+edit.newsLook+"']").prop("checked","checked");
                 // body.find(".newsTop input[name='newsTop']").prop("checked",edit.newsTop);
                    form.render();
                }
//                setTimeout(function(){
//                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
//                        tips: 3
//                    });
//                },500)
            }
        })
//        layui.layer.full(index);
//        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
//        $(window).on("resize",function(){
//            layui.layer.full(index);
//        })
    }
    $("#AddSupplyUserRecordBtn").click(function(){
    	AddSupplyUserRecord();
    })
    
    //重置搜索框
       $(".reset_btn").click(function(){
    	$("#department").val("-1"); 
    	$("#userName").val("-1"); 
    	$("#supplyType").val("-1"); 
    	$("#supplyTypeName").val("-1"); 
    	$("#model").val("-1"); 
    	form.render("select");  
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
    table.on('tool(supplyUserRecordList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	AddSupplyUserRecord(data);
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
    
    //撤销
   $("#DelSupplyUserRecordBtn").click(function(){
       var checkStatus = table.checkStatus('supplyUserRecordList');
       var  data = checkStatus.data;
       if(data.length > 0) {
       	if(data.length > 1) {
       		 layer.msg("请只选中一条的数据");
       	}
       	else if(data.length=1){
       		
       		layer.confirm('确定要删除选择物资类型么 ', {
       			  btn: ['确定','取消'] //按钮
       			}, function(){
       				//alert(JSON.stringify(data[0].id)+"----"+JSON.stringify(data[0].state));
       				DelSupplyUseRecord(JSON.stringify(data[0].id));
       			}, 
       			function(){
       				 layer.msg('你选择了取消');
       			});
       	}
           
       }else{
           layer.msg("请选择需要删除的数据");
       }
   });
   
   
   function DelSupplyUseRecord(id){
   	$.ajax({
   	url:"/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=delSupplyUserRecord&&id="+id,
   	type:"Post",
   	dataType:"json",
   	success:function(result){
   	var data = result.data; //返回的数据
   	var msg =result.msg;
   	layer.msg(msg);
   	table.reload('supplyUserRecordList');
   	}
   	});
   }  
    
   
   //开启搜索功能
   $(".search_btn").click(function(){
     //  alert("搜索功能开启");
   	  var departmentValue=$("#department").val();
   	  var userNameValue=$("#userName").val();
   //	  var supplyTypeValue=$("#supplyType").val();
   	  var supplyTypeNameValue=$("#supplyTypeName").val();
   	  var modelValue=$("#model").val();
  // 	return;
       var tableIns = table.render({
           elem: '#supplyUserRecordList',																																													
           url : '/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=supplyUserRecordDataBySelect&&department='+departmentValue+'&&userName='+userNameValue+'&&supplyTypeName='+supplyTypeNameValue+'&&model='+modelValue,
           cellMinWidth : 95,
           page : true,
           limit : 15,
           limits : [10,15,20,25],
           id : "supplyUserRecordList",
           cols:[[
           	  {type: "checkbox", fixed:"left"},
           	  {field: 'id', title: 'ID', align:"center",width:60},
           	  {field: 'department', title: '部门',align:"center"},
           	  {field: 'userName', title: '使用者',align:"center"},
           	 // {field: 'supplyType', title: '物品名称',align:"center"},
           	  {field: 'supplyTypeName', title: '物品名称',align:"center"},
           	  {field: 'model', title: '型号',align:"center"},
           	  {field: 'physicalAddress', title: '物理地址',align:"center"},
           	  {field: 'note1', title: '备注一',align:"center"},
           	  {field: 'note2', title: '备注二',align:"center"},
           	  {field: 'createTime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.createTime);} },
           	 
           	 // {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}},
           	  {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
           ]]
       });
       form.render();  
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
    
  //批量上传
    
    $("#BatchUploadSupplyUserRecordBtn").click(function(){
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
    
    //批量导出
    $(".BatchExportSupplyUserRecordBtn").on("click",function(){
    	batchExport();
    });
    function batchExport(){
    	//遮盖动画
    	var msg;
		var fileName;
		var path;
    	var index = layer.msg('生成Excel中，请稍候',{icon: 16,time:false,shade:0.8});
    	
    //	
    //	layer.msg("1");
    	//layer.close(index);
    	//访问接口  生成  excel  返回的json里包含json
    	 $.ajax({
					url : '/BackgroundMaterialManagementSystem/SupplyUseRecordController?methods=makeExcel',
					data:'', 
				//	data:body.find('').serialize(),//表单数据 
					cache : false,
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							layer.close(index);
							msg=data.msg;
							path=data.path;
							
						//	path="/BackgroundMaterialManagementSystem/project/outExcel/"+fileName+".xls";
							//layer.msg("msg:"+msg+" fileName:"+fileName+" path:"+path);
							 layer.open({
								 	//type: 1,
								   shade: false,
							        title: msg,
							        content: '<a class="link" >点击此处下载Excel</a>',
							        success: function (layero, index) {
							            $(layero).find('.link').click(function () {
							                layer.close(index);
//							                window.location.href ='http://111.231.206.138:18080/BackgroundMaterialManagementSystem/project/outExcel/UserRecorditem.xls';
							                window.location.href ='/BackgroundMaterialManagementSystem/project/outExcel/UserRecorditem.xls';
							               // window.location.href = path;
							            
							            })
							        }
							    })
							
							
//							layer.confirm(msg+' 是否开始下载文件 '+fileName, {
//								  btn: ['确定','取消'] //按钮
//								}, function(){
//								 
//									//本地获取文件夹
//								  //layer.msg('的确很重要', {icon: 1});
//								}, 
//								function(){
//								  layer.msg('也可以这样', {
//								    time: 20000, //20s后自动关闭
//								    btn: ['明白了', '知道了']
//								  });
//								});
							
							
							
//							 setTimeout(function(){
//					         top.layer.close(index);
//					         top.layer.msg(msg);
//					          layer.closeAll("iframe");
//					            //刷新父页面
//					            parent.location.reload();
//					        },500);									
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
				});
				
				

    	
    	
    }
})