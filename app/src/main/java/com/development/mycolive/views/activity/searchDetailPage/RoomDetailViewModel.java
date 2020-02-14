package com.development.mycolive.views.activity.searchDetailPage;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.searchDetailPage.SearchDetailApiRsponse;

public class RoomDetailViewModel extends AndroidViewModel {

    public RoomDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<SearchDetailApiRsponse> getData(Context context, String id) {
        return RoomDetailRepository.getInstance().getData(context, id);
    }
}
