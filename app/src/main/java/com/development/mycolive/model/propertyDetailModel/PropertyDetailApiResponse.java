package com.development.mycolive.model.propertyDetailModel;

import com.development.mycolive.model.booking.BookingResponse;

public class PropertyDetailApiResponse {
    public PropertyDetailResponse response;
    private Throwable error;
    private String message;
    private int status;

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
