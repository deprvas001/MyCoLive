package com.development.mycolive.model.favourite;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomateData implements Parcelable {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String dob;
    private String late;
    private String lang;
    private String post_code;
    private String address;
    private String age;
    private String country;
    private String city_name;
    private String disctrict;
    private String university_name;
    private String facebook_connected_yn;
    private String facebook_id;
    private String google_id;
    private String instagram_id;
    private String profile_image;
    private String favourites;

    protected RoomateData(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        gender = in.readString();
        dob = in.readString();
        late = in.readString();
        lang = in.readString();
        post_code = in.readString();
        address = in.readString();
        age = in.readString();
        country = in.readString();
        city_name = in.readString();
        disctrict = in.readString();
        university_name = in.readString();
        facebook_connected_yn = in.readString();
        facebook_id = in.readString();
        google_id = in.readString();
        instagram_id = in.readString();
        profile_image = in.readString();
        favourites = in.readString();
    }

    public static final Creator<RoomateData> CREATOR = new Creator<RoomateData>() {
        @Override
        public RoomateData createFromParcel(Parcel in) {
            return new RoomateData(in);
        }

        @Override
        public RoomateData[] newArray(int size) {
            return new RoomateData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDisctrict() {
        return disctrict;
    }

    public void setDisctrict(String disctrict) {
        this.disctrict = disctrict;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getFacebook_connected_yn() {
        return facebook_connected_yn;
    }

    public void setFacebook_connected_yn(String facebook_connected_yn) {
        this.facebook_connected_yn = facebook_connected_yn;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getInstagram_id() {
        return instagram_id;
    }

    public void setInstagram_id(String instagram_id) {
        this.instagram_id = instagram_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(mobile);
        parcel.writeString(gender);
        parcel.writeString(dob);
        parcel.writeString(late);
        parcel.writeString(lang);
        parcel.writeString(post_code);
        parcel.writeString(address);
        parcel.writeString(age);
        parcel.writeString(country);
        parcel.writeString(city_name);
        parcel.writeString(disctrict);
        parcel.writeString(university_name);
        parcel.writeString(facebook_connected_yn);
        parcel.writeString(facebook_id);
        parcel.writeString(google_id);
        parcel.writeString(instagram_id);
        parcel.writeString(profile_image);
        parcel.writeString(favourites);
    }
}
