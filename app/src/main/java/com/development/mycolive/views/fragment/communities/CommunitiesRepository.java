package com.development.mycolive.views.fragment.communities;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunitiesRepository {

    private static CommunitiesRepository communityRepository = null;
    private ShipmentApi shipmentApi;

    public CommunitiesRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static CommunitiesRepository getInstance(){
        if(communityRepository == null)
            communityRepository =new CommunitiesRepository();
        return communityRepository;
    }

    public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context, String type){
        final MutableLiveData<ViewCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommunityData(type).enqueue(new Callback<ViewCommunityResponse>() {
            @Override
            public void onResponse(Call<ViewCommunityResponse> call, Response<ViewCommunityResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new ViewCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
