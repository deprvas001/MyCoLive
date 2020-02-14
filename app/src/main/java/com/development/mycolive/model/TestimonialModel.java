package com.development.mycolive.model;

public class TestimonialModel {
    private String name;
    private String description;
    private String date;
    private String comment;

    public TestimonialModel(String name, String description, String date, String comment) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
