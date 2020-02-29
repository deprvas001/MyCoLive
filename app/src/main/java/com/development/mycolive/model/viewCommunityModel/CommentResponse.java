package com.development.mycolive.model.viewCommunityModel;

public class CommentResponse {
    private String message;
    private int status;
    private LikeData data;

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

    public LikeData getData() {
        return data;
    }

    public void setData(LikeData data) {
        this.data = data;
    }
}
