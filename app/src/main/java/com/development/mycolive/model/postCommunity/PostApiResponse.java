package com.development.mycolive.model.postCommunity;

import com.development.mycolive.model.viewCommunityModel.CommentResponse;

public class PostApiResponse {

    public PostResponse response;
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

    public PostApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public PostApiResponse(PostResponse response) {
        this.response = response;
    }

    public PostApiResponse(Throwable error) {
        this.error = error;
    }

    public PostApiResponse(String message) {
        this.message = message;
    }

    public PostApiResponse(int status) {
        this.status = status;
    }

    public PostResponse getResponse() {
        return response;
    }

    public void setResponse(PostResponse response) {
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
