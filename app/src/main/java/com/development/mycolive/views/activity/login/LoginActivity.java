package com.development.mycolive.views.activity.login;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityLoginBinding;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.MainActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.forgotPassword.ForgotPassword;
import com.development.mycolive.views.activity.SignupScreen;
import com.development.mycolive.model.loginModel.LoginApiResponse;
import com.development.mycolive.model.loginModel.LoginRequestModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.development.mycolive.constant.ApiConstant.USER_DEVICE_TYPE_VALUE;

public class LoginActivity extends BaseActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding loginBinding;
    LoginViewModel loginViewModel;
    SessionManager session;
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager callbackManager;
    private boolean isVisible= false;
    String device_token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        // Session class instance
        session = new SessionManager(getApplicationContext());

        initailzeView();
        // session.checkLogin();
        setClickListener();
        // userLogin();
    }

    private void setClickListener(){
        loginBinding.btnLogin.setOnClickListener(this);
        loginBinding.forgotPassword.setOnClickListener(this);
        loginBinding.signUp.setOnClickListener(this);
        loginBinding.googleLogin.setOnClickListener(this);
        loginBinding.facebookLogin.setOnClickListener(this);
        loginBinding.passwordVisibility.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:

                if(isNetworkAvailable(LoginActivity.this)){
                    if(loginBinding.inputEmail.getText().toString().isEmpty()){
                        Toast.makeText(this, getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
                        return;
                    }else if(loginBinding.inputPassword.getText().toString().isEmpty()){
                        Toast.makeText(this, getString(R.string.password_empty), Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(loginBinding.inputEmail.getText().toString()).matches()) {

                            String user_email = loginBinding.inputEmail.getText().toString();
                            String password = loginBinding.inputPassword.getText().toString();
                            String type = ApiConstant.NORMAL;
                            String social_id = "abc1111";

                            userLogin(user_email,password,type,social_id,"");

                        }
                        else {
                            loginBinding.inputEmail.setError("Invalid Email Address");
                        }
                    }
                }else{
                    showAlertDialog(LoginActivity.this,getString(R.string.check_network));
                }

                break;

            case R.id.google_login:
                signIn();
                break;

            case R.id.password_visibility:
                if(!isVisible){
                    isVisible = true;
                    loginBinding.passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                    loginBinding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    isVisible = false;
                    loginBinding.passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                    loginBinding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;

            case R.id.facebook_login:
                // facebookSignIn();
                loginBinding.facebookButton.setVisibility(View.VISIBLE);
                break;

            case R.id.forgot_password:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.sign_up:
                Intent i =new Intent(LoginActivity.this,SignupScreen.class);
                i.putExtra("login_type",ApiConstant.NORMAL);
                i.putExtra("socail_id","abc1111");
                startActivity(i);
                break;
        }
    }

    private void userLogin(String user_email, String password, String type, String social_id,String person_name) {
        showProgressDialog(getString(R.string.loading));

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);


        LoginRequestModel requestModel = new LoginRequestModel(user_email,
                password,type,social_id);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getLoginUser(this,requestModel,  headers).observe(this, new Observer<LoginApiResponse>() {
            @Override
            public void onChanged(LoginApiResponse loginApiResponse) {
                hideProgressDialog();
                if ( loginApiResponse.getStatus_code() == 400 || loginApiResponse.getStatus_code() ==401
                        || loginApiResponse.getStatus_code() ==500) {
                    // handle error here
                    Toast.makeText(LoginActivity.this,loginApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (loginApiResponse.response !=null) {
                    //   Toast.makeText(LoginActivity.this,loginApiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    if (loginApiResponse.getResponse().getStatus() == 1) {

                        if(loginApiResponse.getResponse().getIsEmailExist() ==1){
                            String token = loginApiResponse.getResponse().getData().getAuthenticateToken();
                            String userID = loginApiResponse.getResponse().getData().getUserId();
                            String userType = loginApiResponse.getResponse().getData().getUserType();
                            String name = loginApiResponse.getResponse().getData().getName();
                            String email = loginApiResponse.getResponse().getData().getEmail();
                            String image = loginApiResponse.getResponse().getData().getImage();

                            session.createLoginSession(name,
                                    email,userID,userType,token,image,type);
                            //  LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.success));

                            Intent i = new Intent(LoginActivity.this, ShowHomeScreen.class);
                            // Closing all the Activities
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            // Staring Login Activity
                            startActivity(i);
                        }else{
                            signOut();
                            Intent i =new Intent(LoginActivity.this,SignupScreen.class);
                            i.putExtra("login_type",type);
                            i.putExtra("socail_id",social_id);
                            i.putExtra("user_name",person_name);
                            i.putExtra("user_email",user_email);
                            startActivity(i);

                        }
                    }

                } else {
                    // call failed.
                    Toast.makeText(LoginActivity.this,loginApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.e(TAG, "Error is " + e.getLocalizedMessage());
                }
            }
        });
    }

    private void initailzeView(){


        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;

        if (!loggedOut) {
            //    Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());

            //Using Graph API
            //   getUserProfile(AccessToken.getCurrentAccessToken());
        }
        callbackManager = CallbackManager.Factory.create();

        FacebookSdk.sdkInitialize(getApplicationContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestEmail()
                .requestId()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        loginBinding.facebookButton.setReadPermissions("email","public_profile");
        loginBinding.facebookButton.setLoginBehavior(LoginBehavior.WEB_ONLY);
        loginBinding.facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");
              //  Toast.makeText(LoginActivity.this,String.valueOf(AccessToken.getCurrentAccessToken()), Toast.LENGTH_SHORT).show();
                getUserProfile(AccessToken.getCurrentAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        getDeviceToken();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
           /* if(acct.getPhotoUrl() != null){
                String personPhotoUrl = acct.getPhotoUrl().toString();
            }*/

            String email = acct.getEmail();
            String socail_id = acct.getId();

            Log.e(TAG, "Name: " + personName + ", email: " + email);

            userLogin(email,"",ApiConstant.GOOGLE,socail_id,personName);

        } else {
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     //   callbackManager.onActivityResult(requestCode,resultCode,data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //      updateUI(false);
                    }
                });
    }


    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                          //  String email = object.getString("email");

                            String id = object.getString("id");
                            //     String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                            userLogin("","",ApiConstant.FACEBOOK,id,first_name+" "+last_name);
                           /* txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                            txtEmail.setText(email);
                            Picasso.with(MainActivity.this).load(image_url).into(imageView);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void getDeviceToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("mco", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        if(token!=null){
                            device_token = token;
                        }


                        // Log and toast
                        String msg = getString(R.string.fcm_token, token);
                        Log.d("mco", msg);
                      //  Toast.makeText(LoginActivity.this, device_token,Toast.LENGTH_LONG).show();
                    }
                });
    }

}
