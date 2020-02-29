package com.development.mycolive.model.favourite;

import java.util.List;

public class FavouriteResponse {
    private int status;
    private String message;
    private List<FavouritePropertyModel> data;

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

    public List<FavouritePropertyModel> getData() {
        return data;
    }

    public void setData(List<FavouritePropertyModel> data) {
        this.data = data;
    }
}
