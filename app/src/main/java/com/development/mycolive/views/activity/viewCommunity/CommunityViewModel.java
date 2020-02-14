package com.development.mycolive.views.activity.viewCommunity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;

public class CommunityViewModel extends AndroidViewModel {

    public CommunityViewModel(@NonNull Application application){
        super(application);
    }

   /* public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context, String type,String id) {
        return CommunityRepository.getInstance().getCommunityData(context, type,id);
    }*/
}
