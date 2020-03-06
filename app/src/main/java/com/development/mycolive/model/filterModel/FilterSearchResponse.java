package com.development.mycolive.model.filterModel;

public class FilterSearchResponse {
    private int status;
    private String message;
    private FilterResponseData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FilterResponseData getData() {
        return data;
    }

    public void setData(FilterResponseData data) {
        this.data = data;
    }
}
