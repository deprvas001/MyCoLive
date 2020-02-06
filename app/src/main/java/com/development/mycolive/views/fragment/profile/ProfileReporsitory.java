package com.development.mycolive.views.fragment.profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;
import com.development.mycolive.views.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.views.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.views.model.editProfile.ProfileApiResponse;
import com.development.mycolive.views.model.editProfile.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileReporsitory {

    private static ProfileReporsitory profileRepository = null;
    private ShipmentApi shipmentApi;

    public ProfileReporsitory(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ProfileReporsitory getInstance(){
        if(profileRepository == null)
            profileRepository =new ProfileReporsitory();
        return profileRepository;
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, String type){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getProfile(type).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401){
                    profileResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    profileResponseLiveData.setValue(new ProfileApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
