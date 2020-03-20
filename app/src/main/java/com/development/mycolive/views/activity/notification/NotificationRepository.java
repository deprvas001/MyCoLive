package com.development.mycolive.views.activity.notification;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.notificationModel.NotificationApiResponse;
import com.development.mycolive.model.notificationModel.NotificationBodyRequest;
import com.development.mycolive.model.notificationModel.NotificationResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private static NotificationRepository notificationRepository = null;
    private ShipmentApi shipmentApi;

    public NotificationRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static NotificationRepository getInstance(){
        if(notificationRepository  == null)
            notificationRepository  =new NotificationRepository();
        return notificationRepository ;
    }

    public MutableLiveData<NotificationApiResponse> getNotificationList(Context context, Map<String,String> headers, NotificationBodyRequest bodyRequest){
        final MutableLiveData<NotificationApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getNotification(headers,bodyRequest).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    historyResponseLiveData.setValue(new NotificationApiResponse(response.code()));
                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new NotificationApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new NotificationApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }

}

