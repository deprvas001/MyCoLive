package com.development.mycolive.model.termscondition;

import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.model.signup.SignUpResponse;

public class TermCondApiResponse {
    public TermConditionResponse response;
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

    public TermCondApiResponse(String message,int status,int status_code) {
        this.message = message;
        this.status = status;
        this.status_code = status_code;
    }


    public TermCondApiResponse(TermConditionResponse response) {
        this.response = response;
    }

    public TermCondApiResponse(Throwable error) {
        this.error = error;
    }

    public TermCondApiResponse(int status) {
        this.status = status;
    }

    public TermConditionResponse getResponse() {
        return response;
    }

    public void setResponse(TermConditionResponse response) {
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
