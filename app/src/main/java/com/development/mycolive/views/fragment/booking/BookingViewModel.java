package com.development.mycolive.views.fragment.booking;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.development.mycolive.views.model.booking.BookingApiResponse;

public class BookingViewModel extends AndroidViewModel {

    public BookingViewModel(@NonNull Application application){
             super(application);
    }

    public MutableLiveData<BookingApiResponse> getBooking(Context context, String type) {
        return BookingRepository.getInstance().getBooking(context, type);
    }
}
