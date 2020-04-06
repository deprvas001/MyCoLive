package com.development.mycolive.model.termscondition;

public class TermConditionResponse {
    private int status;
    private String message;
    private ContractResponse data;

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

    public ContractResponse getData() {
        return data;
    }

    public void setData(ContractResponse data) {
        this.data = data;
    }
}
