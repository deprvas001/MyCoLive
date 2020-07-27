package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivitySignupScreenBinding;
import com.development.mycolive.views.fragment.signUp.SignUpOne;

public class SignupScreen extends BaseActivity {
    ActivitySignupScreenBinding screenBinding;
    public String type = "", social_id = "", user_name,user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup_screen);
        if (getIntent() != null) {
            type = getIntent().getExtras().getString("login_type");
            social_id = getIntent().getExtras().getString("socail_id");

            if (getIntent().getExtras().containsKey("user_name")) {
                if (getIntent().getExtras().getString("user_name") != null) {
                    user_name = getIntent().getExtras().getString("user_name");
                }
            }

            if (getIntent().getExtras().containsKey("user_email")) {
                if (getIntent().getExtras().getString("user_email") != null) {
                    user_email = getIntent().getExtras().getString("user_email");
                }
            }
        }
        loadFragment(new SignUpOne());
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
}
