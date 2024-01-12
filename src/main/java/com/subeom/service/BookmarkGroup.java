package com.subeom.service;

public class BookmarkGroup {
    private int id;
    private String name;
    private int order;
    private String addTimestamp;

    // Constructor
    public BookmarkGroup() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getAddTimestamp() {
        return addTimestamp;
    }

    public void setAddTimestamp(String addTimestamp) {
        this.addTimestamp = addTimestamp;
    }
}
