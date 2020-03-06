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
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentPastBookingBinding;
import com.development.mycolive.adapter.CurrentBookingAdapter;
import com.development.mycolive.model.booking.BookingApiResponse;
import com.development.mycolive.model.booking.BookingData;
import com.development.mycolive.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastBooking extends Fragment {
    FragmentPastBookingBinding bookingBinding ;
    private CurrentBookingAdapter bookingAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<BookingData> bookingList = new ArrayList<>();
    BookingViewModel bookingViewModel;
    SessionManager session;
    String token="";
    public PastBooking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_past_booking, container, false);
        getSession();
        return bookingBinding.getRoot();
    }

    private void setRecycleView(){
        bookingAdapter = new CurrentBookingAdapter(getActivity(), bookingList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        bookingBinding.recyclerView.setLayoutManager(mLayoutManager);
        bookingBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingBinding.recyclerView.setAdapter(bookingAdapter);
    }

    private void getBooking(String token) {
        String type = "PASTBOOKING";

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        bookingViewModel = ViewModelProviders.of(getActivity()).get(BookingViewModel.class);

        bookingViewModel.getBooking(getActivity(),headers, type).observe(getActivity(), new Observer<BookingApiResponse>() {
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
    private void getSession(){
        session = new SessionManager(getActivity());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);


        getBooking(token);
    }
}
