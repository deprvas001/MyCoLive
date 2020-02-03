package com.development.mycolive.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityHomeScreenBinding;
import com.development.mycolive.views.fragment.Home;
import com.development.mycolive.views.fragment.ProfileScreenOne;
import com.development.mycolive.views.fragment.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity  {
ActivityHomeScreenBinding screenBinding;
    ActionBarDrawerToggle toggle;

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

                case R.id.account:
                    Toast.makeText(HomeScreen.this, "My Account",Toast.LENGTH_SHORT).show();
                    //  screenBinding.toolbar.toolbarText.setText(R.string.title_home);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        screenBinding = DataBindingUtil.setContentView(this,R.layout.activity_home_screen);
        initializeView();

        loadFragment(new Home());
    }

    private void initializeView(){
        screenBinding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toggle = new ActionBarDrawerToggle(this,screenBinding.drawerLayout,R.string.Open,R.string.Close);
        screenBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        screenBinding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(HomeScreen.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(HomeScreen.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(HomeScreen.this, "My Cart",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
