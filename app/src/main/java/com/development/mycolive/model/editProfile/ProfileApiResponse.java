package com.development.mycolive.model.editProfile;

public class ProfileApiResponse {
    public ProfileResponse response;
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

    public ProfileApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

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
