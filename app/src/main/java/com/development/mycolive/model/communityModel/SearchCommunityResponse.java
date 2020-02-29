package com.development.mycolive.model.communityModel;

import com.development.mycolive.model.searchDetailPage.SearchData;

import java.util.List;

public class SearchCommunityResponse {
    private String message;
    private int status;
    private SearchCommunityData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SearchCommunityData getData() {
        return data;
    }

    public void setData(SearchCommunityData data) {
        this.data = data;
    }
}
