package com.subeom.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookmarkGroupDeleteServlet")
public class BookmarkGroupDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String deleteSql = "DELETE FROM bookmark_groups WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db");
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            response.sendRedirect("bookmark-group.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "에러가 발생했습니다.");
        }
    }
}
