package com.development.mycolive.model.viewCommunityModel;


public class ViewCommunityApiResponse {
    public ViewCommunityResponse response;
    private Throwable error;
    private String message;
    private int status;

    public ViewCommunityApiResponse(ViewCommunityResponse response) {
        this.response = response;
    }

    public ViewCommunityApiResponse(Throwable error) {
        this.error = error;
    }

    public ViewCommunityApiResponse(String message) {
        this.message = message;
    }

    public ViewCommunityApiResponse(int status) {
        this.status = status;
    }

    public ViewCommunityResponse getResponse() {
        return response;
    }

    public void setResponse(ViewCommunityResponse response) {
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
