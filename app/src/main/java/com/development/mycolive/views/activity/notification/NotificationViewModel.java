package com.development.mycolive.views.activity.notification;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.notificationModel.NotificationApiResponse;
import com.development.mycolive.model.notificationModel.NotificationBodyRequest;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class NotificationViewModel extends AndroidViewModel {
    public NotificationViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<NotificationApiResponse> getNotificationList(Context context, Map<String,String> headers, NotificationBodyRequest bodyRequest) {
        return NotificationRepository.getInstance().getNotificationList(context,headers, bodyRequest);
    }
}
