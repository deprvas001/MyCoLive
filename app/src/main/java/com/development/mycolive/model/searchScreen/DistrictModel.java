package com.development.mycolive.model.searchScreen;


import androidx.annotation.NonNull;

public class DistrictModel {
 private String id;
 private String district_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    @NonNull
    @Override
    public String toString() {
        return district_name;
    }
}
