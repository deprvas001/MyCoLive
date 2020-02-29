package com.development.mycolive.model.editProfile;

public class ProfilePostApiResponse {
    public PostProfileResponse response;
    private Throwable error;
    private String message;
    private int status;

    public ProfilePostApiResponse(PostProfileResponse response) {
        this.response = response;
    }

    public ProfilePostApiResponse(Throwable error) {
        this.error = error;
    }

    public ProfilePostApiResponse(String message) {
        this.message = message;
    }

    public ProfilePostApiResponse(int status) {
        this.status = status;
    }

    public PostProfileResponse getResponse() {
        return response;
    }

    public void setResponse(PostProfileResponse response) {
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
