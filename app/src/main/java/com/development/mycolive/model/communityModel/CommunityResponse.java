package com.development.mycolive.model.communityModel;

import java.util.List;

public class CommunityResponse {
    private int status;
    private String message;
    private List<AllPost> data;

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

    public List<AllPost> getData() {
        return data;
    }

    public void setData(List<AllPost> data) {
        this.data = data;
    }
}
