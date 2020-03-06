package com.development.mycolive.model.myCommunityModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.development.mycolive.views.activity.myCommunity.MyCommunity;

public class MyCommunityComment implements Parcelable {
    private String id;
    private String parent_id;
    private String comment;
    private String date;
    private String post_type;
    private String city_name;
    private String university;
    private String user;
    private String profile;


    protected MyCommunityComment(Parcel in) {
        id = in.readString();
        parent_id = in.readString();
        comment = in.readString();
        date = in.readString();
        post_type = in.readString();
        city_name = in.readString();
        university = in.readString();
        user = in.readString();
        profile = in.readString();
    }

    public static final Creator<MyCommunityComment> CREATOR = new Creator<MyCommunityComment>() {
        @Override
        public MyCommunityComment createFromParcel(Parcel in) {
            return new MyCommunityComment(in);
        }

        @Override
        public MyCommunityComment[] newArray(int size) {
            return new MyCommunityComment[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(parent_id);
        parcel.writeString(comment);
        parcel.writeString(date);
        parcel.writeString(post_type);
        parcel.writeString(city_name);
        parcel.writeString(university);
        parcel.writeString(user);
        parcel.writeString(profile);
    }
}
