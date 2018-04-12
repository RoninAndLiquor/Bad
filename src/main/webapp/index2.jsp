<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd

">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理.....</title>
<link rel="stylesheet" href="/Redhotel/after/css/bootstrap.min.css" />
<script src="/Redhotel/after/js/jquery-3.2.1.min.js"></script>
<script src="/Redhotel/after/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/Redhotel/after/css/bootstrap-table.css" />
<script src="/Redhotel/after/js/bootstrap-table.js"></script>
<script src="/Redhotel/after/js/bootstrap-table-zh-CN.js"></script>
</head>
<body>
	<table id="table"></table>

	<div id="toolbar">
		<div class="btn-group">
		
			<button  type="button" class="btn btn-info" id="button">开房</button>
			
		</div>
	</div> 
</body>
<script type="text/javascript">
	$(function() {
		InitMainTable();
		
	})
	function InitMainTable() {
		//记录页面bootstrap-table全局变量$table，方便应用
		var queryUrl = '/Redhotel/orderaction/findAllorders.action'
		
		$('#table').bootstrapTable({
			url : queryUrl, //请求后台的URL（*）
			method : 'POST', //请求方式（*）
			toolbar: '#toolbar',              //工具按钮用哪个容器
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : true, //是否启用排序
			pageNumber : 1, //初始化加载第一页，默认第一页,并记录
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			search : true, //是否显示表格搜索
			strictSearch : false,
			showColumns : true, //是否显示所有的列（选择显示的列）
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			//height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ordernum", //每一行的唯一标识，一般为主键列
			//showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
			//cardView: false,                    //是否显示详细视图
			//detailView: false,                  //是否显示父子表
			//得到查询的参数
			/*  queryParams : function (params) {
			     //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			     var temp = {   
			         rows: params.limit,                         //页面大小
			         page: (params.offset / params.limit) + 1,   //页码
			         sort: params.sort,      //排序列名  
			         sortOrder: params.order //排位命令（desc，asc） 
			     };
			     return temp;
			 }, */
			 
			columns : [ [ {
				checkbox : true,
				visible : true
			//是否显示复选框  
			}, {
				field : 'ordernum',
				title : '订单编号',
				sortable : true
			}, {
				field : 'orderusename',
				title : '客户姓名',
				sortable : true
			}, {
				field : 'orderphone',
				title : '手机号',
				sortable : true
			},{
				field : 'roomtype',
				title : '房间类型',
				sortable : true
			},{
				field : 'roomprice',
				title : '房间价格',
				sortable : true
			},{
				field : 'startime',
				title : '入住时间',
				sortable : true
			},{
				field : 'endtime',
				title : '离店时间',
				sortable : true
			},{
				field : 'orderroomcount',
				title : '预定房间数量',
				sortable : true
			},{
				field : 'orderday',
				title : '入住天数',
				sortable : true
			},{
				field : 'ordertime',
				title : '下订单时间',
				sortable : true
			},{
				field : 'orderstate',
				title : '订单状态',
				sortable : true
			},{
				field : 'ordersprice',
				title : '订单总额',
				sortable : true
			} ] ],
			onDblClickRow : function(row) {
				alert("我双击了" + row.menuName);
			}
		});
	};
	$(function() {
		InitMainTable();
		$("#button").on('click',function(){
			var rows = $('#table').bootstrapTable("getSelections");		
			var id;
			if(rows.length>0){	
				for(var i = 0;i<rows.length;i++){
					id=rows[i].ordernum;
				}
				//或者 这里 
				id = id + "";
			window.location.href="/Redhotel/orderaction/findorderById.action?id="+id+"";
			}else{
				alert("没有选中行");
			}
		});
	})

</script>
</html>