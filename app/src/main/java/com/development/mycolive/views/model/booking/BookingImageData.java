package com.development.mycolive.views.model.booking;

import com.google.gson.annotations.SerializedName;

public class BookingImageData {
    private String id;
    @SerializedName("room_id")
    private String roomId;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
