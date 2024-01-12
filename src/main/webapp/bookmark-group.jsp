<%--즐겨찾기 그룹 관리--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.subeom.service.BookmarkGroup" %>
<%@ page import="com.subeom.service.AddBookmarkGroupServlet" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>북마크 그룹</h1>

<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">즐겨 찾기 보기</a> |
    <a href="bookmark-group.jsp">즐겨찾기 그룹 관리</a>
</div>
<button type="button" id="addBookmarkGroupBtn" class="create-bookmark-group">북마크 그룹 이름 추가</button>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th> <!-- 수정안했을시 안보임 -->
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        AddBookmarkGroupServlet s = new AddBookmarkGroupServlet();
        s.init();
        List<BookmarkGroup> groups = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db");
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM bookmark_groups");
                while (rs.next()) {
                    BookmarkGroup group = new BookmarkGroup();
                    group.setId(rs.getInt("id"));
                    group.setName(rs.getString("bookmarkName"));
                    group.setOrder(rs.getInt("bookmarkOrder"));
                    group.setAddTimestamp(rs.getString("addTimestamp"));
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        for (BookmarkGroup group : groups) {
    %>
    <tr>
        <td><%= group.getId() %></td>
        <td><%= group.getName() %></td>
        <td><%= group.getOrder() %></td>
        <td><%= group.getAddTimestamp() %></td>
        <td> <!-- 수정일자 --></td>
        <td> <!-- 비고 --></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<script>
    // JavaScript를 사용하여 클릭 이벤트 처리
    document.getElementById('addBookmarkGroupBtn').addEventListener('click', function() {
        window.location.href = 'bookmark-group-add.jsp'; // 사용자를 bookmark-group-add.jsp로 리디렉션
    });
</script>
</body>
</html>
