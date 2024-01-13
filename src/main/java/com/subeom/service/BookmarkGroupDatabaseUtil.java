package com.subeom.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDatabaseUtil {

    public static List<BookmarkGroup> getBookmarkGroups() {
        List<BookmarkGroup> groups = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/bookmark_groups.db");
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM bookmark_groups");
                while (rs.next()) {
                    BookmarkGroup group = new BookmarkGroup();
                    group.setId(rs.getInt("id"));
                    group.setName(rs.getString("bookmarkName"));
                    group.setOrder(rs.getInt("bookmarkOrder"));
                    group.setAddTimestamp(rs.getString("addTimestamp"));
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider better error handling
        }
        return groups;
    }
}
