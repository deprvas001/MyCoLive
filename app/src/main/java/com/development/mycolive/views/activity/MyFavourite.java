package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityMyFavouriteBinding;
import com.development.mycolive.views.fragment.favouriteBooking.FavouriteProperty;
import com.development.mycolive.views.fragment.FavouriteRoomate;

public class MyFavourite extends AppCompatActivity implements View.OnClickListener {
ActivityMyFavouriteBinding favouriteBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouriteBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_favourite);
        favouriteBinding.toolbar.setTitle(getString(R.string.my_favourite));
        setSupportActionBar( favouriteBinding.toolbar);
        loadFragment(new FavouriteProperty());
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
        favouriteBinding.currentBooking.setOnClickListener(this);
        favouriteBinding.pastBooking.setOnClickListener(this);
        favouriteBinding.back.setOnClickListener(this);
        favouriteBinding.notification.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.current_booking:
                favouriteBinding.currentBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                favouriteBinding.pastBooking.setBackground(null);
                favouriteBinding.currentBooking.setTextColor(getResources().getColor(R.color.white));
                favouriteBinding.pastBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new FavouriteProperty());
                break;

            case R.id.past_booking:
                favouriteBinding.pastBooking.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                favouriteBinding.currentBooking.setBackground(null);
                favouriteBinding.pastBooking.setTextColor(getResources().getColor(R.color.white));
                favouriteBinding.currentBooking.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                loadFragment(new FavouriteRoomate());
                break;

            case R.id.back:
                finish();
                break;

            case R.id.notification:
                startActivity(new Intent(this,Notification.class));
                break;
        }
    }
}
