package com.development.mycolive.views.fragment.communities;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.SearchCommunityApiResponse;
import com.development.mycolive.model.postCommunity.PostApiResponse;

public class CommunitiesViewModel extends AndroidViewModel {

    public CommunitiesViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<CommunityApiResponse> getCommunityData(Context context, String type) {
        return CommunitiesRepository.getInstance().getCommunityData(context, type);
    }

    public MutableLiveData<SearchCommunityApiResponse> getSearchData(Context context, String type) {
        return CommunitiesRepository.getInstance().getSearchData(context, type);
    }
}
