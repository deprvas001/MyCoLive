package com.development.mycolive.model.searchFilterModel;

public class FilterResponse {
  private boolean status;

  private FilterData data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean  status) {
        this.status = status;
    }

    public FilterData getData() {
        return data;
    }

    public void setData(FilterData data) {
        this.data = data;
    }
}
