package com.development.mycolive.model.home;

public class RoommateResponse {
    private int status;
    private String message;
    private RoommateData data;

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

    public RoommateData getData() {
        return data;
    }

    public void setData(RoommateData data) {
        this.data = data;
    }
}
