package com.development.mycolive.views.model.bookingHistory;

import java.util.List;

public class BookingHistoryResponse {
    private int status;
    private String  message;
    private List<BookingHistoryData> data;

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

    public List<BookingHistoryData> getData() {
        return data;
    }

    public void setData(List<BookingHistoryData> data) {
        this.data = data;
    }
}


