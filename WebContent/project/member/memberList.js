layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

 
    
    
    
    SearchListData("name"); 
    SearchListData("workNumber"); 
    SearchListData("tel"); 
    SearchListData("department"); 
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
        	var selectId=type+"Select";
        	var Select= document.getElementById(selectId);
        	//搜索框
        	for(var i=0;i<date.length;i++){
        		Select.options.add(new Option(date[i].extraField,date[i].extraField)); //这个兼容IE与firefox
        	}
        	form.render("select");  
        	form.render();
        		}
        	});
    }  
    

    
    
    
    
   
//	var nameValue= $("#name").val(); 
//	var workNumberValue= $("#workNumber").val(); 
//	var telvalue= $("#tel").val();
//	var departmentValue= $("#department").val();
    //新闻列表
    var tableIns = table.render({
        elem: '#memberList',
       // url : '../../json/newsList.json',
        url : '/BackgroundMaterialManagementSystem/MemberController?methods=memberData',
       // where:{id:idValue,name:nameValue,workNumber:workNumberValue,tel:telvalue,department:departmentValue},
        cellMinWidth : 95,
        page : true,
     //   height : "full-125",
        limit : 15,
        limits : [10,15,20,25],
        id : "memberList",
        cols:[[
        	  {type: "checkbox", fixed:"left"},
//        	  {field: 'id', title: 'ID', align:"center",width:100},
        	  {type: 'numbers',title:'序号',align:"center"},
        	  {field: 'name', title: '姓名', align:"center",width:100},
        	  {field: 'workNumber', title: '用户账号',align:"center",width:100},
        	  {field: 'tel', title: '电话',align:"center"},
        	  {field: 'department', title: '部门',align:"center"},
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
  	//alert("开始重置按钮");
  	$("#nameSelect").val("-1"); 
  	$("#workNumberSelect").val("-1"); 
  	$("#telSelect").val("-1"); 
  	$("#departmentSelect").val("-1"); 
  	form.render("select");  
  });
    //添加文章
    function addMember(edit){
        var index = layui.layer.open({
            title : "编辑人员",
            type : 2,
            content : "addMember.jsp",
            area: ['360px', '460px'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".name").val(edit.name);
                    body.find(".workNumber").val(edit.workNumber);
                    body.find(".tel").val(edit.tel);
                    body.find(".department").val(edit.department);
                    form.render();
                	}
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
       // layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
       // $(window).on("resize",function(){
       //     layui.layer.full(index);
       // })
    }
    $("#addMemberBtn").click(function(){
    	addMember();
    });
    
    $("#delMemberBtn").click(function(){
        var checkStatus = table.checkStatus('memberList');
        var  data = checkStatus.data;
        if(data.length > 0) {
        	if(data.length > 1) {
        		 layer.msg("请只选中一条的数据");
        	}
        	else if(data.length=1){
        		
        		layer.confirm('确定要删除选择的人员么 ', {
        			  btn: ['确定','取消'] //按钮
        			}, function(){
        				DeldelMember(JSON.stringify(data[0].id));
        			}, 
        			function(){
        				 layer.msg('你选择了取消');
        			});
        	}
            
        }else{
            layer.msg("请选择需要删除的物资");
        }
    });
    function DeldelMember(id){
    	$.ajax({
    	url:"/BackgroundMaterialManagementSystem/MemberController?methods=delmember&&id="+id,
    	type:"Post",
    	dataType:"json",
    	success:function(result){
    	var data = result.data; //返回的数据
    	var msg =result.msg;
    	layer.msg(msg);
    	table.reload('memberList');
    	}
    	});
    } 
    $(".search_btn").click(function(){  	
//    	var nameValue= $("#nameSelect").val(); 
//    	var workNumberValue= $("#workNumberSelect").val(); 
//    	var telValue= $("#telSelect").val();
//    	var departmentValue= $("#departmentSelect").val();
 //   	alert("开始搜索"+"nameValue:"+nameValue+"workNumberValue:"+workNumberValue+"telValue:"+telValue+"departmentValue:"+departmentValue);
    	table.reload('memberList',{
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
    		workNumber:$('#workNumberSelect').val(),
    		tel:$('#telSelect').val(),
    		department:$('#departmentSelect').val()
    		//搜索的关键字
    	 }
    	} 
    
    //列表操作
    table.on('tool(memberList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addMember(data);
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