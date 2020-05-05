package com.development.mycolive.views.activity.searchResult;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.communityModel.SearchCommunityApiResponse;
import com.development.mycolive.model.homeProperty.FeatureApiResponse;
import com.development.mycolive.model.homeProperty.FeatureResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.home.HomeResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

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

    public MutableLiveData<FeatureApiResponse> getData(Context context, Map<String,String> params, String offset, String per_page){
        final MutableLiveData<FeatureApiResponse> homeResponseLiveData =new MutableLiveData<>();

        shipmentApi.getRoomList(params,offset,per_page).enqueue(new Callback<FeatureResponse>() {
            @Override
            public void onResponse(Call<FeatureResponse> call, Response<FeatureResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        homeResponseLiveData.setValue(new FeatureApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        homeResponseLiveData.setValue(new FeatureApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FeatureResponse> call, Throwable t) {
                homeResponseLiveData.setValue(new FeatureApiResponse(t));
            }
        });

        return   homeResponseLiveData;
    }
}
