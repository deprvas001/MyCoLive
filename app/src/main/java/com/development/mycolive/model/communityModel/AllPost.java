package com.development.mycolive.model.communityModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AllPost implements Parcelable {
    private String id;
    private String parent_comment_id;
    private String post_type;
    private String city;
    private String university;
    private String comment;
    private List<String> image;
    private String created_by;
    private String date;
    private String post_type_name;
    private String city_name;
    private String university_name;
    private String user_name;
    private String profile_image;
    private String total_likes;
    private String user_like_this_comment;
    private String total_reply_comment;

   // private String comment_reply;

    protected AllPost(Parcel in) {
        id = in.readString();
        parent_comment_id = in.readString();
        post_type = in.readString();
        city = in.readString();
        university = in.readString();
        comment = in.readString();
        image = in.createStringArrayList();
        created_by = in.readString();
        date = in.readString();
        post_type_name = in.readString();
        city_name = in.readString();
        university_name = in.readString();
        user_name = in.readString();
        profile_image = in.readString();
        total_likes = in.readString();
        user_like_this_comment = in.readString();
        total_reply_comment = in.readString();
       // comment_reply = in.readString();
    }

    public static final Creator<AllPost> CREATOR = new Creator<AllPost>() {
        @Override
        public AllPost createFromParcel(Parcel in) {
            return new AllPost(in);
        }

        @Override
        public AllPost[] newArray(int size) {
            return new AllPost[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(String parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost_type_name() {
        return post_type_name;
    }

    public void setPost_type_name(String post_type_name) {
        this.post_type_name = post_type_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getTotal_likes() {
        return total_likes;
    }

    public void setTotal_likes(String total_likes) {
        this.total_likes = total_likes;
    }

    public String getUser_like_this_comment() {
        return user_like_this_comment;
    }

    public void setUser_like_this_comment(String user_like_this_comment) {
        this.user_like_this_comment = user_like_this_comment;
    }

    public String getTotal_reply_comment() {
        return total_reply_comment;
    }

    public void setTotal_reply_comment(String total_reply_comment) {
        this.total_reply_comment = total_reply_comment;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(parent_comment_id);
        parcel.writeString(post_type);
        parcel.writeString(city);
        parcel.writeString(university);
        parcel.writeString(comment);
        parcel.writeStringList(image);
        parcel.writeString(created_by);
        parcel.writeString(date);
        parcel.writeString(post_type_name);
        parcel.writeString(city_name);
        parcel.writeString(university_name);
        parcel.writeString(user_name);
        parcel.writeString(profile_image);
        parcel.writeString(total_likes);
        parcel.writeString(user_like_this_comment);
        parcel.writeString(total_reply_comment);
     //   parcel.writeString(comment_reply);
    }
}
