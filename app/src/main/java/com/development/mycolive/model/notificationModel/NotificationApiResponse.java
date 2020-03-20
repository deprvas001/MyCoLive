package com.development.mycolive.model.notificationModel;

import com.development.mycolive.model.myCommunityModel.MyCommunityResponse;

public class NotificationApiResponse {
    public NotificationResponse response;
    private Throwable error;
    private String message;
    private int status;

    public NotificationApiResponse(NotificationResponse response) {
        this.response = response;
    }

    public NotificationApiResponse(Throwable error) {
        this.error = error;
    }

    public NotificationApiResponse(String message) {
        this.message = message;
    }

    public NotificationApiResponse(int status) {
        this.status = status;
    }

    public NotificationResponse getResponse() {
        return response;
    }

    public void setResponse(NotificationResponse response) {
        this.response = response;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
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
}
