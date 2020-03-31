package com.development.mycolive.model.paymentModel;

import com.development.mycolive.model.postCommunity.PostResponse;

public class PaymentApiResponse {

    public PaymentResponse response;
    private Throwable error;
    private String message;
    private int status;
    private int status_code;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public PaymentApiResponse(String message, int status, int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }

    public PaymentApiResponse(PaymentResponse response) {
        this.response = response;
    }

    public PaymentApiResponse(Throwable error) {
        this.error = error;
    }

    public PaymentApiResponse(String message) {
        this.message = message;
    }

    public PaymentApiResponse(int status) {
        this.status = status;
    }

    public PaymentResponse getResponse() {
        return response;
    }

    public void setResponse(PaymentResponse response) {
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
