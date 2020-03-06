package com.development.mycolive.views.activity.myCommunity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.fragment.communities.CommunitiesRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCommunityRepository {

    private static MyCommunityRepository communityRepository = null;
    private ShipmentApi shipmentApi;

    public MyCommunityRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static MyCommunityRepository getInstance(){
        if(communityRepository == null)
            communityRepository =new MyCommunityRepository();
        return communityRepository;
    }

    public MutableLiveData<MyCommunityApiResponse> getCommunityData(Context context, Map<String,String> headers, String type){
        final MutableLiveData<MyCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getMyCommunityData(headers,type).enqueue(new Callback<MyCommunityResponse>() {
            @Override
            public void onResponse(Call<MyCommunityResponse> call, Response<MyCommunityResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new MyCommunityApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new MyCommunityApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new MyCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<MyCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new MyCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
