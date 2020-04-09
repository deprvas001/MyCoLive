package com.development.mycolive.views.fragment.favouriteBooking;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouriteResponse;
import com.development.mycolive.model.favourite.FavouriteRoomateApiResponse;
import com.development.mycolive.model.favourite.FavouriteRoomateResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.fragment.profile.ProfileReporsitory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteRepository {

    private static FavouriteRepository favouriteRepository = null;
    private ShipmentApi shipmentApi;

    public FavouriteRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static FavouriteRepository getInstance(){
        if(favouriteRepository == null)
            favouriteRepository =new FavouriteRepository();
        return favouriteRepository;
    }

    public MutableLiveData<FavouriteApiResponse> getFavourite(Context context, Map<String,String> headers, String type, String offset, String perPage){
        final MutableLiveData<FavouriteApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getFavourite(headers,type,offset,perPage).enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new FavouriteApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new FavouriteApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new FavouriteApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }


    public MutableLiveData<FavouriteRoomateApiResponse> getFavouriteRoomate(Context context,Map<String,String> headers, String type){
        final MutableLiveData<FavouriteRoomateApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getFavouriteRoomate(headers,type).enqueue(new Callback<FavouriteRoomateResponse>() {
            @Override
            public void onResponse(Call<FavouriteRoomateResponse> call, Response<FavouriteRoomateResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new FavouriteRoomateApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new FavouriteRoomateApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouriteRoomateResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new FavouriteRoomateApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
