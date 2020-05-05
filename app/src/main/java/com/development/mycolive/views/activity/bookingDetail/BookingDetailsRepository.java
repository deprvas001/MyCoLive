package com.development.mycolive.views.activity.bookingDetail;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.bookingDetail.BookingDetailApiResponse;
import com.development.mycolive.model.bookingDetail.UploadIdRequest;
import com.development.mycolive.model.bookingDetail.UploadIdResponse;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetailsRepository {


    private static BookingDetailsRepository historyRepository = null;
    private ShipmentApi shipmentApi;

    public BookingDetailsRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static BookingDetailsRepository getInstance(){
        if(historyRepository == null)
            historyRepository =new BookingDetailsRepository();
        return historyRepository;
    }

    public MutableLiveData<BookingDetailApiResponse> uploadIdProof(Context context, Map<String,String> headers, UploadIdRequest request){
        final MutableLiveData<BookingDetailApiResponse> historyResponseLiveData =new MutableLiveData<>();

        shipmentApi.uploadIdProof(headers,request).enqueue(new Callback<UploadIdResponse>() {
            @Override
            public void onResponse(Call<UploadIdResponse> call, Response<UploadIdResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        historyResponseLiveData.setValue(new BookingDetailApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                else {

                    if(response.isSuccessful()){
                        historyResponseLiveData.setValue(new BookingDetailApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadIdResponse> call, Throwable t) {
                historyResponseLiveData.setValue(new BookingDetailApiResponse(t));
            }
        });

        return   historyResponseLiveData;
    }


}
