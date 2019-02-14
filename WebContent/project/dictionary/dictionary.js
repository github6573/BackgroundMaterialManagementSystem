layui
		.use(
				[ 'form', 'layer', 'laydate', 'table', 'laytpl' ],
				function() {
					var form = layui.form, layer = parent.layer === undefined ? layui.layer
							: top.layer, $ = layui.jquery, laydate = layui.laydate, laytpl = layui.laytpl, table = layui.table;
					// 新闻列表
					var tableIns = table
							.render({
								elem : '#dictionaryList',
								// url : '../../json/newsList.json',
								url : '/BackgroundMaterialManagementSystem/DictionaryController?methods=dictionaryData',
								cellMinWidth : 95,
								page : true,
								limit : 15,
								limits : [ 10, 15, 20, 25 ],
								id : "dictionaryList",
								cols : [ [ {
									type : "checkbox",
									fixed : "left"
								}, {
									type : 'numbers',
									title : '序号',
									align : "center"
								},
								// {field: 'id', title: 'ID',
								// align:"center",width:100},
//								{
//									field : 'type',
//									title : '类型',
//									align : "center",
//									width : 100},
								{
									field : 'chineseName',
									title : '类型',
									align : "center"
								},
								{
									field : 'value',
									title : '值',
									align : "center"
								}, {
									field : 'name',
									title : '内容',
									align : "center"
								}, {
									field : 'note',
									title : '备注',
									align : "center"
								},
								
								// {field: 'state', title:
								// '状态',align:"center",templet :function (row){
								// return state(row.state);}},
								{
									title : '操作',
									width : 170,
									templet : '#newsListBar',
									fixed : "right",
									align : "center"
								} ] ]
							});

					$(".refresh").on("click", function() {
						location.reload();
					});

					// 添加文章
					function addDict(edit) {
						var index = layui.layer.open({
							title : "编辑人员",
							type : 2,
							content : "addDict.jsp",
							area : [ '350px', '340px' ],
							success : function(layero, index) {
								var body = layui.layer.getChildFrame('body',
										index);
								if (edit) {

									body.find(".id").val(edit.id);
									body.find(".type").val(edit.type);
									body.find(".value").val(edit.value);
									body.find(".name").val(edit.name);
									body.find(".note").val(edit.note);
									form.render();
								}

							}
						})
					}
					$(".addDict").click(function() {
						addDict();
					})

					$("#DelDictBtn").click(function() {
						var checkStatus = table.checkStatus('dictionaryList');
						var data = checkStatus.data;
						if (data.length > 0) {
							if (data.length > 1) {
								layer.msg("请只选中一条的数据");
							} else if (data.length = 1) {

								layer.confirm('确定要删除选择的字典数据么 ', {
									btn : [ '确定', '取消' ]
								// 按钮
								}, function() {
									Deldict(JSON.stringify(data[0].id));
								}, function() {
									layer.msg('你选择了取消');
								});
							}

						} else {
							layer.msg("请选择需要删除的物资");
						}
					});
					function Deldict(id) {
						$
								.ajax({
									url : "/BackgroundMaterialManagementSystem/DictionaryController?methods=delDict&&id="
											+ id,
									type : "Post",
									dataType : "json",
									success : function(result) {
										var data = result.data; // 返回的数据
										var msg = result.msg;
										layer.msg(msg);
										table.reload('dictionaryList');
									}
								});
					}

					// 列表操作
					table.on('tool(dictionaryList)', function(obj) {
						var layEvent = obj.event, data = obj.data;

						if (layEvent === 'edit') { // 编辑
							addDict(data);
						} else if (layEvent === 'del') { // 删除
							layer.confirm('确定删除此文章？', {
								icon : 3,
								title : '提示信息'
							}, function(index) {
								// $.get("删除文章接口",{
								// newsId : data.newsId //将需要删除的newsId作为参数传入
								// },function(data){
								tableIns.reload();
								layer.close(index);
								// })
							});
						} else if (layEvent === 'look') { // 预览
							layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
						}
					});

					function createTime(v) {
						var date = new Date(v);
						var y = date.getFullYear();
						var m = date.getMonth() + 1;
						m = m < 10 ? '0' + m : m;
						var d = date.getDate();
						d = d < 10 ? ("0" + d) : d;
						var h = date.getHours();
						h = h < 10 ? ("0" + h) : h;
						var M = date.getMinutes();
						M = M < 10 ? ("0" + M) : M;
						var str = y + "-" + m + "-" + d + " " + h + ":" + M;
						return str;
					}

					function state(s) {
						var st = null;
						if (s == "1") {
							st = "正常";
						} else if (s == "0") {
							st = "禁用";
						} else {
							st = "异常";
						}
						return st;
					}

				})