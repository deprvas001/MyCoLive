package com.development.mycolive.model.notificationModel;

public class NotificationData {

    private String created_by;
    private String created_at;
    private BodyResponse body;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public BodyResponse getBody() {
        return body;
    }

    public void setBody(BodyResponse body) {
        this.body = body;
    }
}
