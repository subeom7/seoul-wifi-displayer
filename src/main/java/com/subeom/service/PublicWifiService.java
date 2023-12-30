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
    private String apiKey; // API 키를 저장할 변수

    public PublicWifiService() {
        loadApiKey();
    }

    private void loadApiKey() {
        try (InputStream input = Files.newInputStream(Paths.get("../config.properties"))) {
            Properties prop = new Properties();

            prop.load(input);

            apiKey = prop.getProperty("public_wifi_api_key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadWifiSpots() {
        //TODO: API URL을 구성하고 요청을 보내는 로직 구현
        //TODO: 응답을 받아 파싱하는 로직 구현
        //TODO: 데이터를 추출하고 저장하는 로직 구현
    }

}

