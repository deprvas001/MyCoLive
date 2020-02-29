package com.development.mycolive.model.home;

public class HomeApiResponse {
    public HomeResponse response;
    private Throwable error;
    private String message;
    private int status;

    public HomeApiResponse(HomeResponse response) {
        this.response = response;
    }

    public HomeApiResponse(Throwable error) {
        this.error = error;
    }

    public HomeApiResponse(String message) {
        this.message = message;
    }

    public HomeApiResponse(int status) {
        this.status = status;
    }

    public HomeResponse getResponse() {
        return response;
    }

    public void setResponse(HomeResponse response) {
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
