package com.example.ctest2;

import android.app.NotificationManager;
import android.content.Intent;
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
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Application extends android.app.Application implements CTPushNotificationListener {
    @Override
    public void onCreate() {
     CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"biswa2","biswa2","biswa2", NotificationManager.IMPORTANCE_MAX,true,"sound1.mp3");
       //CleverTapAPI.createNotificationChannel(getApplicationContext(),"biswa1","biswa1","biswa1", NotificationManager.IMPORTANCE_MAX,true,"sound2.mp3");
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"nutrabay_push", "Nutrabay Push","Notification related to app", 5, true) ;
        // possible values are PushConstants.ALL_DEVICES, PushConstants.XIAOMI_MIUI_DEVICES,PushConstants.NO_DEVICES
// default is PushConstants.ALL_DEVICES
       CleverTapAPI.enableXiaomiPushOn(PushConstants.ALL_DEVICES);
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
        Log.d("afterclick", "afterclick in application: "+extras);
        Log.d("afterclick","in application Id: "+extras.get("ID"));

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key",extras);
        startActivity(intent);
        //  CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());




    }
}
