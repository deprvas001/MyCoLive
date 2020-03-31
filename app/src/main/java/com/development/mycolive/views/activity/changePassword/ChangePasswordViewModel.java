package com.development.mycolive.views.activity.changePassword;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.logout.LogoutApiResponse;
import com.development.mycolive.model.logout.LogoutRequestModel;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.views.activity.testimonial.TestimonialRepository;

import java.util.Map;

public class ChangePasswordViewModel extends AndroidViewModel {

    public ChangePasswordViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<PostApiResponse> changePassword(Context context, Map<String,String> headers, ChangePasswordModel passwordModel ) {
        return ChangePasswordRepository.getInstance().changePassword(context,headers, passwordModel);
    }

    public MutableLiveData<LogoutApiResponse> logout(Context context, Map<String,String> headers, LogoutRequestModel requestModel ) {
        return ChangePasswordRepository.getInstance().logout(context,headers, requestModel);
    }
}
