package com.development.mycolive.views.model.editProfile;

import java.util.List;

public class ProfileResponse {
    private int status;
    private String message;
    private List<ProfileData> data;

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

    public List<ProfileData> getData() {
        return data;
    }

    public void setData(List<ProfileData> data) {
        this.data = data;
    }
}
