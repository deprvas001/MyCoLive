package com.development.mycolive.views.activity.changePassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityChangePasswordBinding;
import com.development.mycolive.model.ChangePasswordModel;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.testimonial.TestimonailViewModel;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends BaseActivity implements View.OnClickListener {
ActivityChangePasswordBinding passwordBinding;
    SessionManager session;
    String id="";
    String token="";
    String email="";
    ChangePasswordViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passwordBinding = DataBindingUtil.setContentView(this,R.layout.activity_change_password);
        /*passwordBinding.toolbar.setTitle(getString(R.string.change_password));
        setSupportActionBar(passwordBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        getSession();
        setClickListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickListener(){
        passwordBinding.back.setOnClickListener(this);
        passwordBinding.btnReset.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.btn_reset:
                if(passwordBinding.inputOld.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter Old Password", Toast.LENGTH_SHORT).show();
                    return;
                }
               else if(passwordBinding.inputNew.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter New Password", Toast.LENGTH_SHORT).show();
                  return;
                }else if(passwordBinding.inputReenter.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please ReConfirm New Password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!passwordBinding.inputReenter.getText().toString().equals(passwordBinding.inputNew.getText().toString())){
                    Toast.makeText(this, "Please New Password And ReConfirm should be match.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    changePassword();
                }

                break;
        }
    }

    private void getSession(){

        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        //    id = user.get(SessionManager.KEY_USERID);
        token = user.get(SessionManager.KEY_TOKEN);


        //getCommunity("TESTIMONIAL",token);

        //  getCommunity("ALL");

        //   getBooking(token);
    }

    private void changePassword(){
        showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        ChangePasswordModel passwordModel = new ChangePasswordModel();
        passwordModel.setNewPassword(passwordBinding.inputNew.getText().toString());
        passwordModel.setOldPassword(passwordBinding.inputOld.getText().toString());
        passwordModel.setConfirmPassword(passwordBinding.inputReenter.getText().toString());
     //   passwordModel.setUser_name(email);

        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);

        viewModel.changePassword(this,headers,passwordModel).observe(this, new Observer<PostApiResponse>() {
            @Override
            public void onChanged(PostApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    Toast.makeText(ChangePassword.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    if(apiResponse.getResponse().getStatus() == 1){
                        finish();
                    }

                }else {
                    Toast.makeText(ChangePassword.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }
}
