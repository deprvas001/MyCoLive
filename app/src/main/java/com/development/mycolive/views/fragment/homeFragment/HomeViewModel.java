package com.development.mycolive.views.fragment.homeFragment;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.home.HomeResponse;
import com.development.mycolive.model.home.RoomateApiResponse;
import com.development.mycolive.networking.RetrofitApiService;
import com.development.mycolive.networking.RetrofitService;
import com.development.mycolive.views.activity.HomeScreen;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {
    public MutableLiveData<HomeResponse> homeResponse = new MutableLiveData<HomeResponse>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RetrofitApiService retrofitService = new RetrofitApiService();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<HomeApiResponse> getData(Context context, String type) {
        return HomeRepository.getInstance().getData(context, type);
    }

    public MutableLiveData<RoomateApiResponse> getRoomateData(Context context, Map<String,String> headers, String type) {
        return HomeRepository.getInstance().getRoommateData(context,headers, type);
    }

    public void refresh(Map<String,String> headers){
        fetchFromRemote(headers);
    }

    private void fetchFromRemote(Map<String,String> headers){
        loading.setValue(true);
        disposable.add(
                       retrofitService.getData(headers)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<HomeResponse>() {
                            @Override
                            public void onSuccess(HomeResponse response) {

                                homeResponse.setValue(response);
                                error.setValue(false);
                                loading.setValue(false);

                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
