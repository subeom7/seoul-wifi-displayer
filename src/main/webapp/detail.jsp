<%@ page import="com.subeom.service.WifiSpot" %>
<%@ page import="com.subeom.service.PublicWifiService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="detailStyles.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">즐겨 찾기 보기</a> |
    <a href="bookmark-group.jsp">즐겨찾기 그룹 관리</a>
</div>

<%--북마크 그룹 드랍다운 메뉴--%>
<select class="bookmark-group-dropdown" name="bookmarkGroup">
    <option value="">북마크 그룹 이름 선택</option>
    <!-- DB구축 후 server-side 코드 구현해서 북마크 그룹 가져옴 -->
</select>
<button type="button" class="favorites-button" onclick="searchNearbyWipi()">즐겨찾기 추가하기</button>
<%
    String distance = request.getParameter("distance");
    String managerNo = request.getParameter("managerNo");
    String ward = request.getParameter("ward");
    String wifiName = request.getParameter("wifiName");
    String address1 = request.getParameter("address1");
    String address2 = request.getParameter("address2");
    String installationFloor = request.getParameter("installationFloor");
    String installationType = request.getParameter("installationType");
    String serviceProvider = request.getParameter("serviceProvider");
    String netType = request.getParameter("netType");
    String installYear = request.getParameter("installYear");
    String indoorOutdoor = request.getParameter("indoorOutdoor");
    String wifiEnvironment = request.getParameter("wifiEnvironment");
    String latitude = request.getParameter("latitude");
    String longitude = request.getParameter("longitude");
    String workDate = request.getParameter("workDate");
%>

<table>
    <tr><th>거리(Km)</th><td><%= distance %></td></tr>
    <tr><th>관리번호</th><td><%= managerNo %></td></tr>
    <tr><th>자치구</th><td><%= ward %></td></tr>
    <tr><th>와이파이명</th><td><a href><%= wifiName %></a></td></tr>
    <tr><th>도로명주소</th><td><%= address1 %></td></tr>
    <tr><th>상세주소</th><td><%= address2 %></td></tr>
    <tr><th>설치위치(층)</th><td><%= installationFloor %></td></tr>
    <tr><th>설치유형</th><td><%= installationType %></td></tr>
    <tr><th>설치기관</th><td><%= serviceProvider %></td></tr>
    <tr><th>서비스 구분</th><td><%= netType %></td></tr>
    <tr><th>설치년도</th><td><%= installYear %></td></tr>
    <tr><th>실내외구분</th><td><%= indoorOutdoor %></td></tr>
    <tr><th>WIFI 접속환경</th><td><%= wifiEnvironment %></td></tr>
    <tr><th>X좌표</th><td><%= latitude %></td></tr>
    <tr><th>Y좌표</th><td><%= longitude %></td></tr>
    <tr><th>작업일자</th><td><%= workDate %></td></tr>
</table>


<table>


</table>
</body>
</html>
