package com.development.mycolive.model.searchDetailPage;


public class SearchDetailApiRsponse {

    public SearchDetailResponse response;
    private Throwable error;
    private String message;
    private int status;

    public SearchDetailApiRsponse(SearchDetailResponse response) {
        this.response = response;
    }

    public SearchDetailApiRsponse(Throwable error) {
        this.error = error;
    }

    public SearchDetailApiRsponse(String message) {
        this.message = message;
    }

    public SearchDetailApiRsponse(int status) {
        this.status = status;
    }


    public SearchDetailResponse getResponse() {
        return response;
    }

    public void setResponse(SearchDetailResponse response) {
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
