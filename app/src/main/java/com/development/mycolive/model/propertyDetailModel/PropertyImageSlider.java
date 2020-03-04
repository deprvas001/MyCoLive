package com.development.mycolive.model.propertyDetailModel;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyImageSlider implements Parcelable {
    private String image;

    protected PropertyImageSlider(Parcel in) {
        image = in.readString();
    }

    public static final Creator<PropertyImageSlider> CREATOR = new Creator<PropertyImageSlider>() {
        @Override
        public PropertyImageSlider createFromParcel(Parcel in) {
            return new PropertyImageSlider(in);
        }

        @Override
        public PropertyImageSlider[] newArray(int size) {
            return new PropertyImageSlider[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
    }
}
