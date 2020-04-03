package com.development.mycolive.views.activity.searchResult;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.homeProperty.FeatureApiResponse;

import java.util.Map;

public class SearchViewModel extends AndroidViewModel {

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<FeatureApiResponse> getData(Context context, Map<String,String> params, String offset, String per_page) {
        return SearchResultRepository.getInstance().getData(context, params,offset,per_page);
    }
}
