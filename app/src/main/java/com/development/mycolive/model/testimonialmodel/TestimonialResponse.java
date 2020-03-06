package com.development.mycolive.model.testimonialmodel;

public class TestimonialResponse {
    private int status;
    private String message;
    private TestimonialData data;

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

    public TestimonialData getData() {
        return data;
    }

    public void setData(TestimonialData data) {
        this.data = data;
    }
}
