package com.development.mycolive.views.activity.postScreen;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private static PostRepository postRepository = null;
    private ShipmentApi shipmentApi;

    public PostRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static PostRepository getInstance(){
        if(postRepository == null)
            postRepository =new PostRepository();
        return postRepository;
    }

    public MutableLiveData<PostApiResponse> getResponse(Context context, Map<String,String> headers, PostCommunity postCommunity){
        final MutableLiveData<PostApiResponse> loginResponseLiveData =new MutableLiveData<>();

        shipmentApi.getPostResponse(headers,postCommunity).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(new PostApiResponse(response.code()));
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new PostApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new PostApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new PostApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
    
}
