package com.development.mycolive.model.termscondition;

import java.util.List;

public class TermRequest {
    private List<String> room_id;
    private String early_check;
    private String duration;
    private String daterange;

    public List<String> getRoom_id() {
        return room_id;
    }

    public void setRoom_id(List<String> room_id) {
        this.room_id = room_id;
    }

    public String getEarly_check() {
        return early_check;
    }

    public void setEarly_check(String early_check) {
        this.early_check = early_check;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDaterange() {
        return daterange;
    }

    public void setDaterange(String daterange) {
        this.daterange = daterange;
    }
}
