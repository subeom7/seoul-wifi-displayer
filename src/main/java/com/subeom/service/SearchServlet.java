package com.subeom.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/History.db");
                 Statement stmt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS history (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "latitude REAL," +
                        "longitude REAL," +
                        "searchTimestamp TEXT);";
                stmt.execute(createTableSQL);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PublicWifiService publicWifiService = new PublicWifiService();

        String insertSql = "INSERT INTO history(latitude, longitude, searchTimestamp) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/History.db");
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lng = Double.parseDouble(request.getParameter("lng"));


            List<WifiSpot> wifiSpots = publicWifiService.loadWifiSpots(lat, lng);

            request.setAttribute("wifiSpots", wifiSpots);
            request.getRequestDispatcher("wifiInfoDisplay.jsp").forward(request, response);

            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lng);
            pstmt.setString(3, LocalDateTime.now().toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "와이파이 정보를 가져올때 오류가 발생했습니다.");
        }

    }
}
