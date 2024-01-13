package com.subeom.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

@WebServlet("/AddBookmarkGroupServlet")
public class AddBookmarkGroupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db");
                 Statement stmt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS bookmark_groups (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "bookmarkName TEXT," +
                        "bookmarkOrder REAL," +
                        "addTimestamp TEXT," +
                        "editTimestamp TEXT);";
                stmt.execute(createTableSQL);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bookmarkName = request.getParameter("bookmarkName");
        int bookmarkOrder = Integer.parseInt(request.getParameter("bookmarkOrder"));
        String id = request.getParameter("id");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db")) {
            String sql;
            if (id == null || id.isEmpty()) {
                // ID가 없으면 새로운 북마크 그룹 추가
                sql = "INSERT INTO bookmark_groups (bookmarkName, bookmarkOrder, addTimestamp) VALUES (?, ?, ?)";
            } else {
                // ID가 있으면 기존 북마크 그룹 업데이트
                sql = "UPDATE bookmark_groups SET bookmarkName = ?, bookmarkOrder = ?, editTimestamp = ? WHERE id = ?";
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, bookmarkName);
                pstmt.setInt(2, bookmarkOrder);
                if (id != null && !id.isEmpty()) {
                    pstmt.setString(3, LocalDateTime.now().toString());
                    pstmt.setInt(4, Integer.parseInt(id));
                } else {
                    pstmt.setString(3, LocalDateTime.now().toString());
                }
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }

        response.sendRedirect("bookmark-group.jsp"); // 북마크 그룹 페이지로 리디렉션
    }

}
