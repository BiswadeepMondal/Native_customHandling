package com.example.ctest2;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

public class Application extends android.app.Application implements CTPushNotificationListener, android.app.Application.ActivityLifecycleCallbacks {
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

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.d("dismissid", "onActivityCreated() called with: activity = [" + activity.getIntent() + "], savedInstanceState = [" + savedInstanceState + "]");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            NotificationUtils.dismissNotification(activity.getIntent(), this);
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

}
