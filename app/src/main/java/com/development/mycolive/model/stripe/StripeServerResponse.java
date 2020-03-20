package com.development.mycolive.model.stripe;

public class StripeServerResponse {
    private int status;
    private StripeData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public StripeData getData() {
        return data;
    }

    public void setData(StripeData data) {
        this.data = data;
    }
}
