package com.development.mycolive.views.fragment.profile;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.ProfileApiResponse;

public class ProfileViewModel extends AndroidViewModel {

    public ProfileViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, String type) {
        return ProfileReporsitory.getInstance().getProfile(context, type);
    }
}
