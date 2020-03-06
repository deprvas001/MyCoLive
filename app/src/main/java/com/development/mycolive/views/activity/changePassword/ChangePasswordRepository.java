package com.development.mycolive.views.activity.changePassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.testimonial.TestimonialRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordRepository {

    private static ChangePasswordRepository historyRepository = null;
    private ShipmentApi shipmentApi;

    public ChangePasswordRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ChangePasswordRepository getInstance(){
        if(historyRepository == null)
            historyRepository =new ChangePasswordRepository();
        return historyRepository;
    }

    public MutableLiveData<PostApiResponse> changePassword(Context context, Map<String,String> headers, ChangePasswordModel passwordModel){
        final MutableLiveData<PostApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.changePassword(headers,passwordModel).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    historyResponseLiveData.setValue(new PostApiResponse(response.code()));
                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new PostApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new PostApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
