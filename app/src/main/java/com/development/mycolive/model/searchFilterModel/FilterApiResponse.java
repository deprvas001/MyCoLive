package com.development.mycolive.model.searchFilterModel;

public class FilterApiResponse {
    public FilterResponse filterResponse;
    public Throwable error;
    public String message;
    public int status;

    private int status_code;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public FilterApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }




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
