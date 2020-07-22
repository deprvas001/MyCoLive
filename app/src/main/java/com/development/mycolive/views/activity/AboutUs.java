package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityAboutUsBinding;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
ActivityAboutUsBinding usBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usBinding = DataBindingUtil.setContentView(this,R.layout.activity_about_us);
        usBinding.back.setOnClickListener(this);
        WebSettings webSettings = usBinding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        usBinding.webview.loadUrl(" https://mycolive.com/landing/aboutUs");

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
