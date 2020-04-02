package com.development.mycolive.model.homeProperty;

import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.propertyDetailModel.FacilityData;

import java.util.ArrayList;
import java.util.List;

public class FeatureResponse {
    private String message;
    private int status;
    private ArrayList<HomeFeatureProperty> data;

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
