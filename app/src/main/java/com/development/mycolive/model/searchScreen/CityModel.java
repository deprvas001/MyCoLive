package com.development.mycolive.model.searchScreen;

import androidx.annotation.NonNull;

public class CityModel {
    private String id;
    private String city_name;
    private String post_code;
    private String fb_link;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getFb_link() {
        return fb_link;
    }

    public void setFb_link(String fb_link) {
        this.fb_link = fb_link;
    }


    @NonNull
    @Override
    public String toString() {
        return city_name;
    }
}
