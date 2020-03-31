package com.development.mycolive.views.fragment.signUp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.views.fragment.profile.ProfileReporsitory;

public class SignUpViewModel extends AndroidViewModel {

    public SignUpViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<SignUpApiResponse> setSignUp(Context context, SignPostRequest signPostRequest) {
        return SignRepository.getInstance().setSignUp(context, signPostRequest);
    }
}
