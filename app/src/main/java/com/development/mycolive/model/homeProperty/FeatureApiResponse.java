package com.development.mycolive.model.homeProperty;

import com.development.mycolive.model.home.HomeResponse;

public class FeatureApiResponse {
    public FeatureResponse response;
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

    public FeatureApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

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
