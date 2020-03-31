package com.development.mycolive.views.activity.paymentScreen;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.paymentModel.PaymentResponse;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRepository {

    private static PaymentRepository paymentRepository  = null;
    private ShipmentApi shipmentApi;

    public PaymentRepository (){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static PaymentRepository  getInstance() {
        if (paymentRepository == null)
            paymentRepository = new PaymentRepository();
        return paymentRepository;
    }


    public MutableLiveData<PaymentApiResponse> bookingPost(Context context, Map<String,String> headers, PaymentRequestBody requestBody){
        final MutableLiveData<PaymentApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.bookingPost(headers,requestBody).enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {

                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        historyResponseLiveData.setValue(new PaymentApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new PaymentApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new PaymentApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
