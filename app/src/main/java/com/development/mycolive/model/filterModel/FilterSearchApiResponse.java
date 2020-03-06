package com.development.mycolive.model.filterModel;

import com.development.mycolive.model.communityModel.CommunityResponse;

public class FilterSearchApiResponse {

    public FilterSearchResponse response;
    private Throwable error;
    private String message;
    private int status;

    public FilterSearchApiResponse(FilterSearchResponse response) {
        this.response = response;
    }

    public FilterSearchApiResponse(Throwable error) {
        this.error = error;
    }

    public FilterSearchApiResponse(String message) {
        this.message = message;
    }

    public FilterSearchApiResponse(int status) {
        this.status = status;
    }

    public FilterSearchResponse getResponse() {
        return response;
    }

    public void setResponse(FilterSearchResponse response) {
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
