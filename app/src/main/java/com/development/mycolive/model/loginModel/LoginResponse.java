package com.development.mycolive.model.loginModel;

import com.development.mycolive.model.Data;

public class LoginResponse {
    private int isEmailExist;
    private int status;
    private String message;
    private Data data;

    public int getIsEmailExist() {
        return isEmailExist;
    }

    public void setIsEmailExist(int isEmailExist) {
        this.isEmailExist = isEmailExist;
    }

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
