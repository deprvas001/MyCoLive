package com.development.mycolive.views.activity.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityNotificationBinding;
import com.development.mycolive.adapter.NotificationAdapter;
import com.development.mycolive.model.NotificationModel;
import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.notificationModel.NotificationApiResponse;
import com.development.mycolive.model.notificationModel.NotificationBodyRequest;
import com.development.mycolive.model.notificationModel.NotificationData;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryViewModel;
import com.development.mycolive.views.activity.bookingHistory.CurrentBookingHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notification extends BaseActivity implements View.OnClickListener {
ActivityNotificationBinding notificationBinding;
    private NotificationAdapter notificationAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<NotificationModel> notificationList = new ArrayList<>();
    SessionManager session;
    String token="";
    String user_id;
    NotificationViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationBinding = DataBindingUtil.setContentView(this,R.layout.activity_notification);
      /*  notificationBinding.toolbar.setTitle(getString(R.string.notification));
        setSupportActionBar(notificationBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        setClickListener();
        getSession();

    }

    private void setRecyclerView(List<NotificationData> notificationDataList) {
        notificationAdapter = new NotificationAdapter(Notification.this, notificationDataList);
        mLayoutManager = new LinearLayoutManager(Notification.this);
        notificationBinding.recyclerView.setLayoutManager(mLayoutManager);
        notificationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationBinding.recyclerView.setAdapter(notificationAdapter);

    }

    /*private void settestimonialList(){
        notificationList.clear();
        for (int i=0;i<4;i++){
            NotificationModel notificationModel = new NotificationModel("",
                    "");
            notificationList.add(notificationModel);
        }
        notificationAdapter.notifyDataSetChanged();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickListener(){
        notificationBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
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
        token = user.get(SessionManager.KEY_TOKEN);
        user_id = user.get(SessionManager.KEY_USERID);


        getNotifiation(token,user_id);
    }


    private void getNotifiation(String token,String user_id){
        showProgressDialog(getString(R.string.loading));

        NotificationBodyRequest bodyRequest = new NotificationBodyRequest();
        bodyRequest.setUser_id(user_id);

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        viewModel.getNotificationList(this,headers,bodyRequest).observe(this, new Observer<NotificationApiResponse>() {
            @Override
            public void onChanged(NotificationApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.getError() == null && apiResponse.getStatus() == 400) {
                    // handle error here
                } else if (apiResponse.getError() == null ) {

                    setRecyclerView(apiResponse.getResponse().getData());
                } else {
                    // call failed.
                    Throwable e = apiResponse.getError();
                    Toast.makeText(Notification.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.e(TAG, "Error is " + e.getLocalizedMessage());
                }
            }

        });
    }

}
