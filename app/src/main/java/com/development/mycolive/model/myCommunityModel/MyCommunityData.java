package com.development.mycolive.model.myCommunityModel;

import com.development.mycolive.model.communityModel.AllPost;
import com.google.gson.annotations.Expose;

import java.util.List;

public class MyCommunityData {
    private int total;
    private int total_comment;
    private int total_liked;
    private List<MyPostComment> my_post;
    private List<MyPostComment> my_comments;

    private List<MyPostComment> my_liked;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_comment() {
        return total_comment;
    }

    public void setTotal_comment(int total_comment) {
        this.total_comment = total_comment;
    }

    public int getTotal_liked() {
        return total_liked;
    }

    public void setTotal_liked(int total_liked) {
        this.total_liked = total_liked;
    }

    public List<MyPostComment> getMy_post() {
        return my_post;
    }

    public void setMy_post(List<MyPostComment> my_post) {
        this.my_post = my_post;
    }

    public List<MyPostComment> getMy_comments() {
        return my_comments;
    }

    public void setMy_comments(List<MyPostComment> my_comments) {
        this.my_comments = my_comments;
    }

    public List<MyPostComment> getMy_liked() {
        return my_liked;
    }

    public void setMy_liked(List<MyPostComment> my_liked) {
        this.my_liked = my_liked;
    }
}
