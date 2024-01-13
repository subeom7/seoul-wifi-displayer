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

@WebServlet("/AddBookmarkServlet")
public class AddBookmarkServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark.db");
                 Statement stmt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS bookmark (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "bookmarkName TEXT," +
                        "wifiName TEXT," +
                        "addTimestamp TEXT);";
                stmt.execute(createTableSQL);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bookmarkName = request.getParameter("bookmarkName");
        String wifiName = request.getParameter("wifiName");

        // Database connection and insertion logic
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark.db");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bookmark (bookmarkName, wifiName, addTimestamp) VALUES (?, ?, ?)")) {

            pstmt.setString(1, bookmarkName);
            pstmt.setString(2, wifiName);
            pstmt.setString(3, LocalDateTime.now().toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("bookmark-list.jsp");
    }
}

