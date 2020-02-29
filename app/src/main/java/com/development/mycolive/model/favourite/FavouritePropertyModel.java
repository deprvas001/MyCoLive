package com.development.mycolive.model.favourite;

import java.util.List;

public class FavouritePropertyModel {
    private String id;
    private String room_apartment;
    private String price;
    private String no_of_bathroom;
    private String near_by_area;
    private String districtName;
    private String number_of_room;
    private String name;
    private String address;
    private List<PropertyImage> image;
    private String favourites;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNo_of_bathroom() {
        return no_of_bathroom;
    }

    public void setNo_of_bathroom(String no_of_bathroom) {
        this.no_of_bathroom = no_of_bathroom;
    }

    public String getNear_by_area() {
        return near_by_area;
    }

    public void setNear_by_area(String near_by_area) {
        this.near_by_area = near_by_area;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(String number_of_room) {
        this.number_of_room = number_of_room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PropertyImage> getImage() {
        return image;
    }

    public void setImage(List<PropertyImage> image) {
        this.image = image;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }
}
