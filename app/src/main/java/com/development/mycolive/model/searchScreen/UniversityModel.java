package com.development.mycolive.model.searchScreen;

import androidx.annotation.NonNull;

public class UniversityModel {
    private String id;
    private String district_id;
    private String university_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    @NonNull
    @Override
    public String toString() {
        return university_name;
    }
}
