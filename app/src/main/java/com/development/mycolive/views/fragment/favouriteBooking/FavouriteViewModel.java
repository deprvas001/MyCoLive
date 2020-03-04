package com.development.mycolive.views.fragment.favouriteBooking;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouriteRoomateApiResponse;
import com.development.mycolive.views.fragment.profile.ProfileReporsitory;

public class FavouriteViewModel extends AndroidViewModel {

    public FavouriteViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<FavouriteApiResponse> getFavourite(Context context, String type,String offset,String perPage) {
        return FavouriteRepository.getInstance().getFavourite(context, type,offset,perPage);
    }


    public MutableLiveData<FavouriteRoomateApiResponse> getFavouriteRoomate(Context context, String type) {
        return FavouriteRepository.getInstance().getFavouriteRoomate(context, type);
    }
}
