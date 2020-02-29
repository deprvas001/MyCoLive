package com.development.mycolive.model.searchFilterModel;

public class FilterResponse {
  private String status;
  private FilterData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FilterData getData() {
        return data;
    }

    public void setData(FilterData data) {
        this.data = data;
    }
}
