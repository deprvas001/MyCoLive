package com.development.mycolive.model.bookingHistory;

public class BookingHistoryApiResponse {
    public BookingHistoryResponse response;
    private Throwable error;
    private String message;
    private int status;

    public BookingHistoryApiResponse(BookingHistoryResponse response) {
        this.response = response;
    }

    public BookingHistoryApiResponse(Throwable error) {
        this.error = error;
    }

    public BookingHistoryApiResponse(String message) {
        this.message = message;
    }

    public BookingHistoryApiResponse(int status) {
        this.status = status;
    }

    public BookingHistoryResponse getResponse() {
        return response;
    }

    public void setResponse(BookingHistoryResponse response) {
        this.response = response;
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
