package com.development.mycolive;

import android.app.Application;

import com.stripe.android.PaymentConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_test_XK5dkhNbBEF7oM2sNk5DKcms00JZsoB55P"
        );

    }
}
