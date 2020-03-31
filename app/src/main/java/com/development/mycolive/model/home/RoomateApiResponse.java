package com.development.mycolive.model.home;

import com.development.mycolive.model.filterModel.FilterSearchResponse;

public class RoomateApiResponse {

    public RoommateResponse response;
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

    public RoomateApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public RoomateApiResponse(RoommateResponse response) {
        this.response = response;
    }

    public RoomateApiResponse(Throwable error) {
        this.error = error;
    }

    public RoomateApiResponse(String message) {
        this.message = message;
    }

    public RoomateApiResponse(int status) {
        this.status = status;
    }

    public RoommateResponse getResponse() {
        return response;
    }

    public void setResponse(RoommateResponse response) {
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
