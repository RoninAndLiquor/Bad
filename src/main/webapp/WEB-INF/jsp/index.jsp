<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>
	<title>WORLD</title>
	<jsp:include page="../base/base.jsp" flush="true"></jsp:include>
	<style>
		#firstDiv{
			margin:0px auto;
			margin-top:20px;
			width:800px;
			height:200px;
			border:1px solid gray;
			border-radius:10px;
		}
		#pp{
			text-align:center;
			margin-top:80px;
			font-size:30px;
		}
		form p{
			text-align:center;
		}
	</style>
</head>
<body>
	<div id="firstDiv">
		<p id="pp" align="center">EXERCISE</p>
	</div>
		<form action="save.htm" method="post">
			<p>姓名：<input type="text" name="empName"/></p>
			<p>部门：<input type="text" name="deptId"/></p>
			<p>电话：<input type="text" name="empPhone"/></p>
			<p>职位：<input type="text" name="postId"/></p>
			<p>备注：<input type = "text" name = "empRemark"/></p>
			<p>职称：<input type="text" name="titleId"></p>
			<p>身份：<input type ="text" name="empIden"></p>
			<p>状态：<input type="text" name="empState"></p>
			<p><input type="submit" class="btn btn-success" value="提交"></p>
		</form>		
</body>


<script type="text/javascript">
	$("#firstDiv").on("mouseover",function(){
		$("#pp").css({"color":"red","cursor":"pointer"})
	});
	$("#firstDiv").on("mouseout",function(){
		$("#pp").css("color","black")
	});
	$("#firstDiv").on("click",function(){
		alert(22);
	});
</script>
	
</html>
