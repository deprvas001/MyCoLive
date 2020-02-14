package com.development.mycolive.views.activity.searchDetailPage;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.searchDetailPage.SearchDetailApiRsponse;
import com.development.mycolive.model.searchDetailPage.SearchDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDetailRepository {
    private static RoomDetailRepository roomDetailRepository = null;
    private ShipmentApi shipmentApi;

    public RoomDetailRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static RoomDetailRepository getInstance(){
        if(roomDetailRepository == null)
            roomDetailRepository =new RoomDetailRepository();
        return roomDetailRepository;
    }

    public MutableLiveData<SearchDetailApiRsponse> getData(Context context, String id){
        final MutableLiveData<SearchDetailApiRsponse> apiResponseLiveData =new MutableLiveData<>();

        shipmentApi.getPropertyById(id).enqueue(new Callback<SearchDetailResponse>() {
            @Override
            public void onResponse(Call<SearchDetailResponse> call, Response<SearchDetailResponse> response) {
                if(response.code() == 401){
                    apiResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    apiResponseLiveData.setValue(new SearchDetailApiRsponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        apiResponseLiveData.setValue(new SearchDetailApiRsponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchDetailResponse> call, Throwable t) {
                apiResponseLiveData.setValue(new SearchDetailApiRsponse(t));
            }
        });

        return   apiResponseLiveData;
    }
}
