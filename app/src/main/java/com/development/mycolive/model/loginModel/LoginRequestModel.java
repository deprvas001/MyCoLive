package com.development.mycolive.model.loginModel;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    private String email;
    private String password;
    @SerializedName("login_type")
    private String loginType;
    @SerializedName("social_id")
    private String socailId;

    public LoginRequestModel(String email, String password, String loginType, String socailId) {
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.socailId = socailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getSocailId() {
        return socailId;
    }

    public void setSocailId(String socailId) {
        this.socailId = socailId;
    }
}
