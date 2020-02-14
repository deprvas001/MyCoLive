package com.development.mycolive.model.booking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingResponse {
    private int status;
    private String message;
    @SerializedName("data")
    private List<BookingData> bookingDataList;

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

    public List<BookingData> getBookingDataList() {
        return bookingDataList;
    }

    public void setBookingDataList(List<BookingData> bookingDataList) {
        this.bookingDataList = bookingDataList;
    }
}
