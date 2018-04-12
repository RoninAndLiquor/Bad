<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../base/base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="map" style="width: 600px; height: 600px;"></div>
</body>


<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=jXIqbKvTo6xMltoThsZqFAT1fAlmwB10"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script>
	var map;
	var gpsPoint;
	var baiduPoint;
	var gpsAddress;
	var baiduAddress;

	function getLocation() {
		//根据IP获取城市  
		var myCity = new BMap.LocalCity();
		myCity.get(getCityByIP);

		//获取GPS坐标  
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showMap, handleError, {
				enableHighAccuracy : true,
				maximumAge : 1000
			});
		} else {
			alert("您的浏览器不支持使用HTML 5来获取地理位置服务");
		}
	}

	function showMap(value) {
		var longitude = value.coords.longitude;
		var latitude = value.coords.latitude;
		map = new BMap.Map("map");
		//alert("坐标经度为：" + latitude + "， 纬度为：" + longitude );  
		gpsPoint = new BMap.Point(longitude, latitude); // 创建点坐标  
		map.centerAndZoom(gpsPoint, 15);

		//根据坐标逆解析地址  
		var geoc = new BMap.Geocoder();
		geoc.getLocation(gpsPoint, getCityByCoordinate);

		BMap.Convertor.translate(gpsPoint, 0, translateCallback);
	}

	translateCallback = function(point) {
		baiduPoint = point;
		var geoc = new BMap.Geocoder();
		geoc.getLocation(baiduPoint, getCityByBaiduCoordinate);
	}

	function getCityByCoordinate(rs) {
		gpsAddress = rs.addressComponents;
		var address = "GPS标注：" + gpsAddress.province + "," + gpsAddress.city
				+ "," + gpsAddress.district + "," + gpsAddress.street + ","
				+ gpsAddress.streetNumber;
		var marker = new BMap.Marker(gpsPoint); // 创建标注  
		map.addOverlay(marker); // 将标注添加到地图中  
		var labelgps = new BMap.Label(address, {
			offset : new BMap.Size(20, -10)
		});
		marker.setLabel(labelgps); //添加GPS标注      
	}

	function getCityByBaiduCoordinate(rs) {
		baiduAddress = rs.addressComponents;
		var address = "百度标注：" + baiduAddress.province + "," + baiduAddress.city
				+ "," + baiduAddress.district + "," + baiduAddress.street + ","
				+ baiduAddress.streetNumber;
		var marker = new BMap.Marker(baiduPoint); // 创建标注  
		map.addOverlay(marker); // 将标注添加到地图中  
		var labelbaidu = new BMap.Label(address, {
			offset : new BMap.Size(20, -10)
		});
		marker.setLabel(labelbaidu); //添加百度标注    
	}

	//根据IP获取城市  
	function getCityByIP(rs) {
		var cityName = rs.name;
		alert("根据IP定位您所在的城市为:" + cityName);
	}

	function handleError(value) {
		alert(value.message);
		switch (value.code) {
		case 1:
			alert("位置服务被拒绝");
			break;
		case 2:
			alert("暂时获取不到位置信息");
			break;
		case 3:
			alert("获取信息超时");
			break;
		case 4:
			alert("未知错误");
			break;
		}
	}

	function init() {
		getLocation();
	}

	window.onload = init;
</script>
</html>