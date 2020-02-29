package com.development.mycolive.views.activity.viewCommunity;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentResponse;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

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

    public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context,  String id){
        final MutableLiveData<ViewCommunityApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommunityResponse(id).enqueue(new Callback<ViewCommunityResponse>() {
            @Override
            public void onResponse(Call<ViewCommunityResponse> call, Response<ViewCommunityResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new ViewCommunityApiResponse(response.code()));
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


    public MutableLiveData<CommentApiResponse> getCommentResponse(Context context, CommentPost commentPost){
        final MutableLiveData<CommentApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getCommentReply(commentPost).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new CommentApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new CommentApiResponse(response.code()));
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


    public MutableLiveData<CommentApiResponse> getLikeUnlike(Context context, LikeUnlike likeUnlike){
        final MutableLiveData<CommentApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getLikeUnlike(likeUnlike).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new CommentApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new CommentApiResponse(response.code()));
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
}
