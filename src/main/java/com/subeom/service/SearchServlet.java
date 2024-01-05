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
        // Initialize your service class
        PublicWifiService publicWifiService = new PublicWifiService();

        try {
            // Retrieve user location from the request
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lng = Double.parseDouble(request.getParameter("lng"));

            // Use the service to get nearby wifi spots
            List<WifiSpot> wifiSpots = publicWifiService.loadWifiSpots(lat, lng);

            // Set the wifi spots as a request attribute to be accessed by the JSP
            request.setAttribute("wifiSpots", wifiSpots);

            // Forward the request to the JSP page
            request.getRequestDispatcher("wifiInfoDisplay.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving wifi information.");
        }
    }
}
