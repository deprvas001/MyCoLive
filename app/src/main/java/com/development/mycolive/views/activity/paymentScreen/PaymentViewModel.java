package com.development.mycolive.views.activity.paymentScreen;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class PaymentViewModel extends AndroidViewModel {

    public PaymentViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<PaymentApiResponse> bookingPost(Context context, Map<String,String> headers, PaymentRequestBody requestBody) {
        return PaymentRepository.getInstance().bookingPost(context,headers,requestBody);
    }
}
