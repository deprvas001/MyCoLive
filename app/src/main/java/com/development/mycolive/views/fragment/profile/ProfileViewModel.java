package com.development.mycolive.views.fragment.profile;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.editProfile.FacebookLinked;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.editProfile.ProfileResponse;
import com.development.mycolive.model.home.HomeResponse;
import com.development.mycolive.networking.RetrofitApiService;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends AndroidViewModel {

    public MutableLiveData<ProfileResponse> profileResponse = new MutableLiveData<ProfileResponse>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RetrofitApiService retrofitService = new RetrofitApiService();

    public ProfileViewModel(@NonNull Application application){
        super(application);
    }

    public MutableLiveData<ProfileApiResponse> getProfile(Context context, Map<String,String> headers , String type) {
        return ProfileReporsitory.getInstance().getProfile(context, headers , type);
    }

    public MutableLiveData<ProfileApiResponse> updateProfile(Context context,  Map<String,String> headers,PostProfileModel postProfileModel) {

        return ProfileReporsitory.getInstance().updateProfile(context,headers, postProfileModel);

    }

    public MutableLiveData<ProfileApiResponse> linkedFacebook(Context context, Map<String,String> headers, FacebookLinked postProfileModel) {
        return ProfileReporsitory.getInstance().linkedFacebook(context,headers, postProfileModel);
    }

    public void refresh(Map<String,String> headers , String type){
        fetchFromRemote(headers,type);
    }

    private void fetchFromRemote(Map<String,String> headers , String type){
        loading.setValue(true);
        disposable.add(
                retrofitService.getProfileData(headers, type)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ProfileResponse>() {
                            @Override
                            public void onSuccess(ProfileResponse response) {

                                profileResponse.setValue(response);
                                error.setValue(false);
                                loading.setValue(false);

                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                loading.setValue(false);
                               // (Http)
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
