package com.development.mycolive.views.activity.propertyDetail;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.R;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailResponse;
import com.development.mycolive.model.termscondition.TermCondApiResponse;
import com.development.mycolive.model.termscondition.TermConditionResponse;
import com.development.mycolive.model.termscondition.TermRequest;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.views.activity.postScreen.PostRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailRepository {

    private static PropertyDetailRepository propertyDetailRepository = null;
    private ShipmentApi shipmentApi;

    public PropertyDetailRepository(){
        shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
    }

    public static PropertyDetailRepository getInstance(){
        if(propertyDetailRepository == null)
            propertyDetailRepository =new PropertyDetailRepository();
        return propertyDetailRepository;
    }

    public MutableLiveData<PropertyDetailApiResponse> getPropertyDetail(Context context, Map<String,String> headers, String id){
        final MutableLiveData<PropertyDetailApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getPropertyDetail(headers,id).enqueue(new Callback<PropertyDetailResponse>() {
            @Override
            public void onResponse(Call<PropertyDetailResponse> call, Response<PropertyDetailResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new PropertyDetailApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new PropertyDetailApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<PropertyDetailResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new PropertyDetailApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }


    public MutableLiveData<TermCondApiResponse> getTermsConditon(Context context, Map<String,String> headers, TermRequest request){
        final MutableLiveData<TermCondApiResponse> loginResponseLiveData =new MutableLiveData<>();
        String token = String.valueOf(R.string.token);

        shipmentApi.getTermsCondition(headers,request).enqueue(new Callback<TermConditionResponse>() {
            @Override
            public void onResponse(Call<TermConditionResponse> call, Response<TermConditionResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new TermCondApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new TermCondApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<TermConditionResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new TermCondApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
