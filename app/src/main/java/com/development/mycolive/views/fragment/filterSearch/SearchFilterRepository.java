package com.development.mycolive.views.fragment.filterSearch;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.filterModel.FilterSearchApiResponse;
import com.development.mycolive.model.filterModel.FilterSearchResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchFilterModel.FilterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilterRepository {

    private static SearchFilterRepository searchFilterRepository = null;
    private ShipmentApi shipmentApi;

    public SearchFilterRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static SearchFilterRepository getInstance(){
        if(searchFilterRepository == null)
            searchFilterRepository =new SearchFilterRepository();
        return searchFilterRepository;
    }

    public MutableLiveData<FilterApiResponse> getDefaultData(Context context, String type){
        final MutableLiveData<FilterApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getDefaultData(type).enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new FilterApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new FilterApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new FilterApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new FilterApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }

    public MutableLiveData<FilterSearchApiResponse> getSearchData(Context context, Map<String,String> headers, String type, String city,
                                                                  String district, String university, String duration, String range){
        final MutableLiveData<FilterSearchApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getFilterData(headers, type,city,district,university,duration,range).enqueue(new Callback<FilterSearchResponse>() {
            @Override
            public void onResponse(Call<FilterSearchResponse> call, Response<FilterSearchResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new FilterSearchApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new FilterSearchApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new FilterSearchApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FilterSearchResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new FilterSearchApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
