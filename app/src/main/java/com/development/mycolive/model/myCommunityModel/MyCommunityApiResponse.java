package com.development.mycolive.model.myCommunityModel;

import com.development.mycolive.model.homeProperty.FeatureResponse;

public class MyCommunityApiResponse {
    public MyCommunityResponse response;
    private Throwable error;
    private String message;
    private int status;

    public MyCommunityApiResponse(MyCommunityResponse response) {
        this.response = response;
    }

    public MyCommunityApiResponse(Throwable error) {
        this.error = error;
    }

    public MyCommunityApiResponse(String message) {
        this.message = message;
    }

    public MyCommunityApiResponse(int status) {
        this.status = status;
    }

    public MyCommunityResponse getResponse() {
        return response;
    }

    public void setResponse(MyCommunityResponse response) {
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
