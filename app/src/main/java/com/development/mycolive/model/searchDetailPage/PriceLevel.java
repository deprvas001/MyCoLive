package com.development.mycolive.model.searchDetailPage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PriceLevel implements Parcelable {
    private String id;
    @SerializedName("room_id")
    private String roomId;
    private String label;
    private String price;

    protected PriceLevel(Parcel in) {
        id = in.readString();
        roomId = in.readString();
        label = in.readString();
        price = in.readString();
    }

    public static final Creator<PriceLevel> CREATOR = new Creator<PriceLevel>() {
        @Override
        public PriceLevel createFromParcel(Parcel in) {
            return new PriceLevel(in);
        }

        @Override
        public PriceLevel[] newArray(int size) {
            return new PriceLevel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(roomId);
        parcel.writeString(label);
        parcel.writeString(price);
    }
}
