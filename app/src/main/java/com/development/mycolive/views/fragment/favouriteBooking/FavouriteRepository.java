package com.development.mycolive.views.fragment.favouriteBooking;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouriteResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.fragment.profile.ProfileReporsitory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRepository {

    private static FavouriteRepository favouriteRepository = null;
    private ShipmentApi shipmentApi;

    public FavouriteRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static FavouriteRepository getInstance(){
        if(favouriteRepository == null)
            favouriteRepository =new FavouriteRepository();
        return favouriteRepository;
    }

    public MutableLiveData<FavouriteApiResponse> getFavourite(Context context, String type,String offset,String perPage){
        final MutableLiveData<FavouriteApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getFavourite(type,offset,perPage).enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                if(response.code() == 401 || response.code() == 400){
                    profileResponseLiveData.setValue(new FavouriteApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new FavouriteApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new FavouriteApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
