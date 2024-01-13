    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel="stylesheet" type="text/css" href="detailStyles.css">
    </head>
    <body>
    <h1>북마크 그룹 추가</h1>
    <div class="menu">
        <a href="index.jsp">홈</a> |
        <a href="history.jsp">위치 히스토리 목록</a> |
        <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
        <a href="bookmark-list.jsp">즐겨 찾기 보기</a> |
        <a href="bookmark-group.jsp">즐겨찾기 그룹 관리</a>

        <form action="AddBookmarkGroupServlet" method="post" accept-charset="UTF-8">
            <table>
                <thead>
                <tr>
                    <th>북마크 이름</th>
                    <td><input type="text" name="bookmarkName" id="bookmarkName" value="<%= request.getParameter("bookmarkName") %>"></td> <!-- 북마크 이름 입력 필드 -->
                </tr>
                <tr>
                    <th>순서</th>
                    <td><input type="text" name="bookmarkOrder" id="bookmarkOrder" value="<%= request.getParameter("order") %>"></td> <!-- 순서 입력 필드 -->
                </tr>
                </thead>
            </table>
            <div class="form-actions">
            <a href="bookmark-group.jsp">돌아가기</a> |
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
            <input type="submit" value="수정" class="button">
            </div>
        </form>

    </div>

    </body>
    </html>
