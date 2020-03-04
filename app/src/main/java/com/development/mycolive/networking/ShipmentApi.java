package com.development.mycolive.networking;

import com.development.mycolive.model.booking.BookingResponse;
import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.communityModel.SearchCommunityResponse;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.PostProfileResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.favourite.FavouriteResponse;
import com.development.mycolive.model.favourite.FavouriteRoomateResponse;
import com.development.mycolive.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.model.home.HomeResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;
import com.development.mycolive.model.loginModel.LoginResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.model.searchDetailPage.SearchDetailResponse;
import com.development.mycolive.model.searchFilterModel.FilterResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentResponse;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyNTI2NzUxLCJuYmYiOjE1ODI1MjY3NTEsImV4cCI6MTU4MzczNjM1MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTQ5IiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoxNDN9fQ.zpuj83OlnQQtMCSUDMD7r57E6ZLIguIZA8Oyuxf-a7s"
    })
    @GET("bookingList")
    Call<BookingResponse> getBooking(
            @Query("type") String  type);


    @GET("getAllByDefaultData")
    Call<FilterResponse> getDefaultData(
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
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyNTI2NzUxLCJuYmYiOjE1ODI1MjY3NTEsImV4cCI6MTU4MzczNjM1MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTQ5IiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoxNDN9fQ.zpuj83OlnQQtMCSUDMD7r57E6ZLIguIZA8Oyuxf-a7s"
    })
    @GET("getProfile")
    Call<ProfileResponse> getProfile(
            @Query("type") String  type);

    @GET("homePage")
    Call<HomeResponse> getData();


    @GET("getProperty")
    Call<HomeResponse> getRoomList( @Query("type") String  type,
                                    @Query("offset") String  offset,
                                    @Query("per_page") String  per_page);

    @GET("getProperty")
    Call<SearchDetailResponse> getPropertyById(@Query("property_id") String  id);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: GET",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgwMTkwMzg3LCJuYmYiOjE1ODAxOTAzODcsImV4cCI6MTU4MTM5OTk4NywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEUklWRVIiLCJ1c2VyX2RldmljZV90eXBlIjoiQUROUk9JRCIsInVzZXJfZGV2aWNlX3Rva2VuIjoiMjM0MjM0ZHZkZmRmc2Rmc2RmIiwiU291cmNlcyI6IkFQUCIsInVzZXJfbmFtZSI6IlJhbmRoaXIga3VtYXIiLCJ1c2VyX2lkIjoiMzAiLCJsb2dpbl90eXBlIjoiRkFDRUJPT0siLCJ1c2VyX2xvZ19pZCI6MTh9fQ.HwyaAVzQEFWs3Fd8QSsWQtnfJQbraUtRZxaD4WnC5-8"
    })
    @GET("viewCommunity")
    Call<CommunityResponse> getCommunityData(
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
    @GET("searchCommunity")
    Call<SearchCommunityResponse> getSearchData(
            @Query("text") String  type);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: GET",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgwMTkwMzg3LCJuYmYiOjE1ODAxOTAzODcsImV4cCI6MTU4MTM5OTk4NywiZGF0YSI6eyJ1c2VyX3R5cGUiOiJEUklWRVIiLCJ1c2VyX2RldmljZV90eXBlIjoiQUROUk9JRCIsInVzZXJfZGV2aWNlX3Rva2VuIjoiMjM0MjM0ZHZkZmRmc2Rmc2RmIiwiU291cmNlcyI6IkFQUCIsInVzZXJfbmFtZSI6IlJhbmRoaXIga3VtYXIiLCJ1c2VyX2lkIjoiMzAiLCJsb2dpbl90eXBlIjoiRkFDRUJPT0siLCJ1c2VyX2xvZ19pZCI6MTh9fQ.HwyaAVzQEFWs3Fd8QSsWQtnfJQbraUtRZxaD4WnC5-8"
    })
    @GET("viewCommunity")
    Call<ViewCommunityResponse> getCommunityResponse(
            @Query("comment_id") String id );


    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyMDE2ODQ2LCJuYmYiOjE1ODIwMTY4NDYsImV4cCI6MTU4MzIyNjQ0NiwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTMyIiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjo4Mn19.ejcMW3KtHv3v0kys3QNT_avD4y60lUbCGkxt_7x9Ciw"
    })
    @POST("commentReply")
    Call<CommentResponse> getCommentReply(
            @Body CommentPost commentPost);


    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyMDE2ODQ2LCJuYmYiOjE1ODIwMTY4NDYsImV4cCI6MTU4MzIyNjQ0NiwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTMyIiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjo4Mn19.ejcMW3KtHv3v0kys3QNT_avD4y60lUbCGkxt_7x9Ciw"
    })
    @POST("likeUnlike")
    Call<CommentResponse> getLikeUnlike(
            @Body LikeUnlike likeUnlike);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyMTk5OTcwLCJuYmYiOjE1ODIxOTk5NzAsImV4cCI6MTU4MzQwOTU3MCwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTQ5IiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoxMzV9fQ.v1tbRxgWW6A-dIY90UK-OunOP6trhd0sJNXZbj_kTw4"
    })
    @POST("saveCommunity")
    Call<PostResponse> getPostResponse(
            @Body PostCommunity postCommunity);



    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyNTI2NzUxLCJuYmYiOjE1ODI1MjY3NTEsImV4cCI6MTU4MzczNjM1MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTQ5IiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoxNDN9fQ.zpuj83OlnQQtMCSUDMD7r57E6ZLIguIZA8Oyuxf-a7s"
    })
    @POST("updateProfile")
    Call<PostProfileResponse> updateProfile(
            @Body PostProfileModel postProfileModel);



    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf"
              })
    @POST("signup")
    Call<SignUpResponse> setSignUp(
            @Body SignPostRequest signPostRequest);


    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyODk3NTQxLCJuYmYiOjE1ODI4OTc1NDEsImV4cCI6MTU4NDEwNzE0MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTYxIiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoyNTl9fQ.zQOC85L9TVY8S8IYaRfleQCbE1K-zEl17AwgZ_d17Dg"
    })
    @GET("favouriteList")
    Call<FavouriteResponse> getFavourite(
            @Query("type") String  type,
            @Query("offset") String offset,
            @Query("per_page") String perPage);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyODk3NTQxLCJuYmYiOjE1ODI4OTc1NDEsImV4cCI6MTU4NDEwNzE0MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTYxIiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoyNTl9fQ.zQOC85L9TVY8S8IYaRfleQCbE1K-zEl17AwgZ_d17Dg"
    })
    @GET("favouriteList")
    Call<FavouriteRoomateResponse> getFavouriteRoomate(
            @Query("type") String  type);


    /*  @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: USER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "AuthenticateToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3ZWJmdW1lYXBwLmNvbSIsImF1ZCI6IldlYmZ1bWUgSmFzb24gQXBwIiwiaWF0IjoxNTgyODk3NTQxLCJuYmYiOjE1ODI4OTc1NDEsImV4cCI6MTU4NDEwNzE0MSwiZGF0YSI6eyJ1c2VyX3R5cGUiOiJVU0VSIiwidXNlcl9kZXZpY2VfdHlwZSI6IkFETlJPSUQiLCJ1c2VyX2RldmljZV90b2tlbiI6IjIzNDIzNGR2ZGZkZnNkZnNkZiIsIlNvdXJjZXMiOiJBUFAiLCJ1c2VyX25hbWUiOiJhYmMiLCJ1c2VyX2lkIjoiMTYxIiwibG9naW5fdHlwZSI6Ik5PUk1BTCIsInVzZXJfbG9nX2lkIjoyNTl9fQ.zQOC85L9TVY8S8IYaRfleQCbE1K-zEl17AwgZ_d17Dg"
    })*/
    @GET("propertyById")
    Call<PropertyDetailResponse> getPropertyDetail(
            @HeaderMap Map<String,String> headers,
            @Query("property_id") String  type);


}
