package com.development.mycolive.model.favourite;

public class FavouriteRoomateApiResponse {
    public FavouriteRoomateResponse response;
    private Throwable error;
    private String message;
    private int status;

    public FavouriteRoomateApiResponse(FavouriteRoomateResponse response) {
        this.response = response;
    }

    public FavouriteRoomateApiResponse(Throwable error) {
        this.error = error;
    }

    public FavouriteRoomateApiResponse(String message) {
        this.message = message;
    }

    public FavouriteRoomateApiResponse(int status) {
        this.status = status;
    }

    public FavouriteRoomateResponse getResponse() {
        return response;
    }

    public void setResponse(FavouriteRoomateResponse response) {
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
