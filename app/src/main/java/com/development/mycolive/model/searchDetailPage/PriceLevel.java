package com.development.mycolive.model.searchDetailPage;

import com.google.gson.annotations.SerializedName;

public class PriceLevel {
    private String id;
    @SerializedName("room_id")
    private String roomId;
    private String label;
    private String price;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
