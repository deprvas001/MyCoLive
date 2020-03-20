package com.development.mycolive.views.activity.stripeScreen;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.homeProperty.FeatureApiResponse;
import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.stripe.StripeApiResponse;
import com.development.mycolive.model.stripe.StripeRequestBody;
import com.development.mycolive.views.activity.paymentScreen.PaymentRepository;
import com.development.mycolive.views.activity.searchResult.SearchResultRepository;

import java.util.Map;

public class StripPaymentViewModel extends AndroidViewModel {

    public StripPaymentViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<StripeApiResponse> getStripeKey(Context context, Map<String,String> headers, StripeRequestBody requestBody) {
        return StripeRepository.getInstance().getStripeKey(context,headers,requestBody);
    }
}
