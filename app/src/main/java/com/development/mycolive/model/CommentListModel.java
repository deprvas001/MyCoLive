package com.development.mycolive.model;

public class CommentListModel {
    String name;
    String date;
    String text;

    public CommentListModel(String name, String date, String text) {
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
