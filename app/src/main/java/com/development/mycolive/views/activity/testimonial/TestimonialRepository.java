package com.development.mycolive.views.activity.testimonial;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.bookingHistory.BookingHistoryResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimonialRepository {
    private static TestimonialRepository historyRepository = null;
    private ShipmentApi shipmentApi;

    public TestimonialRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static TestimonialRepository getInstance(){
        if(historyRepository == null)
            historyRepository =new TestimonialRepository();
        return historyRepository;
    }

    public MutableLiveData<TestimonialApiResponse> getTestimonial(Context context, Map<String,String> headers, String type){
        final MutableLiveData<TestimonialApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.getTestimonial(headers,type).enqueue(new Callback<TestimonialResponse>() {
            @Override
            public void onResponse(Call<TestimonialResponse> call, Response<TestimonialResponse> response) {
                if(response.code() == 401){
                    historyResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    historyResponseLiveData.setValue(new TestimonialApiResponse(response.code()));
                }
                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new TestimonialApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<TestimonialResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new TestimonialApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }
}
