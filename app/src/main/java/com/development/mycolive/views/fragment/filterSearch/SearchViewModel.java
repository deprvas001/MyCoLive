package com.development.mycolive.views.fragment.filterSearch;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.filterModel.FilterSearchApiResponse;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;

import java.util.Map;

public class SearchViewModel extends AndroidViewModel {
    public SearchViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<FilterApiResponse> getDefaultData(Context context, String type) {
        return SearchFilterRepository.getInstance().getDefaultData(context, type);
    }

    public MutableLiveData<FilterSearchApiResponse> getSearchData(Context context, Map<String,String> headers, String type, String city,
                                                                  String district, String university, String duration, String range) {
        return SearchFilterRepository.getInstance().getSearchData(context,headers, type,city,district,university,duration,range);
    }
}
