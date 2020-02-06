package com.development.mycolive.views.fragment.booking;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.model.booking.BookingApiResponse;
import com.development.mycolive.views.model.booking.BookingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class BookingRepository {

    private static BookingRepository bookingRepository = null;
    private ShipmentApi shipmentApi;

    public BookingRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static BookingRepository getInstance(){
        if(bookingRepository == null)
            bookingRepository =new BookingRepository();
        return bookingRepository;
    }

    public MutableLiveData<BookingApiResponse> getBooking(Context context, String type){
        final MutableLiveData<BookingApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getBooking(type).enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if(response.code() == 401){
                    loginResponseLiveData.setValue(null);
                }else if(response.code() == 400){
                    loginResponseLiveData.setValue(new BookingApiResponse(response.code()));
                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new BookingApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new BookingApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
