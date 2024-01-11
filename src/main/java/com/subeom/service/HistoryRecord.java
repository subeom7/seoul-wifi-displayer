package com.subeom.service;

public class HistoryRecord {
    private int id;
    private double latitude;
    private double longitude;
    private String searchTimestamp;

    public HistoryRecord() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSearchTimestamp() {
        return searchTimestamp;
    }

    public void setSearchTimestamp(String searchTimestamp) {
        this.searchTimestamp = searchTimestamp;
    }
}
