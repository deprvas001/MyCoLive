package com.development.mycolive.views.activity.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;

public class LoginViewModel extends ViewModel {
    private MediatorLiveData<LoginApiResponse> mutableLiveData;
    private LoginRepository loginRepository;

    public LoginViewModel() {
           mutableLiveData =new MediatorLiveData<>();
           loginRepository =new LoginRepository();
    }

    public LiveData<LoginApiResponse> getLoginUser(Context context, LoginRequestModel requestModel ){
              mutableLiveData.addSource(loginRepository.loginUser(context,requestModel), new Observer<LoginApiResponse>() {
                  @Override
                  public void onChanged(LoginApiResponse loginApiResponse) {

                      mutableLiveData.setValue(loginApiResponse);
                  }
              });

              return mutableLiveData;
    }
}
