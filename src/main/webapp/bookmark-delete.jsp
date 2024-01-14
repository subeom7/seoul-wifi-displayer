
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="detailStyles.css">
</head>
<body>
<h1>북마크 삭제</h1>

<%
    String id = request.getParameter("id");
    String bookmarkName = request.getParameter("bookmarkName");
    String bookmarkWifiName = request.getParameter("bookmarkWifiName");
    String bookmarkAddTimeStamp = request.getParameter("bookmarkAddTimeStamp");
%>

<table>
    <tr><th>북마크 이름</th><td><%= bookmarkName %></td></tr>
    <tr><th>와이파이 명</th><td><%= bookmarkWifiName %></td></tr>
    <tr><th>등록일자</th><td><%= bookmarkAddTimeStamp %></td></tr>

</table>

<form action="BookmarkDeleteServlet" method="post">
    <div class="form-actions">
        <a href="bookmark-list.jsp">돌아가기</a> |
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <input type="submit" value="삭제" class="button">
    </div>
</form>


</body>
</html>
