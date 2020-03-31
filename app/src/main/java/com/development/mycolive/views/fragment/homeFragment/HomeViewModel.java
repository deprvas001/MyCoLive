package com.development.mycolive.views.fragment.homeFragment;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.home.RoomateApiResponse;

import java.util.Map;

public class HomeViewModel extends AndroidViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<HomeApiResponse> getData(Context context, String type) {
        return HomeRepository.getInstance().getData(context, type);
    }

    public MutableLiveData<RoomateApiResponse> getRoomateData(Context context, Map<String,String> headers, String type) {
        return HomeRepository.getInstance().getRoommateData(context,headers, type);
    }
}
