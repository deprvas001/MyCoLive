package com.development.mycolive.views.activity.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.networking.ShipmentApi;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;
import com.development.mycolive.model.loginModel.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
   private static LoginRepository loginRepository = null;
   private ShipmentApi shipmentApi;

   public LoginRepository(){
          shipmentApi = RetrofitService.getRetrofitInstance().create(ShipmentApi.class);
   }

    public static LoginRepository getInstance(){
       if(loginRepository == null)
             loginRepository =new LoginRepository();
       return loginRepository;
    }

    public LiveData<LoginApiResponse> loginUser(Context context, LoginRequestModel requestModel ){
     final MutableLiveData<LoginApiResponse> loginResponseLiveData =new MutableLiveData<>();


        shipmentApi.loginUser(requestModel).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 401 || response.code() == 400 || response.code() == 500){
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        int status = jObjError.getInt("status");
                        loginResponseLiveData.setValue(new LoginApiResponse(message,status,response.code()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    if(response.isSuccessful()){
                        loginResponseLiveData.setValue(new LoginApiResponse(response.body()));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new LoginApiResponse(t));
            }
        });

        return  loginResponseLiveData;
    }
}
