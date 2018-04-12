<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../base/base.jsp"></jsp:include>
<title>Error</title>
<style>
	#fristP{
		font-size:90px;
		font-weight:bold;
	}
	#myDiv{
		margin-top:50px;
		weight:60%;
	}
	#myDiv p {
		text-align:center;
		margin-top:15px;
	}
</style>
</head>
<body>
	<p id="firstP">404</p>
	<div id="myDiv">
		<p>出错位置：${error}</p>
		<p>出错异常：${exception }</p>
		<p>出错位置：${url }</p>
	</div>
</body>
</html>