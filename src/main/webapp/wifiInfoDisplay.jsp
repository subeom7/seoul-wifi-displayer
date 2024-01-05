<%@ page import="com.subeom.service.WifiSpot" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="LocationHistoryList.jsp">위치 히스토리 목록</a> |
    <a href="OpenApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="FavoritesView.jsp">즐겨 찾기 보기</a> |
    <a href="FavoriteGroupManagement.jsp">즐겨찾기 그룹 관리</a>
</div>

<form action="SearchServlet" method="GET" class="search-form">
    <label for="lat">LAT:</label>
    <input type="text" id="lat" name="lat" class="search-field">

    <label for="lng">, LNT:</label>
    <input type="text" id="lng" name="lng" class="search-field">

    <input type="button" value="내 위치 가져오기" class="search-button" onclick="getLocation()">
    <button type="button" class="nearby-wipi-button" onclick="searchNearbyWipi()">근처 WIPI 정보 보기</button>
</form>

<div class="wifi-table">
<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스 구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<WifiSpot> wifiSpots = (List<WifiSpot>) request.getAttribute("wifiSpots");
        if(wifiSpots != null && !wifiSpots.isEmpty()) {
            for(WifiSpot spot : wifiSpots) {
    %>
    <tr>
        <td><%= spot.getDistance() %></td>
        <td><%= spot.getManagerNo() %></td>
        <td><%= spot.getWard() %></td>
        <td><%= spot.getWifiName() %></td>
        <td><%= spot.getAddress1() %></td>
        <td><%= spot.getAddress2() %></td>
        <td><%= spot.getInstallationFloor() %></td>
        <td><%= spot.getInstallationType() %></td>
        <td><%= spot.getServiceProvider() %></td>
        <td><%= spot.getNetType() %></td>
        <td><%= spot.getInstallYear() %></td>
        <td><%= spot.getIndoorOutdoor() %></td>
        <td><%= spot.getWifiEnvironment() %></td>
        <td><%= spot.getLatitude() %></td>
        <td><%= spot.getLongitude() %></td>
        <td><%= spot.getWorkDate() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="17">와이파이 정보를 불러오는 데 실패하였습니다.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>


<script>
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition, showError);
        } else {
            alert("현재 브라우저에서는 Geolocation을 지원하지 않습니다. 크롬을 이용해주세요");
        }
    }

    function showPosition(position) {
        // 위도와 경도 input 필드에 현재 위치의 위도와 경도 값을 채워 넣음.
        document.getElementById("lat").value = position.coords.latitude;
        document.getElementById("lng").value = position.coords.longitude;
    }

    function showError(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                alert("User denied the request for Geolocation.");
                break;
            case error.POSITION_UNAVAILABLE:
                alert("Location information is unavailable.");
                break;
            case error.TIMEOUT:
                alert("The request to get user location timed out.");
                break;
            case error.UNKNOWN_ERROR:
                alert("An unknown error occurred.");
                break;
        }
    }

    function searchNearbyWipi() {
        var lat = document.getElementById("lat").value;
        var lng = document.getElementById("lng").value;
        window.location.href = 'SearchServlet?lat=' + lat + '&lng=' + lng;
    }

</script>
</body>
</html>