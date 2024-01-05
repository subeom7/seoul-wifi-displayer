package com.subeom.service;

public class WifiSpot implements Comparable<WifiSpot> {
    private double distance; // 사용자로부터의 거리
    private String managerNo; // 관리번호
    private String ward; // 자치구
    private String wifiName; // 와이파이명
    private String address1; // 도로명주소
    private String address2; // 상세주소
    private String installationFloor; // 설치위치(층)
    private String installationType; // 설치유형
    private String serviceProvider; // 서비스 제공자
    private String netType; // 망 종류
    private int installYear; // 설치년도
    private String indoorOutdoor; // 실내외 구분
    private String wifiEnvironment; // WIFI 접속환경
    private double latitude; // 위도
    private double longitude; // 경도
    private String workDate; // 작업일자


    public WifiSpot() {}

    // Getters and setters
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getInstallationFloor() {
        return installationFloor;
    }

    public void setInstallationFloor(String installationFloor) {
        this.installationFloor = installationFloor;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public int getInstallYear() {
        return installYear;
    }

    public void setInstallYear(int installYear) {
        this.installYear = installYear;
    }

    public String getIndoorOutdoor() {
        return indoorOutdoor;
    }

    public void setIndoorOutdoor(String indoorOutdoor) {
        this.indoorOutdoor = indoorOutdoor;
    }

    public String getWifiEnvironment() {
        return wifiEnvironment;
    }

    public void setWifiEnvironment(String wifiEnvironment) {
        this.wifiEnvironment = wifiEnvironment;
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

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Override
    public int compareTo(WifiSpot other) {
        return Double.compare(this.distance, other.distance);
    }
}
