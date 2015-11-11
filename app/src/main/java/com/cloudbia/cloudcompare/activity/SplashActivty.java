package com.cloudbia.cloudcompare.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cloudbia.cloudcompare.R;
import com.cloudbia.cloudcompare.contentProvider.CustomAuthenticationListener;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;

import java.net.MalformedURLException;

public class SplashActivty extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);
//        try {
//            BMSClient.getInstance().initialize(this, "<APPLICATION_ROUTE>", "<APPLICATION_ID>");
//            BMSClient.getInstance().;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivty.this, StartActivity.class);
                startActivity(i);

                // initialize SDK with IBM Bluemix application ID and route
                // close this activity
                finish();
            }
        }, 4000);
    }


}
