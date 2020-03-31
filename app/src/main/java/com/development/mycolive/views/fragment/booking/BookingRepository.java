package com.development.mycolive.views.fragment.booking;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.booking.BookingApiResponse;
import com.development.mycolive.model.booking.BookingResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

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

    public MutableLiveData<BookingApiResponse> getBooking(Context context, Map<String,String> headers , String type){
        final MutableLiveData<BookingApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getBooking(headers,type).enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new BookingApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
