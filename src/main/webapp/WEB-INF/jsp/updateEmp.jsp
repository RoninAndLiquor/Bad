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
			width:60%;
			height:15%;
			border:1px solid gray;
			border-radius:10px;
			margin-bottom:20px;
		}
		#pp{
			text-align:center;
			margin-top:3%;
			font-size:30px;
		}
		form p{
			text-align:center;
		}
		#divHid{
			position:absolute;
			top:30%;
			right:8%;
			width:26px;
			font-size:20px;
		}
		form{
			width:70%;
			margin:0px auto;
		}
	</style>
</head>
<body>
	<div id="firstDiv">
		<p id="pp" align="center">UPDATE</p>
	</div>
		<form id="myForm" action="updateEmp.json" method="post">
			<p>工号：<input type="text" onblur="checkNum(value)" id="empJobnum" name="empJobnum"/></p>
			<p>姓名：<input type="text" id="empName" name="empName"/></p>
			<p>部门：<input type="text" id="deptId" name="deptId"/></p>
			<p>电话：<input type="text" id="empPhone" name="empPhone"/></p>
			<p>职位：<input type="text" id="postId" name="postId"/></p>
			<p>备注：<input type = "text" id="empRemark" name = "empRemark"/></p>
			<p>职称：<input type="text" id="titleId" name="titleId"></p>
			<p>身份：<input type ="text" id="empIden" name="empIden"></p>
			<p>状态：<input type="text" id="empState" name="empState"></p>
			<p><input type="submit" id="submit" disabled="disabled" class="btn btn-gray" value="提交"></p>
		</form>	
		<div id="divHid">
			
		</div>	
</body>


<script type="text/javascript">
	window.onload= init;
	var beforEmpnum = "";
	function init(){
		initInput();
	}
	var M = {};
	$("#firstDiv").on("mouseover",function(){
		$("#pp").css({"color":"red","cursor":"pointer"})
	});
	$("#firstDiv").on("mouseout",function(){
		$("#pp").css("color","black")
	});
	$("#firstDiv").on("click",function(){
		alert(22);
	});
	function initInput(){
		$("input").each(function(){
			if($(this).attr("type")=="text"){
				if($(this).attr("name")=="empJobnum"){
					$(this).attr("placeHolder","必填");
				}else{
					$(this).attr("placeHolder","选填");
				}
			}	
		});
	}
	function cleanInput(){
		$("input").each(function(){
			if($(this).attr("type")=="text"){	
				$(this).val("");
			}
		});	
	}
	function checkNum(val){
		if(val==null||val==""){
			cleanInput();
			/* $("#divHid").toggle(1500);
			$("#divHid").html("工号不能为空");
			$("#divHid").toggle(1500); */
			//$("#divHid").html("工号不能为空").hide(3000);
			M.dialog12 = jqueryAlert({
				'icon'    : '../img/error.png',
				'content' : '工号不能为空',
				'closeTime' : 700,
			});
			
		}else{
			if(val!=beforEmpnum){
				$.ajax({
					url:"queryById.json",
					type:"POST",
					dataType:"json",
					async:false,
					data:{empJobnum:val},
					success:function(data){
						$("#submit").removeAttr("disabled");
						$("#submit").attr("class","btn btn-success");
						$("#empName").val(data.empName);
						$("#deptId").val(data.deptId);
						$("#empPhone").val(data.empPhone);
						$("#postId").val(data.postId);
						$("#empIden").val(data.empIden);
						$("#titleId").val(data.titleId);
						$("#empState").val(data.empState);
						$("#empRemark").val(data.empRemark);
					},
					error:function(data){
						$("#submit").attr("disabled","disabled");
						M.dialog12 = jqueryAlert({
							'icon'    : '../img/warning.png',
							'content' : '没有此工号的相关信息',
							'closeTime' : 700,
						});
						cleanInput();
					}
				});
			}
		}
		beforEmpnum =val;
	}
</script>
	
</html>
