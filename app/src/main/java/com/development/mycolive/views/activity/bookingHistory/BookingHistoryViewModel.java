package com.development.mycolive.views.activity.bookingHistory;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;

public class BookingHistoryViewModel extends AndroidViewModel {

    public BookingHistoryViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<BookingHistoryApiResponse> bookingHistory(Context context, String type ,String orderId) {
        return BookingHistoryRepository.getInstance().bookingHistory(context, type,orderId);
    }
}
