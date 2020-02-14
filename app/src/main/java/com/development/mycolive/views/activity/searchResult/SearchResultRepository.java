package com.development.mycolive.views.activity.searchResult;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.home.HomeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultRepository {

    private static SearchResultRepository  searchResultRepository = null;
    private ShipmentApi shipmentApi;

    public SearchResultRepository (){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static SearchResultRepository getInstance(){
        if(searchResultRepository == null)
            searchResultRepository =new SearchResultRepository();
        return searchResultRepository;
    }

    public MutableLiveData<HomeApiResponse> getData(Context context, String type,String offset,String per_page){
        final MutableLiveData<HomeApiResponse> homeResponseLiveData =new MutableLiveData<>();

        shipmentApi.getRoomList(type,offset,per_page).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if(response.code() == 401){
                    homeResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    homeResponseLiveData.setValue(new HomeApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        homeResponseLiveData.setValue(new HomeApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                homeResponseLiveData.setValue(new HomeApiResponse(t));
            }
        });

        return   homeResponseLiveData;
    }
}
