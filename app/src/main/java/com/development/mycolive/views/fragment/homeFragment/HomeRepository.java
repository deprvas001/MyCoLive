package com.development.mycolive.views.fragment.homeFragment;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.home.RoomateApiResponse;
import com.development.mycolive.model.home.RoommateResponse;
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

public class HomeRepository {
    private static HomeRepository homeRepository = null;
    private ShipmentApi shipmentApi;

    public HomeRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static HomeRepository getInstance(){
        if(homeRepository == null)
            homeRepository =new HomeRepository();
        return homeRepository;
    }

    public MutableLiveData<HomeApiResponse> getData(Context context, String type){
        final MutableLiveData<HomeApiResponse> homeResponseLiveData =new MutableLiveData<>();

        shipmentApi.getData().enqueue(new Callback<HomeResponse>() {
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

    public MutableLiveData<RoomateApiResponse> getRoommateData(Context context, Map<String,String> headers, String type){
        final MutableLiveData<RoomateApiResponse> homeResponseLiveData =new MutableLiveData<>();

        shipmentApi.getRoomateList(headers,type).enqueue(new Callback<RoommateResponse>() {
            @Override
            public void onResponse(Call<RoommateResponse > call, Response<RoommateResponse > response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        homeResponseLiveData.setValue(new RoomateApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        homeResponseLiveData.setValue(new RoomateApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoommateResponse > call, Throwable t) {
                homeResponseLiveData.setValue(new RoomateApiResponse(t));
            }
        });

        return   homeResponseLiveData;
    }

}
