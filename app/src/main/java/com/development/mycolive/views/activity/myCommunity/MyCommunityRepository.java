package com.development.mycolive.views.activity.myCommunity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityResponse;
import com.development.mycolive.model.myCommunityModel.PostDeleteRequest;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.fragment.communities.CommunitiesRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCommunityRepository {

    private static MyCommunityRepository communityRepository = null;
    private ShipmentApi shipmentApi;

    public MyCommunityRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static MyCommunityRepository getInstance(){
        if(communityRepository == null)
            communityRepository =new MyCommunityRepository();
        return communityRepository;
    }

    public MutableLiveData<MyCommunityApiResponse> getCommunityData(Context context, Map<String,String> headers, String type){
        final MutableLiveData<MyCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getMyCommunityData(headers,type).enqueue(new Callback<MyCommunityResponse>() {
            @Override
            public void onResponse(Call<MyCommunityResponse> call, Response<MyCommunityResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new MyCommunityApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new MyCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<MyCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new MyCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }

    public MutableLiveData<MyCommunityApiResponse> deletePost(Context context, Map<String,String> headers, PostDeleteRequest request){
        final MutableLiveData<MyCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.deletePost(headers,request).enqueue(new Callback<MyCommunityResponse>() {
            @Override
            public void onResponse(Call<MyCommunityResponse> call, Response<MyCommunityResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new MyCommunityApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new MyCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<MyCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new MyCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
