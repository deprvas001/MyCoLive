package com.development.mycolive.model.viewCommunityModel;

public class CommentApiResponse {
    public CommentResponse response;
    private Throwable error;
    private String message;
    private int status;


    public CommentApiResponse(CommentResponse response) {
        this.response = response;
    }

    public CommentApiResponse(Throwable error) {
        this.error = error;
    }

    public CommentApiResponse(String message) {
        this.message = message;
    }

    public CommentApiResponse(int status) {
        this.status = status;
    }

    public CommentResponse getResponse() {
        return response;
    }

    public void setResponse(CommentResponse response) {
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
