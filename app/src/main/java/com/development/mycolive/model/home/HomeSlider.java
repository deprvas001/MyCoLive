package com.development.mycolive.model.home;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeSlider implements Parcelable {
    private String id;
    private String image;

    protected HomeSlider(Parcel in) {
        id = in.readString();
        image = in.readString();
    }

    public static final Creator<HomeSlider> CREATOR = new Creator<HomeSlider>() {
        @Override
        public HomeSlider createFromParcel(Parcel in) {
            return new HomeSlider(in);
        }

        @Override
        public HomeSlider[] newArray(int size) {
            return new HomeSlider[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        parcel.writeString(id);
        parcel.writeString(image);
    }
}
