package com.development.mycolive.networking;

import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.alert.AlertRequest;
import com.development.mycolive.model.booking.BookingResponse;
import com.development.mycolive.model.bookingDetail.UploadIdRequest;
import com.development.mycolive.model.bookingDetail.UploadIdResponse;
import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.communityModel.CommunityResponse;
import com.development.mycolive.model.communityModel.SearchCommunityResponse;
import com.development.mycolive.model.editProfile.FacebookLinked;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.PostProfileResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.favourite.FavouriteResponse;
import com.development.mycolive.model.favourite.FavouriteRoomateResponse;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavResponse;
import com.development.mycolive.model.filterModel.FilterSearchResponse;
import com.development.mycolive.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.model.home.HomeResponse;
import com.development.mycolive.model.home.RoommateResponse;
import com.development.mycolive.model.homeProperty.FeatureResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;
import com.development.mycolive.model.loginModel.LoginResponse;
import com.development.mycolive.model.logout.LogoutRequestModel;
import com.development.mycolive.model.logout.LogoutResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityResponse;
import com.development.mycolive.model.myCommunityModel.PostDeleteRequest;
import com.development.mycolive.model.notificationModel.NotificationBodyRequest;
import com.development.mycolive.model.notificationModel.NotificationResponse;
import com.development.mycolive.model.paymentModel.PaymentModeRequest;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.paymentModel.PaymentResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.model.searchDetailPage.SearchDetailResponse;
import com.development.mycolive.model.searchFilterModel.FilterResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpResponse;
import com.development.mycolive.model.stripe.StripeRequestBody;
import com.development.mycolive.model.stripe.StripeServerResponse;
import com.development.mycolive.model.termscondition.TermCondApiResponse;
import com.development.mycolive.model.termscondition.TermConditionResponse;
import com.development.mycolive.model.termscondition.TermRequest;
import com.development.mycolive.model.testimonialmodel.TestimonialResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentResponse;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    Call<LoginResponse> loginUser(
            @HeaderMap Map<String,String> headers,
            @Body LoginRequestModel requestModel);

    @Headers({
            "Content-Type: application/json",
            "Sources: APP",
            "user_type: DRIVER",
            "user_device_type: ADNROID",
            "user_device_token: 234234dvdfdfsdfsdf",
            "Method: POST"
    })
    @POST("forgetPassword")
    Call<LoginResponse> forgotPassword(
            @Body ForgotRequestModel requestModel);



    @GET("bookingList")
    Call<BookingResponse> getBooking(
            @HeaderMap Map<String,String> headers ,
            @Query("type") String  type);


    @GET("getAllByDefaultData")
    Call<FilterResponse> getDefaultData(
            @Query("type") String  type);

    @GET("search")
    Call<FilterSearchResponse> getFilterData(
            @HeaderMap Map<String,String> headers ,
            @Query("type") String  type,
            @Query("city") String city,
            @Query("district") String district,
            @Query("university") String university,
            @Query("duration") String duration,
            @Query("daterange") String range);



    @GET("bookingList")
    Call<BookingHistoryResponse> getHistory(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type,
            @Query("order_id") String orderid);

    @GET("contentManagement")
    Call<TestimonialResponse> getTestimonial(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);


    @POST("changePassword")
    Call<PostResponse> changePassword(
            @HeaderMap Map<String,String> headers,
            @Body ChangePasswordModel passwordModel);

    @POST("upload_id_Proof")
    Call<UploadIdResponse> uploadIdProof(
            @HeaderMap Map<String,String> headers,
            @Body UploadIdRequest request);

    @POST("userLogout")
    Call<LogoutResponse> logout(
            @HeaderMap Map<String,String> headers,
            @Body LogoutRequestModel requestModel);


    @GET("userProfile")
    Call<ProfileResponse> getProfile(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);

    @GET("userProfile")
    Single<ProfileResponse> getProfileData(@HeaderMap Map<String,String> headers,
                                       @Query("type") String  type);

    @GET("homePage")
    Call<HomeResponse> getData();

    @GET("homePage")
    Single<HomeResponse> getHomeData(@HeaderMap Map<String,String> headers);



    @GET("getProperty")
    Call<FeatureResponse> getRoomList(@QueryMap Map<String,String> params,
                                      @Query("offset") String  offset,
                                      @Query("per_page") String  per_page);

    @GET("getProperty")
    Call<SearchDetailResponse> getPropertyById(@Query("property_id") String  id);


    @GET("viewCommunity")
    Call<CommunityResponse> getCommunityData(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);

    @GET("myCommunity")
    Call<MyCommunityResponse> getMyCommunityData(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);

    @POST("deleteComment")
    Call<MyCommunityResponse> deletePost(
            @HeaderMap Map<String,String> headers,
            @Body PostDeleteRequest request);

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





    @GET("viewCommunity")
    Call<ViewCommunityResponse> getCommunityResponse(
            @HeaderMap Map<String,String> headers,
            @Query("comment_id") String id );

    @POST("appropiatePost")
    Call<ViewCommunityResponse> sendComplain(
            @HeaderMap Map<String,String> headers,
            @Body AlertRequest alertRequest);



    @POST("commentReply")
    Call<CommentResponse> getCommentReply(
            @HeaderMap Map<String,String> headers,
            @Body CommentPost commentPost);



    @POST("likeUnlike")
    Call<CommentResponse> getLikeUnlike(
            @HeaderMap Map<String,String> headers,
            @Body LikeUnlike likeUnlike);


    @POST("saveCommunity")
    Call<PostResponse> getPostResponse(
            @HeaderMap Map<String,String> headers,
            @Body PostCommunity postCommunity);


    @POST("updateProfile")
    Call<ProfileResponse> updateProfile(
            @HeaderMap Map<String,String> headers,
            @Body PostProfileModel postProfileModel);

    @POST("connectWithFb")
    Call<ProfileResponse> linkedFacebook(
            @HeaderMap Map<String,String> headers,
            @Body FacebookLinked postProfileModel);



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



    @GET("favouriteList")
    Call<FavouriteResponse> getFavourite(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type,
            @Query("offset") String offset,
            @Query("per_page") String perPage);

    @GET("favouriteList")
    Call<FavouriteRoomateResponse> getFavouriteRoomate(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);

    @GET("propertyById")
    Call<PropertyDetailResponse> getPropertyDetail(
            @HeaderMap Map<String,String> headers,
            @Query("property_id") String  type);


    @POST("viewTermAndCondition")
    Call<TermConditionResponse> getTermsCondition(
            @HeaderMap Map<String,String> headers,
            @Body TermRequest request);

    @POST("booking")
    Call<PaymentResponse> bookingPost(
            @HeaderMap Map<String,String> headers,
            @Body PaymentRequestBody requestBody);


    @POST("view_notification")
    Call<NotificationResponse> getNotification(
            @HeaderMap Map<String,String> headers,
            @Body NotificationBodyRequest requestBody);

    @POST("sandbox_payment")
    Call<StripeServerResponse> getStripeKey(
            @HeaderMap Map<String,String> headers,
            @Body StripeRequestBody requestBody);

    @POST("sandbox_second_payment")
    Call<StripeServerResponse> getStripeKey1(
            @HeaderMap Map<String,String> headers,
            @Body StripeRequestBody requestBody);

    @GET("getProfile")
    Call<RoomateFavResponse> getRoomateDetail(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type,
            @Query("user_id") String id);

    @POST("updateFavourite")
    Call<RoomateFavResponse> setFav(
            @HeaderMap Map<String,String> headers,
            @Body FavouriteRequest requestBody);


    @GET("getProfile")
    Call<RoommateResponse> getRoomateList(
            @HeaderMap Map<String,String> headers,
            @Query("type") String  type);


    @POST("payMonthlyPaymentDetails")
    Call<PaymentResponse> payAmount(
            @HeaderMap Map<String,String> headers,
            @Body PaymentModeRequest request);
}
