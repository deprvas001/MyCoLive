package com.development.mycolive.views.activity.paymentMode;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentModeRequest;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.views.activity.paymentScreen.PaymentRepository;

import java.util.Map;

public class PaymentModeViewModel extends AndroidViewModel {

    public PaymentModeViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<PaymentApiResponse> payAmount(Context context, Map<String,String> headers, PaymentModeRequest requestBody) {
        return PaymentModeRepository.getInstance().payAmount(context,headers,requestBody);
    }
}
