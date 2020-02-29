package com.development.mycolive.views.activity.postScreen;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.views.activity.viewCommunity.CommunityRepository;

public class PostViewModel extends AndroidViewModel {

    public PostViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<PostApiResponse> getResponse(Context context, PostCommunity postCommunity) {
        return PostRepository.getInstance().getResponse(context,postCommunity);
    }

}
