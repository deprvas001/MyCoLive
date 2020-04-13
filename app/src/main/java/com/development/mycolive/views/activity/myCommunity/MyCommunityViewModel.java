package com.development.mycolive.views.activity.myCommunity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.PostDeleteRequest;
import com.development.mycolive.views.fragment.communities.CommunitiesRepository;

import java.util.Map;

public class MyCommunityViewModel extends AndroidViewModel {

    public MyCommunityViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<MyCommunityApiResponse> getCommunityData(Context context, Map<String,String> headers, String type) {
        return MyCommunityRepository.getInstance().getCommunityData(context,headers, type);
    }

    public MutableLiveData<MyCommunityApiResponse> deletePost(Context context, Map<String,String> headers, PostDeleteRequest request) {
        return MyCommunityRepository.getInstance().deletePost(context,headers, request);
    }
}
