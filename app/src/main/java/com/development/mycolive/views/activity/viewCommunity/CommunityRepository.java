package com.development.mycolive.views.activity.viewCommunity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.alert.AlertRequest;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentResponse;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityRepository {

    private static CommunityRepository communityRepository = null;
    private ShipmentApi shipmentApi;

    public CommunityRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static CommunityRepository getInstance(){
        if(communityRepository == null)
            communityRepository =new CommunityRepository();
        return communityRepository;
    }

    public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context, Map<String,String> headers, String id){
        final MutableLiveData<ViewCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommunityResponse(headers,id).enqueue(new Callback<ViewCommunityResponse>() {
            @Override
            public void onResponse(Call<ViewCommunityResponse> call, Response<ViewCommunityResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new ViewCommunityApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new ViewCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }


    public MutableLiveData<CommentApiResponse> getCommentResponse(Context context, Map<String,String> headers , CommentPost commentPost){
        final MutableLiveData<CommentApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommentReply(headers,commentPost).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new CommentApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new CommentApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new CommentApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }


    public MutableLiveData<CommentApiResponse> getLikeUnlike(Context context,Map<String,String> headers, LikeUnlike likeUnlike){
        final MutableLiveData<CommentApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getLikeUnlike(headers,likeUnlike).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new CommentApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new CommentApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new CommentApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }

    public MutableLiveData<ViewCommunityApiResponse> sendComplain(Context context, Map<String,String> headers, AlertRequest alertRequest){
        final MutableLiveData<ViewCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.sendComplain(headers,alertRequest).enqueue(new Callback<ViewCommunityResponse>() {
            @Override
            public void onResponse(Call<ViewCommunityResponse> call, Response<ViewCommunityResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new ViewCommunityApiResponse(message,status,response.code()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewCommunityResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new ViewCommunityApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }

}
