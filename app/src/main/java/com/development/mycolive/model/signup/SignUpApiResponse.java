package com.development.mycolive.model.signup;

import com.development.mycolive.model.editProfile.ProfileResponse;

public class SignUpApiResponse {
    public SignUpResponse response;
    private Throwable error;
    private String message;
    private int status;

    public SignUpApiResponse(SignUpResponse response) {
        this.response = response;
    }

    public SignUpApiResponse(String message) {
        this.message = message;
    }

    public SignUpApiResponse(Throwable error) {
        this.error = error;
    }

    public SignUpApiResponse(int status) {
        this.status = status;
    }

    public SignUpResponse getResponse() {
        return response;
    }

    public void setResponse(SignUpResponse response) {
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
