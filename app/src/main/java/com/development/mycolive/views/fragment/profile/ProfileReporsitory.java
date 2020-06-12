package com.development.mycolive.views.fragment.profile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.FacebookLinked;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.PostProfileResponse;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileReporsitory {

    private static ProfileReporsitory profileRepository = null;
    private ShipmentApi shipmentApi;

    public ProfileReporsitory(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ProfileReporsitory getInstance(){
        if(profileRepository == null)
            profileRepository =new ProfileReporsitory();
        return profileRepository;
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers ,String type){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.getProfile(headers,type).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new ProfileApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }


    public MutableLiveData<ProfileApiResponse> updateProfile(Context context,  Map<String,String> headers,PostProfileModel postProfileModel){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.updateProfile(headers,postProfileModel).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new ProfileApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }


    public MutableLiveData<ProfileApiResponse> linkedFacebook(Context context, Map<String,String> headers, FacebookLinked postProfileModel){
        final MutableLiveData<ProfileApiResponse> profileResponseLiveData =new MutableLiveData<>();

        shipmentApi.linkedFacebook(headers,postProfileModel).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        profileResponseLiveData.setValue(new ProfileApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        profileResponseLiveData.setValue(new ProfileApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(new ProfileApiResponse(t));
            }
        });

        return   profileResponseLiveData;
    }
}
