package com.development.mycolive.model.editProfile;

import java.util.List;

public class ProfileResponse {
    private int status;
    private String message;
   // private Data data;
    private ProfileData data;

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

    public ProfileData getData() {
        return data;
    }

    public void setData(ProfileData data) {
        this.data = data;
    }
}
