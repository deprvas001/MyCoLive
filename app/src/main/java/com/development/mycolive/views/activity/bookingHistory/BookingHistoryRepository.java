package com.development.mycolive.views.activity.bookingHistory;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.forgotPassword.ForgotRepository;
import com.development.mycolive.views.model.booking.BookingApiResponse;
import com.development.mycolive.views.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.views.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.views.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.views.model.loginModel.LoginApiResponse;
import com.development.mycolive.views.model.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryRepository {
    private static BookingHistoryRepository historyRepository = null;
    private ShipmentApi shipmentApi;

    public BookingHistoryRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static BookingHistoryRepository getInstance(){
        if(historyRepository == null)
            historyRepository =new BookingHistoryRepository();
        return historyRepository;
    }

    public MutableLiveData<BookingHistoryApiResponse> bookingHistory(Context context,String type,String orderId){
        final MutableLiveData<BookingHistoryApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getHistory(type,orderId).enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(Call<BookingHistoryResponse> call, Response<BookingHistoryResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    historyResponseLiveData.setValue(new BookingHistoryApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new BookingHistoryApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new BookingHistoryApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
