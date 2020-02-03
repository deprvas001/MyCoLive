package com.development.mycolive.views.model;

public class AllCommunityModel {
    String name;
    String date;
    String heading;

    public AllCommunityModel(String name, String date, String heading) {
        this.name = name;
        this.date = date;
        this.heading = heading;
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

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
