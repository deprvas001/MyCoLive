package com.development.mycolive.model.myCommunityModel;

public class MyCommunityResponse {
    private String message;
    private int status;
    private MyCommunityData data;

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

    public MyCommunityData getData() {
        return data;
    }

    public void setData(MyCommunityData data) {
        this.data = data;
    }
}
