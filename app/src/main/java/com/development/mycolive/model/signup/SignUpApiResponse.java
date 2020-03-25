package com.development.mycolive.model.signup;

import com.development.mycolive.model.editProfile.ProfileResponse;

public class SignUpApiResponse {
    public SignUpResponse response;
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

    public SignUpApiResponse(SignUpResponse response) {
        this.response = response;
    }

    public SignUpApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
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
