package com.subeom.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDatabaseUtil {

    public static List<Bookmark> getBookmarks() {
        List<Bookmark> groups = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark.db");
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM bookmark");
                while (rs.next()) {
                    Bookmark bookmark = new Bookmark();
                    bookmark.setId(rs.getInt("id"));
                    bookmark.setBookmarkName(rs.getString("bookmarkName"));
                    bookmark.setWifiName(rs.getString("wifiName"));
                    bookmark.setAddTimestamp(rs.getString("addTimestamp"));
                    groups.add(bookmark);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }
}

