package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class DeepLinkingApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.development.mycolive");
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {

        }
    }
}
