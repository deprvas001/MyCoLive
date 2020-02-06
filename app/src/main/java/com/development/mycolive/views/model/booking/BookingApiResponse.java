package com.development.mycolive.views.model.booking;

import com.development.mycolive.views.model.loginModel.LoginResponse;

public class BookingApiResponse {
    public BookingResponse response;
    private Throwable error;
    private String message;
    private int status;

    public BookingApiResponse(BookingResponse response) {
        this.response = response;
    }

    public BookingApiResponse(Throwable error) {
        this.error = error;
    }

    public BookingApiResponse(int status) {
        this.status = status;
    }

    public BookingResponse getResponse() {
        return response;
    }

    public void setResponse(BookingResponse response) {
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
