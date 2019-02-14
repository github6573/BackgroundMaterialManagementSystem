layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#logList',
       // url : '../../json/newsList.json',
        url : '/BackgroundMaterialManagementSystem/LogController?methods=logData',
       // pages:25,
       
        
        cellMinWidth : 95,
        page : true,
     //   height : "full-125",
        limit : 15,
        limits : [10,15,20,25],
        id : "logList",
//        cols : [[
//            {type: "checkbox", fixed:"left", width:50},
//            {field: 'newsId', title: 'ID', width:60, align:"center"},
//            {field: 'newsName', title: '文章标题', width:350},
//            {field: 'newsAuthor', title: '发布者', align:'center'},
//            {field: 'newsStatus', title: '发布状态',  align:'center',templet:"#newsStatus"},
//            {field: 'newsLook', title: '浏览权限', align:'center'},
//            {field: 'newsTop', title: '是否置顶', align:'center', templet:function(d){
//                return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+d.newsTop+'>'
//            }},
//            {field: 'newsTime', title: '发布时间', align:'center', minWidth:110, templet:function(d){
//                return d.newsTime.substring(0,10);
//            }},
//            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
//        ]]
        cols:[[
        	
        	 // {type: "checkbox", fixed:"left"},
        	  {type: 'numbers',title:'序号',align:"center"},
        	  {field: 'id', title: 'ID', align:"center",width:100},
        	  {field: 'userId', title: '用户id',align:"center",width:100},
        	  {field: 'userName', title: '用户名称',align:"center"},
        	  {field: 'logTime', title: '创建时间',align:"center",templet :function (row){ return createTime(row.logTime)}},
        	  {field: 'content', title: '内容',align:"center"},
        	  {field: 'state', title: '状态',align:"center",templet :function (row){ return state(row.state);}}
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
       alert("刷新");
       //刷新当前的页面
       location.reload();
  });
    
    
    //添加文章
    function addNews(edit){
        var index = layui.layer.open({
            title : "编辑人员",
            type : 2,
            content : "newAdd2.jsp",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);
                    body.find(".department").val(edit.department);
                    body.find(".workNumber").val(edit.workNumber);
                    body.find(".userName").val(edit.userName);
                    body.find(".createTime").val(edit.createTime);
                    body.find(".permissions").val(edit.permissions);
                    body.find(".state").val(edit.state);
                  //  body.find(".openness input[name='openness'][title='"+edit.newsLook+"']").prop("checked","checked");
                 // body.find(".newsTop input[name='newsTop']").prop("checked",edit.newsTop);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".addNews_btn").click(function(){
        addNews();
    })
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
    table.on('tool(logList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addNews(data);
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