package com.development.mycolive.views.activity.bookingDetail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingDetail.BookingDetailApiResponse;
import com.development.mycolive.model.bookingDetail.UploadIdRequest;
import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class BookingDetailsViewModel extends AndroidViewModel {
    public BookingDetailsViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<BookingDetailApiResponse> uploadIdProof(Context context, Map<String,String> headers, UploadIdRequest request) {
        return BookingDetailsRepository.getInstance().uploadIdProof(context,headers, request);
    }
}
