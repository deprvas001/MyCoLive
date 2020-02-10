package com.development.mycolive.views.model.home;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HomeData {

    @SerializedName("featuredProperty")
    List<HomeFeatureProperty> featuredPropertyList;

    @SerializedName("hotProperty")
    List<HomeHotProperty> hotPropertyList;

    @SerializedName("propertyByArea")
    List<HomePropertyArea> propertyAreaList;

    @SerializedName("count")
    private CountData  countData;

    public List<HomeFeatureProperty> getFeaturedPropertyList() {
        return featuredPropertyList;
    }

    public void setFeaturedPropertyList(List<HomeFeatureProperty> featuredPropertyList) {
        this.featuredPropertyList = featuredPropertyList;
    }

    public List<HomeHotProperty> getHotPropertyList() {
        return hotPropertyList;
    }

    public void setHotPropertyList(List<HomeHotProperty> hotPropertyList) {
        this.hotPropertyList = hotPropertyList;
    }

    public List<HomePropertyArea> getPropertyAreaList() {
        return propertyAreaList;
    }

    public void setPropertyAreaList(List<HomePropertyArea> propertyAreaList) {
        this.propertyAreaList = propertyAreaList;
    }

    public CountData getCountData() {
        return countData;
    }

    public void setCountData(CountData countData) {
        this.countData = countData;
    }
}
