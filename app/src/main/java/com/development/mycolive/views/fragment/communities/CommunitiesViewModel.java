package com.development.mycolive.views.fragment.communities;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.communityModel.CommunityApiResponse;

public class CommunitiesViewModel extends AndroidViewModel {

    public CommunitiesViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<CommunityApiResponse> getCommunityData(Context context, String type) {
        return CommunitiesRepository.getInstance().getCommunityData(context, type);
    }
}
