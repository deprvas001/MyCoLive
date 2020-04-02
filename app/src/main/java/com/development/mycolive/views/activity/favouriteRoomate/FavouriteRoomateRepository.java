package com.development.mycolive.views.activity.favouriteRoomate;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.model.favouriteRoomate.RoomateFavResponse;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRoomateRepository {
    private static FavouriteRoomateRepository roomateRepository = null;
    private ShipmentApi shipmentApi;

    public FavouriteRoomateRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static FavouriteRoomateRepository getInstance(){
        if(roomateRepository == null)
            roomateRepository =new FavouriteRoomateRepository();
        return roomateRepository;
    }

    public MutableLiveData<RoomateFavApiResponse> getRoomateDetail(Context context, Map<String,String> headers, String type, String orderId){
        final MutableLiveData<RoomateFavApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getRoomateDetail(headers,type,orderId).enqueue(new Callback<RoomateFavResponse>() {
            @Override
            public void onResponse(Call<RoomateFavResponse> call, Response<RoomateFavResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        historyResponseLiveData.setValue(new RoomateFavApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new RoomateFavApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomateFavResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new RoomateFavApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }

    public MutableLiveData<RoomateFavApiResponse> setFav(Context context, Map<String,String> headers, FavouriteRequest request){
        final MutableLiveData<RoomateFavApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.setFav(headers,request).enqueue(new Callback<RoomateFavResponse>() {
            @Override
            public void onResponse(Call<RoomateFavResponse> call, Response<RoomateFavResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        historyResponseLiveData.setValue(new RoomateFavApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new RoomateFavApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomateFavResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new RoomateFavApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
