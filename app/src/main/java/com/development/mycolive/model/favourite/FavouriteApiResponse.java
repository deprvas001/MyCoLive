package com.development.mycolive.model.favourite;

import com.development.mycolive.model.booking.BookingResponse;

public class FavouriteApiResponse {
    public FavouriteResponse response;
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

    public FavouriteApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public FavouriteApiResponse(FavouriteResponse response) {
        this.response = response;
    }

    public FavouriteApiResponse(Throwable error) {
        this.error = error;
    }

    public FavouriteApiResponse(String message) {
        this.message = message;
    }

    public FavouriteApiResponse(int status) {
        this.status = status;
    }

    public FavouriteResponse getResponse() {
        return response;
    }

    public void setResponse(FavouriteResponse response) {
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
