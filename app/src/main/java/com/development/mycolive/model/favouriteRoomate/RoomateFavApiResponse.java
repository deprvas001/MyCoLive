package com.development.mycolive.model.favouriteRoomate;

import com.development.mycolive.model.favourite.FavouriteResponse;

public class RoomateFavApiResponse {

    public RoomateFavResponse response;
    private Throwable error;
    private String message;
    private int status;

    private int status_code;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public RoomateFavApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }


    public RoomateFavApiResponse(RoomateFavResponse response) {
        this.response = response;
    }

    public RoomateFavApiResponse(Throwable error) {
        this.error = error;
    }

    public RoomateFavApiResponse(String message) {
        this.message = message;
    }

    public RoomateFavApiResponse(int status) {
        this.status = status;
    }

    public RoomateFavResponse getResponse() {
        return response;
    }

    public void setResponse(RoomateFavResponse response) {
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



