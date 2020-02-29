package com.development.mycolive.views.fragment.signUp;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.PostProfileResponse;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.model.signup.SignUpResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.fragment.profile.ProfileReporsitory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignRepository {
    private static SignRepository signRepository = null;
    private ShipmentApi shipmentApi;

    public SignRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static SignRepository getInstance(){
        if(signRepository == null)
            signRepository =new SignRepository();
        return signRepository;
    }

    public MutableLiveData<SignUpApiResponse> setSignUp(Context context, SignPostRequest signPostRequest){
        final MutableLiveData<SignUpApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.setSignUp(signPostRequest).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.code() == 401 || response.code() == 400){
                    responseLiveData.setValue(new SignUpApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        responseLiveData.setValue(new SignUpApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                responseLiveData.setValue(new SignUpApiResponse(t));
            }
        });

        return   responseLiveData;
    }

}
