package com.development.mycolive.views.activity.favouriteRoomate;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.model.favouriteRoomate.RoomateFavResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRoomateRepository {
    private static FavouriteRoomateRepository roomateRepository = null;
    private ShipmentApi shipmentApi;

    public FavouriteRoomateRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static FavouriteRoomateRepository getInstance(){
        if(roomateRepository == null)
            roomateRepository =new FavouriteRoomateRepository();
        return roomateRepository;
    }

    public MutableLiveData<RoomateFavApiResponse> getRoomateDetail(Context context, Map<String,String> headers, String type, String orderId){
        final MutableLiveData<RoomateFavApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getRoomateDetail(headers,type,orderId).enqueue(new Callback<RoomateFavResponse>() {
            @Override
            public void onResponse(Call<RoomateFavResponse> call, Response<RoomateFavResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    historyResponseLiveData.setValue(new RoomateFavApiResponse(response.code()));
                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new RoomateFavApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomateFavResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new RoomateFavApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
