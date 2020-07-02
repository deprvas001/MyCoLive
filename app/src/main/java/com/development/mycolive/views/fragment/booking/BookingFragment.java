package com.development.mycolive.views.fragment.booking;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentBookingBinding;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.views.fragment.booking.CurrentBooking;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment implements View.OnClickListener {
    FragmentBookingBinding bookingBinding;
    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_booking, container, false);
        loadFragment(new CurrentBooking());
        setClickListener();
        initializeView();
        return bookingBinding.getRoot();
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private void setClickListener(){
        bookingBinding.currentBooking.setOnClickListener(this);
        bookingBinding.pastBooking.setOnClickListener(this);
        ((ShowHomeScreen) getActivity()).screenBinding.appBar.titleTxt.setVisibility(View.VISIBLE);
        ((ShowHomeScreen) getActivity()).screenBinding.appBar.logo.setVisibility(View.GONE);

        ((ShowHomeScreen)getActivity()).screenBinding.appBar.notification.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.notification:
                startActivity(new Intent(getActivity(), Notification.class));
                break;

            case R.id.current_booking:
                bookingBinding.currentBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                bookingBinding.pastBooking.setBackground(null);
                bookingBinding.currentBooking.setTextColor(getResources().getColor(R.color.white));
                bookingBinding.pastBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new CurrentBooking());
                break;

            case R.id.past_booking:
                bookingBinding.pastBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                bookingBinding.currentBooking.setBackground(null);
                bookingBinding.pastBooking.setTextColor(getResources().getColor(R.color.white));
                bookingBinding.currentBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new PastBooking());
                break;
        }
    }
    private void initializeView(){
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText(getString(R.string.mybooking));
    }

}
