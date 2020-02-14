package com.development.mycolive.views.activity.forgotPassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotRepository {

    private static ForgotRepository forgotRepository = null;
    private ShipmentApi shipmentApi;

    public ForgotRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static ForgotRepository getInstance(){
        if(forgotRepository == null)
            forgotRepository =new ForgotRepository();
        return forgotRepository;
    }

    public MutableLiveData<LoginApiResponse> forgotPassword(Context context,ForgotRequestModel requestModel){
        final MutableLiveData<LoginApiResponse> loginResponseLiveData =new MutableLiveData<>();

        shipmentApi.forgotPassword(requestModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new LoginApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new LoginApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new LoginApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
