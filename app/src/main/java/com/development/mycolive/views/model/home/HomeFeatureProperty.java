package com.development.mycolive.views.model.home;

import com.google.gson.annotations.SerializedName;

public class HomeFeatureProperty {
    private String id;
    private String propertyName;
    private String propertyType;
    @SerializedName("near_by_area")
    private String nearByArea;
    @SerializedName("district_name")
    private String districtName;
    @SerializedName("fclink")
    private String fcLink;
    @SerializedName("fplink")
    private String fpLink;
    @SerializedName("metadesc")
    private String metaDesc;
    @SerializedName("metatitle")
    private String metaTitle;
    @SerializedName("metakey")
    private String metaKey;
    private String address;
    private String description;
    private String maxOccupy;
    private String noOfBathroom;
    private String roomSize;
    private String numberOfRoom;
    private String price;
    private String location;
    private String createdDate;
    private String roomApart;
    private String image;
    private String rating;
    private String favourites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getNearByArea() {
        return nearByArea;
    }

    public void setNearByArea(String nearByArea) {
        this.nearByArea = nearByArea;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getFcLink() {
        return fcLink;
    }

    public void setFcLink(String fcLink) {
        this.fcLink = fcLink;
    }

    public String getFpLink() {
        return fpLink;
    }

    public void setFpLink(String fpLink) {
        this.fpLink = fpLink;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxOccupy() {
        return maxOccupy;
    }

    public void setMaxOccupy(String maxOccupy) {
        this.maxOccupy = maxOccupy;
    }

    public String getNoOfBathroom() {
        return noOfBathroom;
    }

    public void setNoOfBathroom(String noOfBathroom) {
        this.noOfBathroom = noOfBathroom;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRoomApart() {
        return roomApart;
    }

    public void setRoomApart(String roomApart) {
        this.roomApart = roomApart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }
}
