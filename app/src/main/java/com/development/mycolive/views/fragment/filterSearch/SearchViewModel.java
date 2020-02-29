package com.development.mycolive.views.fragment.filterSearch;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;

public class SearchViewModel extends AndroidViewModel {
    public SearchViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<FilterApiResponse> getDefaultData(Context context, String type) {
        return SearchFilterRepository.getInstance().getDefaultData(context, type);
    }
}
