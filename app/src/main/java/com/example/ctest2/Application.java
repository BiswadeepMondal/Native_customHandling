package com.example.ctest2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
//import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Application extends android.app.Application implements CTPushNotificationListener {
    @Override
    public void onCreate() {
     CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());

       CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
       cleverTapAPI.setCTPushNotificationListener(this);
//h
   ActivityLifecycleCallback.register(this);




        TemplateRenderer.setDebugLevel(3);
  CleverTapAPI.setDebugLevel(3);

    super.onCreate();


    }


    @Override
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> extras) {
        Log.d("afterclick", "afterclick: "+extras);
        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());




    }
}
