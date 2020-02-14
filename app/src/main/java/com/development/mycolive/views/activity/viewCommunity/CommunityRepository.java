package com.development.mycolive.views.activity.viewCommunity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityRepository {

    private static CommunityRepository communityRepository = null;
    private ShipmentApi shipmentApi;

    public CommunityRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static CommunityRepository getInstance(){
        if(communityRepository == null)
            communityRepository =new CommunityRepository();
        return communityRepository;
    }

   /* public MutableLiveData<CommunityApiResponse> getCommunityData(Context context, String type,String id){
        final MutableLiveData<CommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommunityData(type).enqueue(new Callback<CommunityResponse>() {
            @Override
            public void onResponse(Call<CommunityResponse> call, Response<CommunityResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new CommunityApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new CommunityApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new CommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<CommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new CommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }*/
}
