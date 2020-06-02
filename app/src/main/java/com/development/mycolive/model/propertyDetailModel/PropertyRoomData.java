package com.development.mycolive.model.propertyDetailModel;


import android.os.Parcel;
import android.os.Parcelable;

import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.searchDetailPage.PriceLevel;

import java.util.List;

public class PropertyRoomData  implements Parcelable {
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
    private List<HomeSlider> image_slider;
    private List<FacilityData> facility;
    private List<PriceLevel> price_levels;
    private String favourites;
    private String video_link;

    protected PropertyRoomData(Parcel in) {
        id = in.readString();
        room_apartment = in.readString();
        apartment_name = in.readString();
        post_code = in.readString();
        district = in.readString();
        university_id = in.readString();
        city = in.readString();
        address = in.readString();
        near_by_area = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        description = in.readString();
        total_price = in.readString();
        byte tmpFb_connected = in.readByte();
        fb_connected = tmpFb_connected == 0 ? null : tmpFb_connected == 1;
        total_room = in.readString();
        no_of_bathroom = in.readString();
        image_slider =  (List<HomeSlider>) in.readValue(HomeSlider.class.getClassLoader());
        facility =  (List<FacilityData>) in.readValue(FacilityData.class.getClassLoader());
        price_levels =  (List<PriceLevel>) in.readValue(PriceLevel.class.getClassLoader());
        favourites = in.readString();
        video_link = in.readString();
    }

    public static final Creator<PropertyRoomData> CREATOR = new Creator<PropertyRoomData>() {
        @Override
        public PropertyRoomData createFromParcel(Parcel in) {
            return new PropertyRoomData(in);
        }

        @Override
        public PropertyRoomData[] newArray(int size) {
            return new PropertyRoomData[size];
        }
    };

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

    public List<HomeSlider> getImage_slider() {
        return image_slider;
    }

    public void setImage_slider(List<HomeSlider> image_slider) {
        this.image_slider = image_slider;
    }

    public List<FacilityData> getFacility() {
        return facility;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    public void setFacility(List<FacilityData> facility) {
        this.facility = facility;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public List<PriceLevel> getPrice_levels() {
        return price_levels;
    }



    public void setPrice_levels(List<PriceLevel> price_levels) {
        this.price_levels = price_levels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(room_apartment);
        parcel.writeString(apartment_name);
        parcel.writeString(post_code);
        parcel.writeString(district);
        parcel.writeString(university_id);
        parcel.writeString(city);
        parcel.writeString(address);
        parcel.writeString(near_by_area);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(description);
        parcel.writeString(total_price);
        parcel.writeByte((byte) (fb_connected == null ? 0 : fb_connected ? 1 : 2));
        parcel.writeString(total_room);
        parcel.writeString(no_of_bathroom);
        parcel.writeValue(image_slider);
        parcel.writeValue(facility);
        parcel.writeValue(price_levels);
        parcel.writeString(favourites);
        parcel.writeString(video_link);
    }
}
