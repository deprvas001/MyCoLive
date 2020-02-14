package com.development.mycolive.model.forgotModel;

public class ForgotRequestModel {
    private String email;

    public ForgotRequestModel() {
    }

    public ForgotRequestModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
