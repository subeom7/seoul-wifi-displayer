<%@ page import="java.sql.*, java.util.ArrayList, com.subeom.service.HistoryRecord, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>



<body>
<h1>위치 히스토리 목록</h1>
<div class="menu">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">즐겨 찾기 보기</a> |
    <a href="bookmark-group.jsp">즐겨 찾기 그룹 관리</a>
</div>

<%
    try {
        Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    List<HistoryRecord> historyRecords = new ArrayList<>();
    String selectSql = "SELECT * FROM history";

    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/history.db");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(selectSql)) {

        while (rs.next()) {
            HistoryRecord record = new HistoryRecord();
            record.setId(rs.getInt("id"));
            record.setLatitude(rs.getDouble("latitude"));
            record.setLongitude(rs.getDouble("longitude"));
            record.setSearchTimestamp(rs.getString("searchTimestamp"));
            historyRecords.add(record);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <%
        for (HistoryRecord record : historyRecords) {
    %>
    <tr class="history-data">
        <td><%= record.getId() %></td>
        <td><%= record.getLatitude() %></td>
        <td><%= record.getLongitude() %></td>
        <td><%= record.getSearchTimestamp() %></td>
        <td>
            <form action="DeleteServlet" method="post">
                <input type="hidden" name="id" value="<%= record.getId() %>">
                <input type="submit" value="삭제">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
