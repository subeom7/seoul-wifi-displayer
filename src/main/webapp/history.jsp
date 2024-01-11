<%@ page import="java.sql.*, java.util.ArrayList, com.subeom.service.HistoryRecord, java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>History Records</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%
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
    <tr>
        <th>ID</th>
        <th>x값</th>
        <th>y값</th>
        <th>검색 시간</th>
        <th>비고</th>
    </tr>
    <%
        for (HistoryRecord record : historyRecords) {
    %>
    <tr>
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
