package com.development.mycolive.model.searchFilterModel;

public class FilterApiResponse {
    public FilterResponse filterResponse;
    public Throwable error;
    public String message;
    public int status;

    public FilterApiResponse(FilterResponse filterResponse) {
        this.filterResponse = filterResponse;
    }

    public FilterApiResponse(Throwable error) {
        this.error = error;
    }

    public FilterApiResponse(String message) {
        this.message = message;
    }

    public FilterApiResponse(int status) {
        this.status = status;
    }

    public FilterResponse getFilterResponse() {
        return filterResponse;
    }

    public void setFilterResponse(FilterResponse filterResponse) {
        this.filterResponse = filterResponse;
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
