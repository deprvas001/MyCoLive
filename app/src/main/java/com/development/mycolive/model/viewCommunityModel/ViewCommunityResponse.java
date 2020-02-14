package com.development.mycolive.model.viewCommunityModel;

import java.util.List;

public class ViewCommunityResponse {
    private String message;
    private int status;
    private List<ViewCommunityModel> data;

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

    public List<ViewCommunityModel> getData() {
        return data;
    }

    public void setData(List<ViewCommunityModel> data) {
        this.data = data;
    }
}
