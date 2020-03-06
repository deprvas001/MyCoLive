package com.development.mycolive.model.testimonialmodel;

import com.development.mycolive.model.viewCommunityModel.CommentResponse;

public class TestimonialApiResponse {
    public TestimonialResponse response;
    private Throwable error;
    private String message;
    private int status;

    public TestimonialApiResponse(TestimonialResponse response) {
        this.response = response;

    }

    public TestimonialApiResponse(Throwable error) {
        this.error = error;
    }


    public TestimonialApiResponse(String message) {
        this.message = message;
    }

    public TestimonialApiResponse(int status) {
        this.status = status;
    }

    public TestimonialResponse getResponse() {
        return response;
    }

    public void setResponse(TestimonialResponse response) {
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
