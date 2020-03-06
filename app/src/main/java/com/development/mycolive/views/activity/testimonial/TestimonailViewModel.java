package com.development.mycolive.views.activity.testimonial;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryRepository;

import java.util.Map;

public class TestimonailViewModel extends AndroidViewModel {

    public TestimonailViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<TestimonialApiResponse> getTestimonial(Context context, Map<String,String> headers, String type ) {
        return TestimonialRepository.getInstance().getTestimonial(context,headers, type);
    }
}
