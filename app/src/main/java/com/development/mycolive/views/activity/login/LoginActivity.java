package com.development.mycolive.views.activity.login;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityLoginBinding;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.MainActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.forgotPassword.ForgotPassword;
import com.development.mycolive.views.activity.SignupScreen;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
ActivityLoginBinding loginBinding;
    LoginViewModel loginViewModel;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        // Session class instance
        session = new SessionManager(getApplicationContext());
        // session.checkLogin();
        setClickListener();
        userLogin();
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

                if(loginBinding.inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else if(loginBinding.inputPassword.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(loginBinding.inputEmail.getText().toString()).matches()) {
                        userLogin();
                    }else{
                        loginBinding.inputEmail.setError("Invalid Email Address");
                    }
                }
               // startActivity(new Intent(this, ShowHomeScreen.class));
                break;

            case R.id.forgot_password:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.sign_up:
                startActivity(new Intent(this, SignupScreen.class));
                break;
        }
    }

    private void userLogin() {
         showProgressDialog(getString(R.string.loading));
    String user_email = loginBinding.inputEmail.getText().toString();
    String password = loginBinding.inputPassword.getText().toString();
    LoginRequestModel requestModel = new LoginRequestModel(user_email,
            password,"NORMAL","abc1111");

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
                String token = loginApiResponse.getResponse().getData().getAuthenticateToken();
                String userID = loginApiResponse.getResponse().getData().getUserId();
                String userType = loginApiResponse.getResponse().getData().getUserType();
                String name = loginApiResponse.getResponse().getData().getName();
                String email = loginApiResponse.getResponse().getData().getEmail();
                String image = loginApiResponse.getResponse().getData().getImage();
                LoginActivity.this.hideProgressDialog();

                if (loginApiResponse.getResponse().getStatus() == 1) {
                    session.createLoginSession(name,
                            email,userID,userType,token,image);
                    //  LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.success));
                    startActivity(new Intent(LoginActivity.this,ShowHomeScreen.class));
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

     /*   FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("mco", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.fcm_token, token);
                        Log.d("mco", msg);
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/
}
}
