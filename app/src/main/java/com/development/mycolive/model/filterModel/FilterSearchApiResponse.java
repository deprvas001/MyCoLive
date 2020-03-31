package com.development.mycolive.model.filterModel;

import com.development.mycolive.model.communityModel.CommunityResponse;

public class FilterSearchApiResponse {

    public FilterSearchResponse response;
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

    public FilterSearchApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }


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
