package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityContactUsBinding;

public class ContactUs extends AppCompatActivity  implements View.OnClickListener {
ActivityContactUsBinding contactUsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactUsBinding = DataBindingUtil.setContentView(this,R.layout.activity_contact_us);
       /* contactUsBinding.toolbar.setTitle(getString(R.string.contact_us));
        setSupportActionBar(contactUsBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        setClickListener();
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
        WebSettings webSettings = contactUsBinding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        contactUsBinding.webview.loadUrl("https://www.google.com");
    }

    private void setClickListener(){
        contactUsBinding.back.setOnClickListener(this);
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
