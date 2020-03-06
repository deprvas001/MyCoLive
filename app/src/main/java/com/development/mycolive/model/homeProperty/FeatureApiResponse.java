package com.development.mycolive.model.homeProperty;

import com.development.mycolive.model.home.HomeResponse;

public class FeatureApiResponse {
    public FeatureResponse response;
    private Throwable error;
    private String message;
    private int status;

    public FeatureApiResponse(FeatureResponse response) {
        this.response = response;
    }

    public FeatureApiResponse(Throwable error) {
        this.error = error;
    }

    public FeatureApiResponse(String message) {
        this.message = message;
    }

    public FeatureApiResponse(int status) {
        this.status = status;
    }

    public FeatureResponse getResponse() {
        return response;
    }

    public void setResponse(FeatureResponse response) {
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
