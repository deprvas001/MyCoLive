package com.development.mycolive.views.activity.testimonial;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityTestimonialsBinding;
import com.development.mycolive.adapter.TestimonialAdapter;
import com.development.mycolive.model.testimonialmodel.TestimonialApiResponse;
import com.development.mycolive.model.testimonialmodel.TestimonialModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.notification.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testimonials extends BaseActivity implements View.OnClickListener {
    private TestimonialAdapter testimonialAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<TestimonialModel> testimonialList = new ArrayList<>();
    ActivityTestimonialsBinding testimonialsBinding;
    TestimonailViewModel viewModel;
    SessionManager session;
    String id="";
    String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testimonialsBinding = DataBindingUtil.setContentView(this,R.layout.activity_testimonials);

        /*testimonialsBinding.toolbar.setTitle(getString(R.string.testimonial));
        setSupportActionBar(testimonialsBinding.toolbar);
        setSupportActionBar(testimonialsBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        getSession();
        setClickListener();
    }

    private void setRecyclerView(List<TestimonialModel> testimonialDataList) {

        testimonialAdapter = new TestimonialAdapter(Testimonials.this, testimonialDataList);
        mLayoutManager = new LinearLayoutManager(Testimonials.this);
        testimonialsBinding.recyclerView.setLayoutManager(mLayoutManager);
        testimonialsBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        testimonialsBinding.recyclerView.setAdapter(testimonialAdapter);

    }


    private void getCommunity(String id,String token){

        showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(TestimonailViewModel.class);

        viewModel.getTestimonial(this,headers,id).observe(this, new Observer<TestimonialApiResponse>() {
            @Override
            public void onChanged(TestimonialApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){

                    setRecyclerView(apiResponse.getResponse().getData().getTestimonial());
                }
                else if(apiResponse.getStatus()== 401){
                }
                else{
                }
             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }


  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

  private void setClickListener(){
      testimonialsBinding.back.setOnClickListener(this);
      testimonialsBinding.notification.setOnClickListener(this);
  }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.notification:
                startActivity(new Intent(this, Notification.class));
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
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        //    id = user.get(SessionManager.KEY_USERID);
        token = user.get(SessionManager.KEY_TOKEN);
        getCommunity("TESTIMONIAL",token);

        //  getCommunity("ALL");

        //   getBooking(token);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
