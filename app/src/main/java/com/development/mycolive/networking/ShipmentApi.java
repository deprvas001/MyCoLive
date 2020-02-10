package com.development.mycolive.networking;

import com.development.mycolive.views.model.booking.BookingResponse;
import com.development.mycolive.views.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.views.model.editProfile.ProfileApiResponse;
import com.development.mycolive.views.model.editProfile.ProfileResponse;
import com.development.mycolive.views.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.views.model.home.HomeResponse;
import com.development.mycolive.views.model.loginModel.LoginRequestModel;
import com.development.mycolive.views.model.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: GET",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgwMTkwMzg3LCJuYmYiOjE1ODAxOTAzODcsImV4cCI6MTU4MTM5OTk4NywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEUklWRVIiLCJ1c2VyX2RldmljZV90eXBlIjoiQUROUk9JRCIsInVzZXJfZGV2aWNlX3Rva2VuIjoiMjM0MjM0ZHZkZmRmc2Rmc2RmIiwiU291cmNlcyI6IkFQUCIsInVzZXJfbmFtZSI6IlJhbmRoaXIga3VtYXIiLCJ1c2VyX2lkIjoiMzAiLCJsb2dpbl90eXBlIjoiRkFDRUJPT0siLCJ1c2VyX2xvZ19pZCI6MTh9fQ.HwyaAVzQEFWs3Fd8QSsWQtnfJQbraUtRZxaD4WnC5-8"
    })
    @GET("bookingList")
    Call<BookingResponse> getBooking(
            @Query("type") String  type);


    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: GET",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgwMTkwMzg3LCJuYmYiOjE1ODAxOTAzODcsImV4cCI6MTU4MTM5OTk4NywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEUklWRVIiLCJ1c2VyX2RldmljZV90eXBlIjoiQUROUk9JRCIsInVzZXJfZGV2aWNlX3Rva2VuIjoiMjM0MjM0ZHZkZmRmc2Rmc2RmIiwiU291cmNlcyI6IkFQUCIsInVzZXJfbmFtZSI6IlJhbmRoaXIga3VtYXIiLCJ1c2VyX2lkIjoiMzAiLCJsb2dpbl90eXBlIjoiRkFDRUJPT0siLCJ1c2VyX2xvZ19pZCI6MTh9fQ.HwyaAVzQEFWs3Fd8QSsWQtnfJQbraUtRZxaD4WnC5-8"
    })
    @GET("bookingList")
    Call<BookingHistoryResponse> getHistory(
            @Query("type") String  type,
            @Query("order_id") String orderid);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: GET",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgwMTkwMzg3LCJuYmYiOjE1ODAxOTAzODcsImV4cCI6MTU4MTM5OTk4NywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEUklWRVIiLCJ1c2VyX2RldmljZV90eXBlIjoiQUROUk9JRCIsInVzZXJfZGV2aWNlX3Rva2VuIjoiMjM0MjM0ZHZkZmRmc2Rmc2RmIiwiU291cmNlcyI6IkFQUCIsInVzZXJfbmFtZSI6IlJhbmRoaXIga3VtYXIiLCJ1c2VyX2lkIjoiMzAiLCJsb2dpbl90eXBlIjoiRkFDRUJPT0siLCJ1c2VyX2xvZ19pZCI6MTh9fQ.HwyaAVzQEFWs3Fd8QSsWQtnfJQbraUtRZxaD4WnC5-8"
    })
    @GET("getProfile")
    Call<ProfileResponse> getProfile(
            @Query("type") String  type);

    @GET("homePage")
    Call<HomeResponse> getData();
}
