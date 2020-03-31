package com.development.mycolive.model.logout;

import com.development.mycolive.model.postCommunity.PostResponse;

public class LogoutApiResponse {
    public LogoutResponse response;
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

    public LogoutApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public LogoutApiResponse(LogoutResponse response) {
        this.response = response;
    }

    public LogoutApiResponse(Throwable error) {
        this.error = error;
    }

    public LogoutApiResponse(String message) {
        this.message = message;
    }

    public LogoutApiResponse(int status) {
        this.status = status;
    }

    public LogoutResponse getResponse() {
        return response;
    }

    public void setResponse(LogoutResponse response) {
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
