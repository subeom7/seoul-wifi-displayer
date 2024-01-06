package com.subeom.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PublicWifiService publicWifiService = new PublicWifiService();

        try {
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lng = Double.parseDouble(request.getParameter("lng"));


            List<WifiSpot> wifiSpots = publicWifiService.loadWifiSpots(lat, lng);

            request.setAttribute("wifiSpots", wifiSpots);


            request.getRequestDispatcher("wifiInfoDisplay.jsp").forward(request, response);
        } catch (Exception e) {

            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving wifi information.");
        }
    }
}
