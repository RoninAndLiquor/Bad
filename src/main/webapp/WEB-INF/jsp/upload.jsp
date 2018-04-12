<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../base/base.jsp" flush="true"></jsp:include>


<meta name="viewport" content="width=device-width,minimum-scale=1.0, maximum-scale=2.0" />
<title>上传文件</title>
<style>
	#img1{
		border-radius:5px;
	}
	#myForm p{
		text-align:center;
		margin-top:10px;
	}
	#myForm{
		width:80%;
		margin:0px auto;
	}
	#img1{
		display:block;
		margin:0px auto;
		width:80%;
	}
	#fileOne{
	}
	#allmap{
		border:1px solid red;
	}
</style>
</head>
<body>
	<form id="myForm" action="/high/fileUpload/getFilePath.json" enctype="multipart/form-data" method="post">
		<p><input id ="fileOne" class="btn btn-info" type="file" onchange="showImg(this)" name="file"></p>
			<div id="imgDiv">
				<img id="img1">
			</div>
		<p><input type="submit" class="btn btn-success col-sm-2" value="submit"></p>
	</form>
	<!-- <div class="main-div">  
    <form method="post" action="" name="theForm" enctype="multipart/form-data" onsubmit="return validate()">  
        <table cellspacing="1" cellpadding="3" width="100%">  
            <tr>  
                <td class="label">经度</td>  
                <td><input type="text" name="lng" id="lng" value=""/>  
                </td>  
            </tr>  
            <tr>  
                <td class="label">纬度</td>  
                <td><input type="text" name="lat" id="lat" value=""/>  
                </td>  
            </tr>  
            <tr>  
                <td class="label">地址</td>  
                <td>  
                    <input type='text' value='' name='sever_add' id='sever_add' readonly>  
                    <input type='button' id="open" value='show'>  
                </td>  
            </tr>  
        </table>  
    </form>  
    <div id='allmap' style='width: 50%; height: 50%; position: absolute; display: none'></div>  
</div>   -->
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=jXIqbKvTo6xMltoThsZqFAT1fAlmwB10"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>  
<script type="text/javascript">
 	window.onload = init ;
	function init(){
	} 
	
	function showImg(file){
		var d;
		 $("#myForm").ajaxSubmit({  
		        type:'post',  
		        url:"/high/fileUpload/getFilePath.json", 
		        dataType : "json",
		        async:false,
		        success:function(data){
		        	d = data;
		        },
		        error:function(data){
		        	d = data;
		        }
		  });
		var path =  d.responseText;
		if(path!=null&&path!=undefined&&path!=""){
			if(path.indexOf("png")!=-1||path.indexOf("gif")!=-1||path.indexOf("jpg")!=-1){
				var split = path.split("img");
				$("#img1").attr("src","/high/img"+split[1]);
			}
		}else{
			alert("ERROR  --->  路径为空！！！");
		}
		return false;
	}
	
	
	
	 /* function validate() {  
	        var sever_add = document.getElementsByName('sever_add')[0].value;  
	        if (isNull(sever_add)) {  
	            alert('请选择地址');  
	            return false;  
	        }  
	        return true;  
	    }  
	  
	    //判断是否是空  
	    function isNull(a) {  
	        return (a == '' || typeof(a) == 'undefined' || a == null) ? true : false;  
	    }  
	  
	    $("#open").on("click",function() {
	      if (document.getElementById('allmap').style.display == 'none') { 
	            document.getElementById('allmap').style.display = 'block';  
	        } 
	    });  
	   var map = new BMap.Map("allmap");  
	    var geoc = new BMap.Geocoder();   //地址解析对象  
	    var markersArray = [];  
	    var geolocation = new BMap.Geolocation();  
	  
	    var point = new BMap.Point(116.331398, 39.897445);  
	    map.centerAndZoom(point, 12); // 中心点  
	    geolocation.getCurrentPosition(function (r) {  
	        if (this.getStatus() == BMAP_STATUS_SUCCESS) {  
	            var mk = new BMap.Marker(r.point);  
	            map.addOverlay(mk);  
	            map.panTo(r.point);  
	            map.enableScrollWheelZoom(true);  
	        }  
	        else {  
	            alert('failed' + this.getStatus());  
	        }  
	    }, {enableHighAccuracy: true})  
	    map.addEventListener("click", showInfo);  
	  
	  
	    //清除标识  
	    function clearOverlays() {  
	        if (markersArray) {  
	            for (i in markersArray) {  
	                map.removeOverlay(markersArray[i])  
	            }  
	        }  
	    }  
	    //地图上标注  
	    function addMarker(point) {  
	        var marker = new BMap.Marker(point);  
	        markersArray.push(marker);  
	        clearOverlays();  
	        map.addOverlay(marker);  
	    }  
	    //点击地图时间处理  
	    function showInfo(e) {  
	        document.getElementById('lng').value = e.point.lng;  
	        document.getElementById('lat').value =  e.point.lat;  
	        geoc.getLocation(e.point, function (rs) {  
	            var addComp = rs.addressComponents;  
	            var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;  
	            if (confirm("确定要地址是" + address + "?")) {  
	                //document.getElementById('allmap').style.display = 'none';  
	                document.getElementById('sever_add').value = address;  
	            }  
	        });  
	        addMarker(e.point);  
	    }  */
</script>
</html>