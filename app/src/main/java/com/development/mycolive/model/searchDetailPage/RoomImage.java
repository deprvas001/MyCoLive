package com.development.mycolive.model.searchDetailPage;

import com.google.gson.annotations.SerializedName;

public class RoomImage {
    private String image;
    private String facility;
    @SerializedName("facility_icon")
    private String facilityIcon;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFacilityIcon() {
        return facilityIcon;
    }

    public void setFacilityIcon(String facilityIcon) {
        this.facilityIcon = facilityIcon;
    }
}
