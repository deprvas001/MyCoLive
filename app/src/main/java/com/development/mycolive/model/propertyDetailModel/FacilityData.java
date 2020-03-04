package com.development.mycolive.model.propertyDetailModel;

import android.os.Parcel;
import android.os.Parcelable;

public class FacilityData implements Parcelable {
    private String facility;
    private String icon_url;

    protected FacilityData(Parcel in) {
        facility = in.readString();
        icon_url = in.readString();
    }

    public static final Creator<FacilityData> CREATOR = new Creator<FacilityData>() {
        @Override
        public FacilityData createFromParcel(Parcel in) {
            return new FacilityData(in);
        }

        @Override
        public FacilityData[] newArray(int size) {
            return new FacilityData[size];
        }
    };

    public String getFacilityString() {
        return facility;
    }

    public void setFacilityString(String facility) {
        this.facility = facility;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(facility);
        parcel.writeString(icon_url);
    }
}
