package com.development.mycolive.views.activity.propertyDetail;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.postScreen.PostRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailRepository {

    private static PropertyDetailRepository propertyDetailRepository = null;
    private ShipmentApi shipmentApi;

    public PropertyDetailRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static PropertyDetailRepository getInstance(){
        if(propertyDetailRepository == null)
            propertyDetailRepository =new PropertyDetailRepository();
        return propertyDetailRepository;
    }

    public MutableLiveData<PropertyDetailApiResponse> getPropertyDetail(Context context, String id){
        final MutableLiveData<PropertyDetailApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getPropertyDetail(id).enqueue(new Callback<PropertyDetailResponse>() {
            @Override
            public void onResponse(Call<PropertyDetailResponse> call, Response<PropertyDetailResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new PropertyDetailApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new PropertyDetailApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new PropertyDetailApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PropertyDetailResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new PropertyDetailApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
