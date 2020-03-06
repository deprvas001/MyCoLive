package com.development.mycolive.model.home;

import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeFeatureProperty {
   private String id;
   private String count;
   private String no_of_bathroom;
   private String name;
   private String number_of_room;
   private String price;
   private String price_without_security;
   private String room_size;
   private String max_occupy;
   private String description;
   private String created_date;
   private String apartment_name;
   private String location;
   private String district_name;
   private String near_by_area;
   private String address;
   private String latitude;
   private String longitude;
   private String fplink;
   private String fclink;
   private String district;
   private List<FacilityData> facilityList;
   private List<HomeSlider> image_slider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNo_of_bathroom() {
        return no_of_bathroom;
    }

    public void setNo_of_bathroom(String no_of_bathroom) {
        this.no_of_bathroom = no_of_bathroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(String number_of_room) {
        this.number_of_room = number_of_room;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_without_security() {
        return price_without_security;
    }

    public void setPrice_without_security(String price_without_security) {
        this.price_without_security = price_without_security;
    }

    public String getRoom_size() {
        return room_size;
    }

    public void setRoom_size(String room_size) {
        this.room_size = room_size;
    }

    public String getMax_occupy() {
        return max_occupy;
    }

    public void setMax_occupy(String max_occupy) {
        this.max_occupy = max_occupy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getNear_by_area() {
        return near_by_area;
    }

    public void setNear_by_area(String near_by_area) {
        this.near_by_area = near_by_area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getFplink() {
        return fplink;
    }

    public void setFplink(String fplink) {
        this.fplink = fplink;
    }

    public String getFclink() {
        return fclink;
    }

    public void setFclink(String fclink) {
        this.fclink = fclink;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<FacilityData> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<FacilityData> facilityList) {
        this.facilityList = facilityList;
    }

    public List<HomeSlider> getImage_slider() {
        return image_slider;
    }

    public void setImage_slider(List<HomeSlider> image_slider) {
        this.image_slider = image_slider;
    }
}
