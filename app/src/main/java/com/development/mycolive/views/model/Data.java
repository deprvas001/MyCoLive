package com.development.mycolive.views.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("user_id")
    private String userId;
    private String name;
    private String email;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("AuthenticateToken")
    private String authenticateToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAuthenticateToken() {
        return authenticateToken;
    }

    public void setAuthenticateToken(String authenticateToken) {
        this.authenticateToken = authenticateToken;
    }
}
