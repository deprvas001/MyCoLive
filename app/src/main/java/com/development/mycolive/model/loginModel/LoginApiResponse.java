package com.development.mycolive.model.loginModel;

public class LoginApiResponse {
    public LoginResponse response;
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

    public LoginApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }


    public LoginApiResponse(int status) {
        this.status = status;
    }


    public LoginApiResponse(LoginResponse response) {
        this.response = response;
        this.error = null;
    }

    public LoginApiResponse(Throwable error) {
        this.error = error;
        this.response = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LoginApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginResponse getResponse() {
        return response;
    }

    public void setResponse(LoginResponse response) {
        this.response = response;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
