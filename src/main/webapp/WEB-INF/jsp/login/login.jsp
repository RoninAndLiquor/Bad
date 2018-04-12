<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../../base/base.jsp"></jsp:include>
	<link href="/high/css/style.css" rel="stylesheet" type="text/css"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>登陆</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />
	</head>
	<body class="style-2">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
						<li>Sign in</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<!-- Start Sign In Form -->
					<form action="/high/login/login" class="fh5co-form animate-box"  data-animate-effect="fadeIn" method="post">
						<h2>登陆</h2>
						<div class="form-group">
							<label for="username" class="sr-only">用户名</label>
							<input type="text" class="form-control" name="loginJobnum" id="username" placeholder="用户名" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密码</label>
							<input type="password" class="form-control" name="loginPass" id="password" placeholder="密码" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="remember"><input type="checkbox" name = "remember" id="remember">记住我</label>
						</div>
						<div class="form-group">
							<p>尚未注册? <a href="signUp.htm">注册</a> | <a href="forgot.htm">忘记密码?</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="GO GO" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->

				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p><small>&copy; All Rights Reserved. ShuaiFeng Jiang <a href="http://www.cssmoban.com/" target="_blank" title="Connect Me">Connect Me</a></small></p></div>
			</div>
		</div>

	</body>
	<script type="text/javascript">
		var data = "1";
		alert(eval(data));
	</script>
</html>