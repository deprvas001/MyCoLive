package com.development.mycolive.model.communityModel;

public class SearchCommunityApiResponse {
    public SearchCommunityResponse communityResponse;
    private Throwable error;
    private String message;
    private int status;

    public SearchCommunityApiResponse(SearchCommunityResponse communityResponse) {
        this.communityResponse = communityResponse;
    }

    public SearchCommunityApiResponse(Throwable error) {
        this.error = error;
    }

    public SearchCommunityApiResponse(String message) {
        this.message = message;
    }

    public SearchCommunityApiResponse(int status) {
        this.status = status;
    }

    public SearchCommunityResponse getCommunityResponse() {
        return communityResponse;
    }

    public void setCommunityResponse(SearchCommunityResponse communityResponse) {
        this.communityResponse = communityResponse;
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
