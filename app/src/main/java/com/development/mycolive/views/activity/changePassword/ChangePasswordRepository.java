package com.development.mycolive.views.activity.changePassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.logout.LogoutApiResponse;
import com.development.mycolive.model.logout.LogoutRequestModel;
import com.development.mycolive.model.logout.LogoutResponse;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.testimonial.TestimonialRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        historyResponseLiveData.setValue(new PostApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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



    public MutableLiveData<LogoutApiResponse> logout(Context context, Map<String,String> headers, LogoutRequestModel requestModel){
        final MutableLiveData<LogoutApiResponse> responseLiveData =new MutableLiveData<>();

        shipmentApi.logout(headers,requestModel).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        responseLiveData.setValue(new LogoutApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else {

                    if(response.isSuccessful()){
                        responseLiveData.setValue(new LogoutApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse>call, Throwable t) {
                responseLiveData.setValue(new LogoutApiResponse(t));
            }
        });

        return  responseLiveData;
    }
}
