package com.development.mycolive.model.searchDetailPage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApartmentData {
    private String id;
    @SerializedName("room_apartment")
    private String roomApartment;
    @SerializedName("apartment_name")
    private String apartmentName;
    @SerializedName("post_code")
    private String postCode;
    @SerializedName("university_id")
    private String universityId;
    private String district;
    private String city;
    @SerializedName("number_of_room")
    private String numberOfRoom;
    private String address;
    @SerializedName("near_by_area")
    private String nearByArea;
    private String latitude;
    private String longitude;
    private String description;
    @SerializedName("total_price")
    private String totalPrice;
    @SerializedName("fb_connected")
    private String fbConnected;
    @SerializedName("total_room")
    private String totalRoom;
    private String totalBathroom;
    private List<RoomImage> image;
    private List<RoomImage> facility;
    @SerializedName("price_levels")
    private List<PriceLevel> privceLevelList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomApartment() {
        return roomApartment;
    }

    public void setRoomApartment(String roomApartment) {
        this.roomApartment = roomApartment;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNearByArea() {
        return nearByArea;
    }

    public void setNearByArea(String nearByArea) {
        this.nearByArea = nearByArea;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFbConnected() {
        return fbConnected;
    }

    public void setFbConnected(String fbConnected) {
        this.fbConnected = fbConnected;
    }

    public String getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(String totalRoom) {
        this.totalRoom = totalRoom;
    }

    public String getTotalBathroom() {
        return totalBathroom;
    }

    public void setTotalBathroom(String totalBathroom) {
        this.totalBathroom = totalBathroom;
    }

    public List<RoomImage> getImage() {
        return image;
    }

    public void setImage(List<RoomImage> image) {
        this.image = image;
    }

    public List<RoomImage> getFacility() {
        return facility;
    }

    public void setFacility(List<RoomImage> facility) {
        this.facility = facility;
    }

    public List<PriceLevel> getPrivceLevelList() {
        return privceLevelList;
    }

    public void setPrivceLevelList(List<PriceLevel> privceLevelList) {
        this.privceLevelList = privceLevelList;
    }

}
