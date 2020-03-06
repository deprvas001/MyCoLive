package com.development.mycolive.model.searchFilterModel;

import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.DistrictModel;
import com.development.mycolive.model.searchScreen.Period;
import com.development.mycolive.model.searchScreen.UniversityModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterData {
    @SerializedName("cities")
    private List<CityModel> cityList;

    @SerializedName("districts")
    private List<DistrictModel> distictList;

    @SerializedName("university")
    private List<UniversityModel> universityList;

    @SerializedName("period")
    private List<Period> periodList;


    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    public List<DistrictModel> getDistictList() {
        return distictList;
    }

    public void setDistictList(List<DistrictModel> distictList) {
        this.distictList = distictList;
    }

    public List<UniversityModel> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<UniversityModel> universityList) {
        this.universityList = universityList;
    }

    public List<Period> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }
}
