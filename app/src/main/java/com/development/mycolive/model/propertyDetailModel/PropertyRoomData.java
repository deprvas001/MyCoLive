package com.development.mycolive.model.propertyDetailModel;


import com.development.mycolive.model.searchDetailPage.PriceLevel;

import java.util.List;

public class PropertyRoomData {
    private String id;
    private String room_apartment;
    private String apartment_name;
    private String post_code;
    private String district;
    private String university_id;
    private String city;
    private String address;
    private String near_by_area;
    private String latitude;
    private String longitude;
    private String description;
    private String total_price;
    private Boolean fb_connected;
    private String total_room;
    private String  no_of_bathroom;
    private List<PropertyImageSlider> image_slider;
    private List<FacilityData> facility;
    private List<PriceLevel> price_levels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_apartment() {
        return room_apartment;
    }

    public void setRoom_apartment(String room_apartment) {
        this.room_apartment = room_apartment;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNear_by_area() {
        return near_by_area;
    }

    public void setNear_by_area(String near_by_area) {
        this.near_by_area = near_by_area;
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

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public Boolean getFb_connected() {
        return fb_connected;
    }

    public void setFb_connected(Boolean fb_connected) {
        this.fb_connected = fb_connected;
    }

    public String getTotal_room() {
        return total_room;
    }

    public void setTotal_room(String total_room) {
        this.total_room = total_room;
    }

    public String getNo_of_bathroom() {
        return no_of_bathroom;
    }

    public void setNo_of_bathroom(String no_of_bathroom) {
        this.no_of_bathroom = no_of_bathroom;
    }

    public List<PropertyImageSlider> getImage_slider() {
        return image_slider;
    }

    public void setImage_slider(List<PropertyImageSlider> image_slider) {
        this.image_slider = image_slider;
    }

    public List<FacilityData> getFacility() {
        return facility;
    }

    public void setFacility(List<FacilityData> facility) {
        this.facility = facility;
    }

    public List<PriceLevel> getPrice_levels() {
        return price_levels;
    }

    public void setPrice_levels(List<PriceLevel> price_levels) {
        this.price_levels = price_levels;
    }
}
