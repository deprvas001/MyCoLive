package com.development.mycolive.model.viewCommunityModel;

import java.util.List;

public class ViewCommunityModel {
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
    private List<CommentReply> comment_reply;
    private String url_for_share;


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

    public String getUrl_for_share() {
        return url_for_share;
    }

    public void setUrl_for_share(String url_for_share) {
        this.url_for_share = url_for_share;
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

    public List<CommentReply> getComment_reply() {
        return comment_reply;
    }

    public void setComment_reply(List<CommentReply> comment_reply) {
        this.comment_reply = comment_reply;
    }
}
