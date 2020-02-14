package com.development.mycolive.views.activity.searchResult;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.home.HomeApiResponse;

public class SearchViewModel extends AndroidViewModel {

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<HomeApiResponse> getData(Context context, String type,String offset,String per_page) {
        return SearchResultRepository.getInstance().getData(context, type,offset,per_page);
    }
}
