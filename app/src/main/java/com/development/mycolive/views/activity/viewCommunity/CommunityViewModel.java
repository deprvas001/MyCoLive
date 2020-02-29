package com.development.mycolive.views.activity.viewCommunity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;

public class CommunityViewModel extends AndroidViewModel {

    public CommunityViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context,String id) {
        return CommunityRepository.getInstance().getCommunityData(context,id);
    }

    public MutableLiveData<CommentApiResponse> getCommentResponse(Context context, CommentPost commentPost) {
        return CommunityRepository.getInstance().getCommentResponse(context,commentPost);
    }

    public MutableLiveData<CommentApiResponse> getLikeUnilike(Context context, LikeUnlike likeUnlike) {
        return CommunityRepository.getInstance().getLikeUnlike(context,likeUnlike);
    }
}
