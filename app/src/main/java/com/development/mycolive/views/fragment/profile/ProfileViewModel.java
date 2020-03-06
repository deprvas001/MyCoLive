package com.development.mycolive.views.fragment.profile;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;

import java.util.Map;

public class ProfileViewModel extends AndroidViewModel {

    public ProfileViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers , String type) {
        return ProfileReporsitory.getInstance().getProfile(context, headers , type);
    }

    public MutableLiveData<ProfilePostApiResponse> updateProfile(Context context,  Map<String,String> headers,PostProfileModel postProfileModel) {
        return ProfileReporsitory.getInstance().updateProfile(context,headers, postProfileModel);
    }
}
