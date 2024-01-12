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
        int bookmarkOrder = Integer.parseInt(request.getParameter("bookmarkOrder"));

        // Database connection and insertion logic
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bookmark_groups (bookmarkName, bookmarkOrder, addTimestamp) VALUES (?, ?, ?)")) {

            pstmt.setString(1, bookmarkName);
            pstmt.setInt(2, bookmarkOrder);
            pstmt.setString(3, LocalDateTime.now().toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        response.sendRedirect("bookmark-group.jsp"); // Redirect back to the bookmark group page
    }
}
