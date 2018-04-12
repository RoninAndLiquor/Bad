<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../../base/base.jsp"></jsp:include>
	<link href="/high/css/style.css" rel="stylesheet" type="text/css"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />
	<style>
	
		#validateDiv{
			display:none;
		}
	</style>
	</head>
	<body class="style-2">

		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<ul class="menu">
						<li class="active">Sign Up</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<!-- Start Sign In Form -->
					<form action="#" class="fh5co-form animate-box" data-animate-effect="fadeIn">
						<h2>Sign Up</h2>
						<!-- <div class="form-group">
							<div class="alert alert-success" role="alert">Your info has been saved.</div>
						</div> -->
						<div class="form-group">
							<label for="name" class="sr-only">Name</label>
							<input type="text" class="form-control" id="name" placeholder="Name" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="email" class="sr-only">Email</label>
							<input type="text" class="form-control" id="email" onblur="validateCode()" placeholder="Email" autocomplete="off">
						</div>
						<div id="validateDiv" class="form-group">
							<button id="repeatSendBtn" onclick="sendEmailCode()" class="btn btn-gray" disabled="disabled">60s 重新发送</button>
							<input type="text" class="form-control" id="emailCode"  placeholder="验证码" autocomplete="off"/>
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" id="password" placeholder="Password" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="re-password" class="sr-only">Re-type Password</label>
							<input type="password" class="form-control" id="re-password" placeholder="Re-type Password" autocomplete="off">
						</div>
						<!-- <div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> 记住我</label>
						</div> -->
						<div class="form-group">
							<p>已经注册? <a href="signIn.htm">登陆</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="注册" class="btn btn-primary">
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
		window.onload = init;
		function init(){
			/* var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+.([a-zA-Z0-9_-])+$/;
			var str = "103s@ss.com";
			if(reg.test(str)){
				alert(true);
			}else{
				alert(false);
			} */
			alert("${login.loginJobnum}");
		}
		var email = "";
		var M = {};
		String.prototype.endWith=function(endStr){
		  var d=this.length-endStr.length;
		  return (d>=0&&this.lastIndexOf(endStr)==d)
		}
		var resultCode = "";
		function validateCode(){
			var val = $("#email").val();
			if(email!=val){
				if(val!=null&&val!=""){
					var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+.[a-zA-Z0-9_-]+$/;
					if(reg.test(val)){
						sendEmailCode();
					}else{
						M.dialog12 = jqueryAlert({
							'icon'    : '../img/error.png',
							'content' : '邮箱格式有误',
							'closeTime' : 700,
						});
					}
				}
			}
		}
		function sendEmailCode(){
			var val = $("#email").val();
			$("#repeatSendBtn").attr("class","btn btn-gray");
			$("#repeatSendBtn").attr("disabled","disabled");
			$("#validateDiv").css("display","block");
			timeInterval();
			$.ajax({
				url:"mailValidate.json",
				type:"post",
				dataType:"json",
				data:{userMail:val},
				success:function(data){
					if(data == "ERROR"){
						alert('Unknow Error!');
						return;
					}
					email = val;
					resultCode = data;
				}
			});
		}
		function timeInterval(){
			var timeSec = 120;
			var timeStr = "";
			var codeTime = setInterval(function(){
				if(timeSec == 0){
					$("#repeatSendBtn").html("重新发送");
					$("#repeatSendBtn").removeAttr("disabled");
					$("#repeatSendBtn").attr("class","btn btn-info");
					clearInterval(codeTime);
					return ;
				}
				timeStr = ""+timeSec+" 重新发送";
				$("#repeatSendBtn").html(timeStr);
				timeSec--;
			}, 1000);
		}
		$("#emailCode").on("blur",function(){
			var val = $("#emailCode").val();
			if(val != resultCode){
				M.dialog12 = jqueryAlert({
					'icon'    : '../img/error.png',
					'content' : '验证码不正确',
					'closeTime' : 700,
				});
			}else{
				M.dialog12 = jqueryAlert({
					'icon'    : '../img/right.png',
					'content' : '验证成功',
					'closeTime' : 700,
				});
				$("#validateDiv").css("display","none");
			}
		});
	</script>
</html>