package com.development.mycolive.views.activity;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityShowHomeScreenBinding;
import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.logout.LogoutApiResponse;
import com.development.mycolive.model.logout.LogoutRequestModel;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.changePassword.ChangePassword;
import com.development.mycolive.views.activity.changePassword.ChangePasswordViewModel;
import com.development.mycolive.views.activity.myCommunity.MyCommunity;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.views.activity.testimonial.Testimonials;
import com.development.mycolive.views.fragment.booking.BookingFragment;
import com.development.mycolive.views.fragment.communities.Communities;
import com.development.mycolive.views.fragment.homeFragment.Home;
import com.development.mycolive.views.fragment.profile.ProfileScreenOne;
import com.development.mycolive.views.fragment.filterSearch.Search;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ShowHomeScreen extends BaseActivity implements View.OnClickListener , GoogleApiClient.OnConnectionFailedListener
        /*implements NavigationView.OnNavigationItemSelectedListener*/ {
    public ActivityShowHomeScreenBinding screenBinding;
    ChangePasswordViewModel viewModel;
    String user_id, token,type,login_type;
    // Session Manager Class
    SessionManager session;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

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
        screenBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_home_screen);
        screenBinding.appBar.titleTxt.setText(getResources().getString(R.string.home_screen));
        initializeView();
        loadFragment(new Home());
    }


    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initializeView() {
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Session class instance
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        setSupportActionBar(screenBinding.appBar.toolbar);

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, screenBinding.drawerLayout, screenBinding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        getUserDetail();

    }

    private void setClickListener() {
        //  screenBinding.navView.setNavigationItemSelectedListener(this);
        screenBinding.appBar.homeScreen.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        screenBinding.customNavigationDrawer.favouriteLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.testimonialLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.passwordLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.myCommunityLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.contactLayout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.logout.setOnClickListener(this);
        screenBinding.customNavigationDrawer.aboutUs.setOnClickListener(this);
        screenBinding.appBar.notification.setOnClickListener(this);
        screenBinding.customNavigationDrawer.logout.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.favourite_layout:
                startActivity(new Intent(ShowHomeScreen.this, MyFavourite.class));
                // checkDrawer();
                break;

            case R.id.testimonial_layout:
                startActivity(new Intent(this, Testimonials.class));
                // checkDrawer();
                break;

            case R.id.password_layout:
                startActivity(new Intent(this, ChangePassword.class));
                //  checkDrawer();
                break;

            case R.id.contact_layout:
                startActivity(new Intent(this, ContactUs.class));
                // checkDrawer();
                break;

            case R.id.about_us:
                startActivity(new Intent(this, AboutUs.class));
                // checkDrawer();
                break;

            case R.id.notification:
                startActivity(new Intent(this, Notification.class));
                // checkDrawer();
                break;

            case R.id.myCommunity_layout:
                startActivity(new Intent(this, MyCommunity.class));
                // checkDrawer();
                break;

            case R.id.logout:
                showCustomDialog();
                // checkDrawer();
                break;
        }
    }


    private void showCustomDialog() {
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
        Button button_ok = (Button) dialogView.findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLogout();

              //  session.logoutUser();
                // Toast.makeText(ShowHomeScreen.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        Button cancel = (Button) dialogView.findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


      /*  cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alertDialog.dismiss();
            }
        });*/
    }

    private void checkDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getUserDetail() {
        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        user_id = user.get(SessionManager.KEY_USERID);
        type = user.get(SessionManager.KEY_USERTYPE);
        token = user.get(SessionManager.KEY_TOKEN);
        login_type = user.get(SessionManager.KEY_LOGIN_TYPE);


        screenBinding.headerLayout.name.setText(name);
        screenBinding.headerLayout.email.setText(email);
        Picasso.get()
                .load(image)
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(screenBinding.headerLayout.profileImage);
    }


    private void checkLogout() {

        if(login_type.equalsIgnoreCase(ApiConstant.NORMAL)){
            showProgressDialog(getResources().getString(R.string.loading));

            Map<String, String> headers = new HashMap<>();
            headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
            headers.put(ApiConstant.AUTHENTICAT_TOKEN, token);

            LogoutRequestModel requestModel = new LogoutRequestModel();
            requestModel.setUser_id(user_id);

            //   passwordModel.setUser_name(email);

            viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);

            viewModel.logout(this, headers, requestModel).observe(this, new Observer<LogoutApiResponse>() {
                @Override
                public void onChanged(LogoutApiResponse apiResponse) {
                    hideProgressDialog();
                    if (apiResponse.response != null) {
                        Toast.makeText(ShowHomeScreen.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                        if (apiResponse.getResponse().getStatus() == 1) {
                            //finish();
                            session.logoutUser();
                        }

                    } else {
                        Toast.makeText(ShowHomeScreen.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if(login_type.equalsIgnoreCase(ApiConstant.GOOGLE)){
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()){
                                session.logoutUser();
                            }else{
                                Toast.makeText(getApplicationContext(),"Session not close",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else if(login_type.equalsIgnoreCase(ApiConstant.FACEBOOK)){
            LoginManager.getInstance().logOut();
            session.logoutUser();
        }


       /* */


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

