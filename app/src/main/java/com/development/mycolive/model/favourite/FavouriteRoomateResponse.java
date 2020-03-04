package com.development.mycolive.model.favourite;

import java.util.List;

public class FavouriteRoomateResponse {
    private int status;
    private String message;
    private List<RoomateData> data;

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

    public List<RoomateData> getData() {
        return data;
    }

    public void setData(List<RoomateData> data) {
        this.data = data;
    }
}
