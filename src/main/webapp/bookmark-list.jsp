<%@ page import="com.subeom.service.Bookmark" %>
<%@ page import="com.subeom.service.BookmarkDatabaseUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %><%--즐겨 찾기 보기--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>북마크 목록</h1>

<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">즐겨 찾기 보기</a> |
    <a href="bookmark-group.jsp">즐겨찾기 그룹 관리</a>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th> <%--삭제--%>
    </tr>
    </thead>
    <tbody>
    <%
        List<Bookmark> bookmarks = BookmarkDatabaseUtil.getBookmarks();
        for (Bookmark bookmark : bookmarks) {
    %>
    <tr>
        <td><%= bookmark.getId() %></td>
        <td><%= bookmark.getBookmarkName() %></td>
        <td><%= bookmark.getWifiName() %></td>
        <td><%= bookmark.getAddTimestamp() %></td>
        <td>
                <a href="bookmark-delete.jsp?id=<%= bookmark.getId() %>&bookmarkName=<%= URLEncoder.encode(bookmark.getBookmarkName(), "UTF-8") %>
               &bookmarkWifiName=<%= URLEncoder.encode(bookmark.getWifiName(), "UTF-8") %>
                &bookmarkAddTimeStamp=<%= URLEncoder.encode(bookmark.getAddTimestamp(), "UTF-8") %>" class="button">삭제</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
