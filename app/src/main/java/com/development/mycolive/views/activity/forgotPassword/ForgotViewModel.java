package com.development.mycolive.views.activity.forgotPassword;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.development.mycolive.views.model.forgotModel.ForgotRequestModel;
import com.development.mycolive.views.model.loginModel.LoginApiResponse;

public class ForgotViewModel extends AndroidViewModel {

    public ForgotViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<LoginApiResponse> forgotPassword(Context context, ForgotRequestModel startJobRequestModel) {
        return ForgotRepository.getInstance().forgotPassword(context, startJobRequestModel);
    }
}
