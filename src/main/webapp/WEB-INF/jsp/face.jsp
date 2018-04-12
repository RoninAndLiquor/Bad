<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../base/base.jsp" flush="true"></jsp:include>
<style>
	#title{
		font-family: 楷体;
		padding-top:10px;
		padding-buttom:10px;
		border-bottom:3px solid #EEEEEC;	
		font-size:60px;
		text-align:center;
		border-radius:340px 340px 100px 100px;
		display:block;
		background-color:#EEEEEC;
	}
	#father{
		
	}
	#inputdiv{
		text-align:center;
		width:60%;
		margin:0px auto;
	}
	#btndiv{
		text-align:center;
		margin:5px;
	}
	/* #buttomdiv{
		position:absolute;
		bottom:0px;
		left:50px;
	} */
	#buttomdiv p{
		text-align:center;
	}
	#bodydiv{
		margin-top:20px;
	}
	.fixed{ position:fixed; left:0px; bottom:0px; width:100%; z-index:9999;margin-top:50px;}
</style>
</head>
<body>
	<p id="title">test</p>
	<div id="father">
		<div id="expre">
			<p id="myP" style="text-align:center;margin:5px;">{{ msg }}</p>
		</div>
		<div id="inputdiv">
			<input class="form-control" id="name"/>
		</div>
		<div id="btndiv">
			<button id="testBtn" class="btn btn-info">{{text}}</button>
		</div>
		<div id="bodydiv">
		</div>
		<ul>
			<li>One</li>
			<li name="two">Two</li>
			<li>Three</li>
		</ul>
		<div id="box">
			<input class="box1" type="checkbox"/>
			<input class="box1" type="checkbox"/>
		</div>
	</div>
	<!-- <div id="buttomdiv">
		<p style="font-size:10px;">隐私声明|服务条款</p>
		<p style="font-size:12px;">测试大全网</p>
		<p style="font-size:12px;">服务电话:<span style="color:green">400-060-6071</span>
			&nbsp;&nbsp;服务监督电话:<span style="color:green">400-066-8780</span>
		</p>
		<p style="font-size:8px">京ICP备15053955号 ICP证151071号京公安网备11010802020161<p>
	</div> -->
</body>
















<script type="text/javascript">
	window.onload = init;
	function init(){
		jsonToObj();
	}
	$(".box1").attr("checked",true);
	console.log($.min(4,15));
	
	
	console.log($("ul :lt(3)").html());
	console.log($("#father :first").attr("id"));
	console.log($("#father [name]").html());
	var a = [1,9,15,3,6];
	//计算数组最大值 Math.max.apply(null,数组);
	console.log(Math.max.apply(null,a));
	console.log(Math.max.call(null,3,6,7));
	for (var i = 0; i < 10; i++) {
		var myName = "JSF";
	  a[i] = function () {
	    console.log(i);
	  };
	}
	a[3]();
	console.log(myName);
	+function(){
		console.log(1111)
	}();
	var person = function(){
		var name = "default";
		return {
			getName : function(){
				return name;
			},
			setName : function(newName){
				name = newName;
			}
		}
	}();
	
	console.log(person.name);
	console.log(person.getName());
	console.log(person.setName("ShangHai"));
	console.log(person.getName());
	//var ss ="ddd";
	
	var name ="aolong";
	var object = {
			name:"group",
			getNameFunc:function(){
				var list = "ddksls";
				return{
					getList :function(){
						return list;
					},
					getName : function(){
						return this.name;
					}
				};
			}
	}
	console.log(object.getNameFunc().getList());
	
	function ss(){
		return this.name;
	}
	
	$("#testBtn").click(function(){
		var M = {};
		var flag = true;
		var val = $("#name").val();
		var test = /^[\u4E00-\u9FA5]{2,4}$/;
		var test2 = /^[a-zA-Z]{3,10}$/;
		if(val==null || val ==""){
			M.dialog12 = jqueryAlert({
				'icon'    : '../img/warning.png',
				'content' : '姓名不能为空',
				'closeTime' : 700,
			});
			flag = false;
		}else{
			if(!(test.test(val)||test2.test(val))){
				M.dialog12 = jqueryAlert({
					'icon'    : '../img/error.png',
					'content' : '姓名格式有误',
					'closeTime' : 700,
				});
				flag = false;
			}
		}
		
		
		if(flag){
			if(val == "test"){
				var bodyStr = "<p style='text-align:center;font-size:20px;font-weight:bold;'>result</p>";
				bodyStr+="<p style='text-align:center;'>here is an old woman lives next to my house. She lives alone because her only son works in another city and can’t go home often. So every day when I go home after school, I can see her sitting in the main gate and chat with others happily. She is very kind. One time, I forgot to bring my key, so I had to sit in the main gate. She talked to me and gave me some snacks. Since then, we became friends. I always talked with her about my problems and she could offer me some ideas. Now my parents often invite her to have dinner with us. She is like a family to us.</p>";
				$("body").css("height","100%");
				$("#bodydiv").css({"border":"3px solid #EEEEEB","border-radius":"5px","background-color":"#EEEEEC","margin":"25px 10px 25px 10px"});
				$("#bodydiv").html(bodyStr);
			}
		}
		
	});
	
	new Vue({
		el:"#myP",
		data:{
			message:"INPUT NAME",
			msg:"PLEASE INPUT NAME"
		}
	});
	new Vue({
		el:"#testBtn",
		data:{
			text:"START TEST"
		}
	})
	
	function fun(n,o){
		console.log(o);
		var name = "THIS IS PRIVATE VALUE";
		return {
			fun : function(m){
				return fun(m,n);
			},
			getName : function(){
				return name;
			},
			setName : function(newName){
				name = newName;
			}
		}
	}
	var a = fun(1);
	a.fun(3);
	a.fun(4);
	var b = fun(1).fun(2).fun(3).fun(4);
	var c = fun(0).fun(1) ; c.fun(2); c.fun(3);
	c.setName("THIS PRIVATE ATTRIBUTE USE SETNAME NEW NAME");
	var name = c.getName();
	console.log(name);
	var d = {
		name : "dd",
		sex : "famale"
	};
	d.age = 23;
	console.log(d);
	
	
	
	function jsonToObj(){
		var obj = [{
				"1":"1111",
				"2":"2222",
				"3":"3333"
		},{
				"1":"4444",
				"2":"5555",
				"3":"6666"
		}];
		for(var i=0;i<obj.length;i++){
			alert(obj[i]["2"]);
		}
	}
</script>
</html>