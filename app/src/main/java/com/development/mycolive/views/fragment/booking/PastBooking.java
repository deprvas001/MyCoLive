package com.development.mycolive.views.fragment.booking;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentPastBookingBinding;
import com.development.mycolive.adapter.CurrentBookingAdapter;
import com.development.mycolive.model.booking.BookingApiResponse;
import com.development.mycolive.model.booking.BookingData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastBooking extends Fragment {
    FragmentPastBookingBinding bookingBinding ;
    private CurrentBookingAdapter bookingAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<BookingData> bookingList = new ArrayList<>();
    BookingViewModel bookingViewModel;

    public PastBooking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_past_booking, container, false);
        getBooking();
        return bookingBinding.getRoot();
    }

    private void setRecycleView(){
        bookingAdapter = new CurrentBookingAdapter(getActivity(), bookingList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        bookingBinding.recyclerView.setLayoutManager(mLayoutManager);
        bookingBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingBinding.recyclerView.setAdapter(bookingAdapter);
    }

    private void getBooking() {
        String type = "PASTBOOKING";

        bookingViewModel = ViewModelProviders.of(getActivity()).get(BookingViewModel.class);

        bookingViewModel.getBooking(getActivity(), type).observe(getActivity(), new Observer<BookingApiResponse>() {
            @Override
            public void onChanged(BookingApiResponse bookingApiResponse) {
                if (bookingApiResponse.response != null) {

                    bookingList.clear();
                    bookingList = bookingApiResponse.response.getBookingDataList();
                    setRecycleView();

                }
            }
        });
    }

}
