package com.development.mycolive.views.fragment.communities;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.communityModel.SearchCommunityApiResponse;
import com.development.mycolive.model.communityModel.SearchCommunityResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import java.util.Map;

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

    public MutableLiveData<CommunityApiResponse> getCommunityData(Context context, Map<String,String> headers,String type){
        final MutableLiveData<CommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommunityData(headers,type).enqueue(new Callback<CommunityResponse>() {
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
    }

    public MutableLiveData<SearchCommunityApiResponse> getSearchData(Context context, String type){
        final MutableLiveData<SearchCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getSearchData(type).enqueue(new Callback<SearchCommunityResponse>() {
            @Override
            public void onResponse(Call<SearchCommunityResponse> call, Response<SearchCommunityResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new SearchCommunityApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new SearchCommunityApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new SearchCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new SearchCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
