package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebViewClient;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityRoomDetailBinding;
import com.development.mycolive.databinding.ActivityRoomateDetailsBinding;

public class RoomateDetails extends AppCompatActivity {
ActivityRoomateDetailsBinding detailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_roomate_details);
        setWebView();

        detailBinding.toolbar.setTitle(getString(R.string.roomate_detail));
        setSupportActionBar(detailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setWebView(){
        detailBinding.webview.setWebViewClient(new WebViewClient());
        detailBinding.webview.getSettings().setJavaScriptEnabled(true);
        detailBinding.webview.loadUrl("http://maps.google.com/maps?" + "saddr=43.0054446,-87.9678884" + "&daddr=42.9257104,-88.0508355");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
