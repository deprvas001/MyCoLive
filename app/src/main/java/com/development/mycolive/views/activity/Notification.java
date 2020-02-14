package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityNotificationBinding;
import com.development.mycolive.adapter.NotificationAdapter;
import com.development.mycolive.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity implements View.OnClickListener {
ActivityNotificationBinding notificationBinding;
    private NotificationAdapter notificationAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<NotificationModel> notificationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationBinding = DataBindingUtil.setContentView(this,R.layout.activity_notification);
      /*  notificationBinding.toolbar.setTitle(getString(R.string.notification));
        setSupportActionBar(notificationBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        setClickListener();
        setRecyclerView();
        settestimonialList();

    }

    private void setRecyclerView() {
        notificationAdapter = new NotificationAdapter(Notification.this, notificationList);
        mLayoutManager = new LinearLayoutManager(Notification.this);
        notificationBinding.recyclerView.setLayoutManager(mLayoutManager);
        notificationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationBinding.recyclerView.setAdapter(notificationAdapter);

    }

    private void settestimonialList(){
        notificationList.clear();
        for (int i=0;i<4;i++){
            NotificationModel notificationModel = new NotificationModel("",
                    "");
            notificationList.add(notificationModel);
        }
        notificationAdapter.notifyDataSetChanged();
    }

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
}
