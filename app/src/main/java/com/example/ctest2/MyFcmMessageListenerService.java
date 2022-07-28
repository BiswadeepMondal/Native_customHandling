package com.example.ctest2;

import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.LaunchPendingIntentFactory;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;
//import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

public class MyFcmMessageListenerService extends FirebaseMessagingService {

@Override
public void onNewToken(String token1)
{
    super.onNewToken(token1);
    Log.d("MY_TOKEN", "Refreshed token: " + token1);
}


    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        Bundle extras = new Bundle();
        if (message.getData().size() > 0)
        {
            for (Map.Entry<String, String> entry : message.getData().entrySet())
            {
                extras.putString(entry.getKey(), entry.getValue());
            }
        }
            NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);
            boolean flag=info.fromCleverTap;

            if (!flag) {
// if payload from Firebse
                Log.d("FCM data", "FCM data: " + new Gson().toJson(message));   // to print payload
                Log.d("img", "img: " + message.getNotification().getImageUrl());
                Log.d("title", "title = [" + message.getNotification().getTitle() + "]");
                Log.d("message", "message = [" + message.getNotification().getBody() + "]");

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                int notificationId = new Random().nextInt(60000);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "biswa2")

                        .setSmallIcon(R.drawable.ic_baseline_share_24)  //a resource for your custom small icon
                        .setColor(Color.YELLOW) //small ic6on bg color
                        .setContentTitle(message.getNotification().getTitle()) //the "title" value you sent in your notification
                        .setContentText(message.getNotification().getBody()) //ditto
                        .setAutoCancel(true);  //dismisses the notification on click

                notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
            }
            else
            {
                // if payload from clevertap
               CleverTapAPI.getDefaultInstance(this).pushNotificationViewedEvent(extras);
                Log.d("CT data", "CT raw: " + message);
               Log.d("CT data", "CT json: " + new Gson().toJson(message));   // to print payload
           boolean status=new CTFcmMessageHandler().createNotification(getApplicationContext(), message);
                Log.d("EXTRAS", "EXTRAS: "+extras);
           CleverTapAPI.getDefaultInstance(this).pushNotificationViewedEvent(extras);
        //  NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // custom rendering
             //   int notificationId = new Random().nextInt(60000);
//                Intent intent = new Intent();
//
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(extras.getString("wzrk_dl")));
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtras(extras);
               // PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
//                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "biswa2")
//
//                        .setSmallIcon(R.drawable.ic_baseline_share_24)  //a resource for your custom small icon
//                        .setColor(Color.YELLOW) //small ic6on bg color
//                        .setContentTitle(extras.getString("nm")) //the "title" value you sent in your notification
//                        .setContentText(extras.getString("nt")) //ditto
//                       // .setContentIntent(pendingIntent)
//                        .setAutoCancel(true);  //dismisses the notification on click
//
//                notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
             }
        }

}