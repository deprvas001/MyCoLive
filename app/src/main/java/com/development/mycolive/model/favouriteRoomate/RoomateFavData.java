package com.development.mycolive.model.favouriteRoomate;

import com.development.mycolive.model.favourite.FavouritePropertyModel;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.home.HomeFeatureProperty;

import java.util.List;

public class RoomateFavData {

    private List<HomeFeatureProperty> nearByArea;

    private List<RoomateData> data;

    public List<HomeFeatureProperty> getNearByArea() {
        return nearByArea;
    }

    public void setNearByArea(List<HomeFeatureProperty> nearByArea) {
        this.nearByArea = nearByArea;
    }

    public List<RoomateData> getData() {
        return data;
    }

    public void setData(List<RoomateData> data) {
        this.data = data;
    }
}
