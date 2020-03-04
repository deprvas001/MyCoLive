package com.development.mycolive.views.activity.propertyDetail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class PropertyDetailViewModel extends AndroidViewModel {

    public PropertyDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<PropertyDetailApiResponse> getPropertyDetail(Context context, Map<String,String> headers,String id) {
        return PropertyDetailRepository.getInstance().getPropertyDetail(context,headers, id);
    }
}
