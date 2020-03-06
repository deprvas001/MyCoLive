package com.development.mycolive.model.home;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HomeData {
    @SerializedName("slider")
    List<HomeSlider> sliderList;
    @SerializedName("featuredProperty")
    List<HomeFeatureProperty> featuredPropertyList;

    @SerializedName("hotProperty")
    List<HomeFeatureProperty> hotProperty;

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

    public List<HomeFeatureProperty> getHotProperty() {
        return hotProperty;
    }

    public void setHotProperty(List<HomeFeatureProperty> hotProperty) {
        this.hotProperty = hotProperty;
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

    public List<HomeSlider> getSliderList() {
        return sliderList;
    }

    public void setSliderList(List<HomeSlider> sliderList) {
        this.sliderList = sliderList;
    }
}
