package com.development.mycolive.model.propertyDetailModel;

import java.util.List;

public class PropertyDetailResponse {
    private int status;
    private String message;
    private List<PropertyDetailData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PropertyDetailData> getData() {
        return data;
    }

    public void setData(List<PropertyDetailData> data) {
        this.data = data;
    }
}
