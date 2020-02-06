package com.development.mycolive.views.model.editProfile;

import com.development.mycolive.views.model.bookingHistory.BookingHistoryResponse;

public class ProfileApiResponse {
    public ProfileResponse response;
    private Throwable error;
    private String message;
    private int status;

    public ProfileApiResponse(ProfileResponse response) {
        this.response = response;
    }

    public ProfileApiResponse(Throwable error) {
        this.error = error;
    }

    public ProfileApiResponse(String message) {
        this.message = message;
    }

    public ProfileApiResponse(int status) {
        this.status = status;
    }


    public ProfileResponse getResponse() {
        return response;
    }

    public void setResponse(ProfileResponse response) {
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
