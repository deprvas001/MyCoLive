package com.development.mycolive.views.fragment.profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.PostProfileResponse;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;

import java.util.Map;

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

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers ,String type){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getProfile(headers,type).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400){
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


    public MutableLiveData<ProfilePostApiResponse> updateProfile(Context context,  Map<String,String> headers,PostProfileModel postProfileModel){
        final MutableLiveData<ProfilePostApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.updateProfile(headers,postProfileModel).enqueue(new Callback<PostProfileResponse>() {
            @Override
            public void onResponse(Call<PostProfileResponse> call, Response<PostProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400){
                    profileResponseLiveData.setValue(new ProfilePostApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfilePostApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PostProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfilePostApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
