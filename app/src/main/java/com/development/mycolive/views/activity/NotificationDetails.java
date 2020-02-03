package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityNotificationBinding;
import com.development.mycolive.databinding.ActivityNotificationDetailsBinding;

public class NotificationDetails extends AppCompatActivity implements View.OnClickListener {
ActivityNotificationDetailsBinding detailsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_notification_details);

        detailsBinding.btnViewProperty.setOnClickListener(this);
        detailsBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_view_property:
                startActivity(new Intent(NotificationDetails.this,RoomDetail.class));
                break;

            case R.id.back:
                finish();
                break;
        }
    }
}
