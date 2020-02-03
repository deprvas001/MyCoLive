package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewClient;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityMapScreenBinding;

public class MapScreen extends AppCompatActivity implements View.OnClickListener {
ActivityMapScreenBinding mapScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_map_screen);
       /* mapScreenBinding.toolbar.setTitle(getString(R.string.map_screen));
        setSupportActionBar(mapScreenBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

       mapScreenBinding.back.setOnClickListener(this);

        setWebView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setWebView(){
        mapScreenBinding.mapView.setWebViewClient(new WebViewClient());
        mapScreenBinding.mapView.getSettings().setJavaScriptEnabled(true);
        mapScreenBinding.mapView.loadUrl("http://maps.google.com/maps" /*+ "saddr=43.0054446,-87.9678884" + "&daddr=42.9257104,-88.0508355"*/);
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
