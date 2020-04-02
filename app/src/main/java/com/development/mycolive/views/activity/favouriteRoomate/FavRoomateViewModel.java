package com.development.mycolive.views.activity.favouriteRoomate;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class FavRoomateViewModel extends AndroidViewModel {


    public FavRoomateViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<RoomateFavApiResponse> getRoomateDetail(Context context, Map<String,String> headers, String type , String orderId) {
        return FavouriteRoomateRepository.getInstance().getRoomateDetail(context,headers, type,orderId);
    }

    public MutableLiveData<RoomateFavApiResponse> setFav(Context context, Map<String,String> headers, FavouriteRequest request) {
        return FavouriteRoomateRepository.getInstance().setFav(context,headers, request);
    }
}
