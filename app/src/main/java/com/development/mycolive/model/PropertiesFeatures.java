package com.development.mycolive.model;

public class PropertiesFeatures {
    String rating;
    String name;
    String duration;
    String price;
    String address;

    public PropertiesFeatures(String rating, String name, String duration, String price, String address) {
        this.rating = rating;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
