package com.development.mycolive.networking;

import com.development.mycolive.views.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.views.model.loginModel.LoginRequestModel;
import com.development.mycolive.views.model.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShipmentApi {
    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: POST"
    })
    @POST("userLogin")
    Call<LoginResponse> loginUser(@Body LoginRequestModel requestModel);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: POST"
    })
    @POST("forgetPassword")
    Call<LoginResponse> forgotPassword(@Body ForgotRequestModel requestModel);

}
