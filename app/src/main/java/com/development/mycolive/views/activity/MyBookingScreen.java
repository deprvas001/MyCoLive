package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityMyBookingScreenBinding;
import com.development.mycolive.views.fragment.booking.CurrentBooking;

public class MyBookingScreen extends AppCompatActivity implements View.OnClickListener {
ActivityMyBookingScreenBinding screenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_booking_screen);
        screenBinding.toolbar.setTitle(getString(R.string.mybooking));
        setSupportActionBar( screenBinding.toolbar);
        loadFragment(new CurrentBooking());
        setClickListener();
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private void setClickListener(){
        screenBinding.currentBooking.setOnClickListener(this);
        screenBinding.pastBooking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.current_booking:
                screenBinding.currentBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                screenBinding.pastBooking.setBackground(null);
                screenBinding.currentBooking.setTextColor(getResources().getColor(R.color.white));
                screenBinding.pastBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new CurrentBooking());
                break;

            case R.id.past_booking:
                screenBinding.pastBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                screenBinding.currentBooking.setBackground(null);
                screenBinding.pastBooking.setTextColor(getResources().getColor(R.color.white));
                screenBinding.currentBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new CurrentBooking());
                break;
        }
    }
}
