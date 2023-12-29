package com.subeom.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class PublicWifiService {

    public Document getPublicWifiData(String apiKey) {
        String urlString = String.format("http://openapi.seoul.go.kr:8088/%s/xml/TbPublicWifiInfo/1/5/", apiKey);
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 성공적으로 응답 받음
                Document doc = parseXML(conn);
                return doc;
            } else {
                System.err.println("Failed to fetch data: HTTP error code : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document parseXML(HttpURLConnection conn) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 메인 함수나 다른 서비스에서 이 메소드를 호출하여 사용
    public static void main(String[] args) {
        Properties prop = new Properties();
        String apiKey = null;
        try (InputStream input = Files.newInputStream(Paths.get("config.properties"))) {
            prop.load(input);
            apiKey = prop.getProperty("public_wifi_api_key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (apiKey != null && !apiKey.trim().isEmpty()) {
            PublicWifiService service = new PublicWifiService();
            Document wifiData = service.getPublicWifiData(apiKey);
        } else {
            System.out.println("API Key is missing or not loaded properly.");
        }

        // TODO: wifiData를 처리하는 로직 구현
    }
}
