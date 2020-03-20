package com.development.mycolive.model.stripe;

public class StripeRequestBody {
    private String amount;
    private String email;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
