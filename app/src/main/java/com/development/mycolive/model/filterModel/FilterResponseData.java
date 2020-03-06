package com.development.mycolive.model.filterModel;

import com.development.mycolive.model.home.HomeFeatureProperty;

import java.util.List;

public class FilterResponseData {
    private RequestData request_data;
    private List<HomeFeatureProperty>  result;

    public RequestData getRequest_data() {
        return request_data;
    }

    public void setRequest_data(RequestData request_data) {
        this.request_data = request_data;
    }

    public List<HomeFeatureProperty> getResult() {
        return result;
    }

    public void setResult(List<HomeFeatureProperty> result) {
        this.result = result;
    }
}
