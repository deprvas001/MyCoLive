package com.development.mycolive.views.fragment.booking;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.development.mycolive.model.booking.BookingApiResponse;

import java.util.Map;

public class BookingViewModel extends AndroidViewModel {

    public BookingViewModel(@NonNull Application application){
             super(application);
    }

    public MutableLiveData<BookingApiResponse> getBooking(Context context, Map<String,String> headers , String type) {
        return BookingRepository.getInstance().getBooking(context,headers, type);
    }
}
