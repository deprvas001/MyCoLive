package com.development.mycolive.views.activity;

import android.content.Intent;
import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityShowHomeScreenBinding;
import com.development.mycolive.views.fragment.booking.BookingFragment;
import com.development.mycolive.views.fragment.communities.Communities;
import com.development.mycolive.views.fragment.homeFragment.Home;
import com.development.mycolive.views.fragment.profile.ProfileScreenOne;
import com.development.mycolive.views.fragment.filterSearch.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ViewGroup;

public class ShowHomeScreen extends BaseActivity implements View.OnClickListener
        /*implements NavigationView.OnNavigationItemSelectedListener*/ {
 public    ActivityShowHomeScreenBinding screenBinding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new Home();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_search:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new Search();
                    loadFragment(fragment);
                    return true;

                case R.id.booking:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new BookingFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.profile:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new ProfileScreenOne();
                    loadFragment(fragment);
                    return true;

                case R.id.community:
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    fragment = new Communities();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this,R.layout.activity_show_home_screen);
        screenBinding.appBar.titleTxt.setText(getResources().getString(R.string.home_screen));
        initializeView();
        loadFragment(new Home());
    }


    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
      //  transaction.addToBackStack(null);
        transaction.commit();
    }
    private void initializeView(){
        setSupportActionBar(screenBinding.appBar.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,screenBinding.drawerLayout, screenBinding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        screenBinding.drawerLayout.addDrawerListener(toggle);
        toggle.setHomeAsUpIndicator(R.drawable.ic_hamburger);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        setClickListener();

    }

    private void setClickListener(){
      //  screenBinding.navView.setNavigationItemSelectedListener(this);
        screenBinding.appBar.homeScreen.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        screenBinding.customNavigationDrawer.favouriteLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.testimonialLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.passwordLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.contactLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.logout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.aboutUs.setOnClickListener(this);
        screenBinding.appBar.notification.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.favourite_layout:
                startActivity(new Intent(ShowHomeScreen.this,MyFavourite.class));
               // checkDrawer();
                break;

            case R.id.testimonial_layout:
               startActivity(new Intent(this,Testimonials.class));
               // checkDrawer();
                break;

            case R.id.password_layout:
                startActivity(new Intent(this,ChangePassword.class));
              //  checkDrawer();
                break;

            case R.id.contact_layout:
                startActivity(new Intent(this,ContactUs.class));
               // checkDrawer();
                break;

            case R.id.about_us:
                startActivity(new Intent(this,AboutUs.class));
                // checkDrawer();
                break;

            case R.id.notification:
                startActivity(new Intent(this,Notification.class));
                // checkDrawer();
                break;

            case R.id.logout:
                showCustomDialog();
                // checkDrawer();
                break;
        }
    }


    private void showCustomDialog(){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.logout_out_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);



        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void checkDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
