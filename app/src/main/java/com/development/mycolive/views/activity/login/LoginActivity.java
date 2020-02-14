package com.development.mycolive.views.activity.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityLoginBinding;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.forgotPassword.ForgotPassword;
import com.development.mycolive.views.activity.SignupScreen;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
ActivityLoginBinding loginBinding;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        setClickListener();
    }

    private void setClickListener(){
        loginBinding.btnLogin.setOnClickListener(this);
        loginBinding.forgotPassword.setOnClickListener(this);
        loginBinding.signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
             //   userLogin("","");
                startActivity(new Intent(this, ShowHomeScreen.class));
                break;

            case R.id.forgot_password:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.sign_up:
                startActivity(new Intent(this, SignupScreen.class));
                break;
        }
    }

    private void userLogin(String username, String password) {
         showProgressDialog(getString(R.string.loading));

         LoginRequestModel requestModel = new LoginRequestModel("abc@yopmail.com",
                 "123456","NORMAL","abc1111");

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getLoginUser(this,requestModel).observe(this, new Observer<LoginApiResponse>() {
            @Override
            public void onChanged(LoginApiResponse loginApiResponse) {
                if (loginApiResponse.getError() == null && loginApiResponse.getStatus() == 400) {
                    // handle error here
                    LoginActivity.this.hideProgressDialog();
                    LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.invalid_credentails));
                    //   Toast.makeText(this, getString(R.string.invalid_credentails), Toast.LENGTH_SHORT).show();
                } else if (loginApiResponse.getError() == null) {
                    LoginActivity.this.hideProgressDialog();
                    if (loginApiResponse.getResponse().getStatus() == 1) {
                        LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.success));
                    }
                } else {
                    // call failed.
                    LoginActivity.this.hideProgressDialog();
                    Throwable e = loginApiResponse.getError();
                    Toast.makeText(LoginActivity.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.e(TAG, "Error is " + e.getLocalizedMessage());
                }
            }
        });
    }
}
