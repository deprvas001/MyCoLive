package com.development.mycolive.model.filterModel;

import com.development.mycolive.model.editProfile.ProfileData;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.home.HomeFeatureProperty;

import java.util.ArrayList;
import java.util.List;

public class FilterResponseData {
  private ArrayList<RoomateData> roommate;



    public ArrayList<RoomateData> getRoommate() {
        return roommate;
    }

    public void setRoommate(ArrayList<RoomateData> roommate) {
        this.roommate = roommate;
    }
}
