package com.development.mycolive.model.favouriteRoomate;

import com.development.mycolive.model.favourite.FavouritePropertyModel;

import java.util.List;

public class RoomateFavResponse {

    private int status;
    private String message;
    private RoomateFavData data;

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

    public RoomateFavData getData() {
        return data;
    }

    public void setData(RoomateFavData data) {
        this.data = data;
    }
}
