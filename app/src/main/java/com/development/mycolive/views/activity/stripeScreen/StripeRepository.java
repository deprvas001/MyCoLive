package com.development.mycolive.views.activity.stripeScreen;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.paymentModel.PaymentResponse;
import com.development.mycolive.model.stripe.StripeApiResponse;
import com.development.mycolive.model.stripe.StripeRequestBody;
import com.development.mycolive.model.stripe.StripeServerResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StripeRepository {

    private static StripeRepository stripeRepository  = null;
    private ShipmentApi shipmentApi;

    public StripeRepository (){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static StripeRepository  getInstance() {
        if (stripeRepository == null)
            stripeRepository = new StripeRepository();
        return stripeRepository;
    }


    public MutableLiveData<StripeApiResponse> getStripeKey(Context context, Map<String,String> headers, StripeRequestBody requestBody){
        final MutableLiveData<StripeApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getStripeKey(headers,requestBody).enqueue(new Callback<StripeServerResponse>() {
            @Override
            public void onResponse(Call<StripeServerResponse> call, Response<StripeServerResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(new StripeApiResponse(response.code()));
                }
                else if(response.code() == 400){
                    // ResponseBody responseBody = response.errorBody();
                    //  String message =    response.message();
                    //  historyResponseLiveData.setValue(new PaymentApiResponse(message));
                    historyResponseLiveData.setValue(new StripeApiResponse(response.code()));
                }

                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new StripeApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<StripeServerResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new StripeApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}