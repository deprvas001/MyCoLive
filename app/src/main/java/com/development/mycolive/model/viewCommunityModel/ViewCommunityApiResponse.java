package com.development.mycolive.model.viewCommunityModel;


public class ViewCommunityApiResponse {
    public ViewCommunityResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public ViewCommunityApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }



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
