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

import java.util.Map;

public class CommunityViewModel extends AndroidViewModel {

    public CommunityViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ViewCommunityApiResponse> getCommunityData(Context context, Map<String,String> headers,String id) {
        return CommunityRepository.getInstance().getCommunityData(context,headers,id);
    }

    public MutableLiveData<CommentApiResponse> getCommentResponse(Context context, Map<String,String> headers , CommentPost commentPost) {
        return CommunityRepository.getInstance().getCommentResponse(context,headers,commentPost);
    }

    public MutableLiveData<CommentApiResponse> getLikeUnilike(Context context,Map<String,String> headers, LikeUnlike likeUnlike) {
        return CommunityRepository.getInstance().getLikeUnlike(context,headers,likeUnlike);
    }
}
