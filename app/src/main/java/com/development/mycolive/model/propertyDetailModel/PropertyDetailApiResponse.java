package com.development.mycolive.model.propertyDetailModel;

import com.development.mycolive.model.booking.BookingResponse;

public class PropertyDetailApiResponse {
    public PropertyDetailResponse response;
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

    public PropertyDetailApiResponse (String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public PropertyDetailApiResponse(PropertyDetailResponse response) {
        this.response = response;
    }

    public PropertyDetailApiResponse(Throwable error) {
        this.error = error;
    }

    public PropertyDetailApiResponse(String message) {
        this.message = message;
    }

    public PropertyDetailApiResponse(int status) {
        this.status = status;
    }

    public PropertyDetailResponse getResponse() {
        return response;
    }

    public void setResponse(PropertyDetailResponse response) {
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
