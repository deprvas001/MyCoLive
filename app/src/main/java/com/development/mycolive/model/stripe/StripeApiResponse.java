package com.development.mycolive.model.stripe;

import com.development.mycolive.model.paymentModel.PaymentResponse;

public class StripeApiResponse {
    public StripeServerResponse response;
    private Throwable error;
    private String message;
    private int status;

    public StripeApiResponse(StripeServerResponse response) {
        this.response = response;
    }

    public StripeApiResponse(Throwable error) {
        this.error = error;
    }

    public StripeApiResponse(String message) {
        this.message = message;
    }

    public StripeApiResponse(int status) {
        this.status = status;
    }

    public StripeServerResponse getResponse() {
        return response;
    }

    public void setResponse(StripeServerResponse response) {
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
