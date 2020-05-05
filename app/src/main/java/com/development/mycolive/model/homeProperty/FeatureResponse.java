package com.development.mycolive.model.homeProperty;

import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.propertyDetailModel.FacilityData;

import java.util.ArrayList;
import java.util.List;

public class FeatureResponse {
    private String message;
    private int status;
    private String city_fb_link;
    private ArrayList<HomeFeatureProperty> data;

    public String getCity_fb_link() {
        return city_fb_link;
    }

    public void setCity_fb_link(String city_fb_link) {
        this.city_fb_link = city_fb_link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<HomeFeatureProperty> getData() {
        return data;
    }

    public void setData(ArrayList<HomeFeatureProperty> data) {
        this.data = data;
    }
}
