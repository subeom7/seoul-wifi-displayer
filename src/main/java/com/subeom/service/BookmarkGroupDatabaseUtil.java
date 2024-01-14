package com.subeom.service;

import java.sql.*;
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
                    group.setEditTimestamp(rs.getString("editTimestamp"));
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }

    public static boolean isTableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        try (ResultSet tables = dbm.getTables(null, null, tableName, null)) {
            return tables.next();
        }
    }
}
