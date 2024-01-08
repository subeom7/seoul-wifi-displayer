package com.subeom.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PublicWifiService {

    private final String apiKey = System.getenv("PUBLIC_WIFI_API_KEY");


    public PublicWifiService() {

    }

    public List<WifiSpot> loadWifiSpots(double lat, double lng) throws Exception {

        // 와이파이 스팟 객체들을 담을 리스트
        List<WifiSpot> spots = new ArrayList<>();
        // API URL 구성
        String urlStr = "http://openapi.seoul.go.kr:8088/" + apiKey + "/xml/TbPublicWifiInfo/1/20/";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // XML 응답 파싱
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(conn.getInputStream());
        doc.getDocumentElement().normalize();

        // XML 응답에서 각 row 순회
        NodeList nList = doc.getElementsByTagName("row");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Element eElement = (Element) nList.item(temp);
            WifiSpot spot = new WifiSpot(); // Initialize WifiSpot object

            //WifiSpot의 속성 설정
            spot.setManagerNo(eElement.getElementsByTagName("X_SWIFI_MGR_NO").item(0).getTextContent());
            spot.setWard(eElement.getElementsByTagName("X_SWIFI_WRDOFC").item(0).getTextContent());
            spot.setWifiName(eElement.getElementsByTagName("X_SWIFI_MAIN_NM").item(0).getTextContent());
            spot.setAddress1(eElement.getElementsByTagName("X_SWIFI_ADRES1").item(0).getTextContent());
            spot.setAddress2(eElement.getElementsByTagName("X_SWIFI_ADRES2").item(0).getTextContent());
            spot.setInstallationFloor(eElement.getElementsByTagName("X_SWIFI_INSTL_FLOOR").item(0).getTextContent());
            spot.setInstallationType(eElement.getElementsByTagName("X_SWIFI_INSTL_TY").item(0).getTextContent());
            spot.setServiceProvider(eElement.getElementsByTagName("X_SWIFI_INSTL_MBY").item(0).getTextContent());
            spot.setNetType(eElement.getElementsByTagName("X_SWIFI_SVC_SE").item(0).getTextContent());
            spot.setInstallYear(parseYear(eElement.getElementsByTagName("X_SWIFI_CNSTC_YEAR").item(0).getTextContent()));
            spot.setIndoorOutdoor(eElement.getElementsByTagName("X_SWIFI_INOUT_DOOR").item(0).getTextContent());
            spot.setWifiEnvironment(eElement.getElementsByTagName("X_SWIFI_REMARS3").item(0).getTextContent());
            spot.setLatitude(Double.parseDouble(eElement.getElementsByTagName("LAT").item(0).getTextContent()));
            spot.setLongitude(Double.parseDouble(eElement.getElementsByTagName("LNT").item(0).getTextContent()));
            spot.setWorkDate(eElement.getElementsByTagName("WORK_DTTM").item(0).getTextContent());

            // 위도와 경도를 기반으로 거리 계산 및 설정
            spot.setDistance(calculateDistance(lat, lng, spot.getLatitude(), spot.getLongitude()));

            spots.add(spot); // 리스트에 WifiSpot 추가
        }


        // 거리에 기반하여 WifiSpot 객체들을 정렬
        spots.sort(Comparator.comparingDouble(WifiSpot::getDistance));

        // WifiSpots의 리스트 반환
        return spots.subList(0, Math.min(spots.size(), 20));
    }
    public static double calculateDistance(double userLat, double userLng, double wifiLat, double wifiLng) {
        double earthRadius = 6371;
        double dLat = Math.toRadians(wifiLat - userLat);
        double dLng = Math.toRadians(wifiLng - userLng);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(wifiLat)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c; // 거리(km)
        return distance;
    }
    // 정수로 년도 파싱
    private int parseYear(String yearStr) {
        try {
            return Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
