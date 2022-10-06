package com.example.ctest2;
import android.app.AlarmManager;
import android.app.Notification;

import com.clevertap.android.sdk.CTWebInterface;
import com.jakewharton.processphoenix.ProcessPhoenix;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CTInboxListener,DisplayUnitListener {
    Button createu,pushpbt,appinbox,getmsg,nativedisp,inboxcount,logout,evtbtn;
    CardView c;
    TextView  text1,titlem,msg,txtinbox;
    private FirebaseAnalytics mFirebaseAnalytics;
    EditText phone,id,email,evt;
    private String myphone,myid,myemail;
    private CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
        ActivityLifecycleCallback.register(getApplication());
        super.onCreate(savedInstanceState);
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

        Log.d("Activity", "onCreate MainActivity");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
         clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        CleverTapAPI cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this);
        CleverTapAPI.setDebugLevel(3);
        TemplateRenderer.setDebugLevel(3);

        //webview
        WebView mywebview = (WebView) findViewById(R.id.web);
        mywebview.addJavascriptInterface(new CTWebInterface(CleverTapAPI.getDefaultInstance(this)),"CleverTap");
        mywebview.getSettings().setJavaScriptEnabled(true);


        //clevertapDefaultInstance.pushFcmRegistrationId(task.getResult(),true);

        MiPushClient.registerPush(this, "2882303761520478796", "5382047861796");
        String xiaomiToken = MiPushClient.getRegId(this);
        if(clevertapDefaultInstance!= null){
            clevertapDefaultInstance.pushXiaomiRegistrationId(xiaomiToken,true);
            Log.d("1231","1231"+xiaomiToken);
        }else{
            Log.d("1231","CleverTap is NULL no token");
        }
       // Log.d("myphone", "phone: "+myphone);
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
////                        sendFCMTokenToDatabase(task.getResult());
//                       // Toast.makeText(getApplicationContext(),"Notification Token is"+task.getResult(),Toast.LENGTH_SHORT).show();
//                        Log.d("ttest", "ttest: "+task.getResult());
//                       clevertapDefaultInstance.pushFcmRegistrationId(task.getResult(),true);
//                        Log.d(	"Push Unregistered", "Push Unregistered: ");
//                    }
//                });
        TemplateRenderer.setDebugLevel(3);
        if (cleverTapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            cleverTapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            cleverTapDefaultInstance.initializeInbox();
           // cleverTapDefaultInstance.getInboxMessageCount();
           // cleverTapDefaultInstance.getAllInboxMessages();
        }
//
//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            Log.d("afterclick", "inside mainactivity" + bundle.get("key").toString());
//        }
        CleverTapAPI.getDefaultInstance(this).setDisplayUnitListener(this);



        createu = findViewById(R.id.createuser);
        pushpbt = findViewById(R.id.pushnotification);
        text1 = findViewById(R.id.textv);
        appinbox = findViewById(R.id.appinbox);
        evt = findViewById(R.id.evt);
        evtbtn = findViewById(R.id.evtbtn);
        nativedisp = findViewById(R.id.nativedisp);
        inboxcount = findViewById(R.id.inboxcount);
        txtinbox = findViewById(R.id.txtinbox);
        c=findViewById(R.id.c1);
        titlem = findViewById(R.id.titlem);
        msg = findViewById(R.id.msg);
        logout = findViewById(R.id.logout);


        appinbox.setOnClickListener(v->{
           // cleverTapDefaultInstance.showAppInbox();
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("Promotions");
            tabs.add("Offers");//We support upto 2 tabs only. Additional tabs will be ignored
            CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
            styleConfig.setFirstTabTitle("Text Tab");
           // styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
            styleConfig.setTabBackgroundColor("#FF0000");
            styleConfig.setSelectedTabIndicatorColor("#0000FF");
            styleConfig.setSelectedTabColor("#0000FF");
            styleConfig.setUnselectedTabColor("#FFFFFF");
            styleConfig.setBackButtonColor("#000014");
            styleConfig.setNavBarTitleColor("#000014");
            styleConfig.setNavBarTitle("App Inbox");
            styleConfig.setNavBarColor("#FFFFFF"); // WHITE
            styleConfig.setInboxBackgroundColor("#ADD8E6");
            if (cleverTapDefaultInstance != null) {
                cleverTapDefaultInstance.showAppInbox(styleConfig);
            }
        });

//        getmsg.setOnClickListener(v -> {
//
////            Bundle params = new Bundle();
////            String v1="v1";
////            String evt="evt";
////            params.putString(v1,"data1");
////            mFirebaseAnalytics.logEvent(evt,params);
//
//          //  HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
//
//            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//            Date date = new Date();
//            String time=dateFormat.format(date);
//            profileUpdate.put("DATE",time);
//            Log.d("mytime", "mytime: "+time);
//            clevertapDefaultInstance.pushProfile(profileUpdate);
//
//            clevertapDefaultInstance.pushEvent("notify",profileUpdate);
//
////            clevertapDefaultInstance.pushEvent("notify",profileUpdate);
//
//        });
       pushpbt.setOnClickListener(v -> {
           clevertapDefaultInstance.pushEvent("biswapushbut");
           clevertapDefaultInstance.removeValueForKey("MYgender");
       });

        evtbtn.setOnClickListener(v->{

            clevertapDefaultInstance.pushEvent(evt.getText().toString());

        });

       nativedisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clevertapDefaultInstance.pushEvent("biswanative");
                }
        });

        inboxcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              int ct= cleverTapDefaultInstance.getInboxMessageCount(); // to count total inbox msg
////              Log.d("Count","Count"+cleverTapDefaultInstance.getInboxMessageCount());
//               //  text1.setText(ct);
//                String ctr= String.valueOf(ct);
//                txtinbox.setText("Your inbox message count is "+ctr);

                //Log.d("Count","data: "+cleverTapDefaultInstance.getAllInboxMessages());

        mywebview.loadUrl("https://biswaemailunsub.000webhostapp.com/");
            }
        });


        createu.setOnClickListener(view -> {

            phone = findViewById(R.id.phone);
            id = findViewById(R.id.id);
            email = findViewById(R.id.email);
            myphone = phone.getText().toString();
            myid = id.getText().toString();
            myemail = email.getText().toString();
            Log.d("MYPHONE", "MYPHONE: "+myphone);
            Log.d("id", "id: "+myid);
            Log.d("email", "email: "+myemail);
            // each of the below mentioned fields are optional
            //HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Name", "biswa3");    // String
            // String or number
            profileUpdate.put("Email", myemail); // Email address of the user
            profileUpdate.put("Phone", myphone);   // Phone (with the country code, starting with +)
            profileUpdate.put("Identity", myid);
            profileUpdate.put("Gender", "M");             // Can be either M or F

            profileUpdate.put("DOB", new Date());            // Date of Birth. Set the Date object to the appropriate value first
            // optional fields. controls whether the user will be sent email, push etc.

            profileUpdate.put("MSG-email", true);        // Disable email notifications
            profileUpdate.put("MSG-push", true);          // Enable push notifications
            profileUpdate.put("MSG-sms", true);          // Disable SMS notifications
            profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications
           //ArrayList<String> stuff = new ArrayList<String>();
            //stuff.add("https://picsum.photos/");
            //stuff.add("shoes");
            //profileUpdate.put("MyStuff7", stuff);                        //ArrayList of Strings


            clevertapDefaultInstance.pushEvent("ONUSER LOGIN on BTN",profileUpdate);

            clevertapDefaultInstance.onUserLogin(profileUpdate);

            text1.setText("User Created");
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  ProcessPhoenix.triggerRebirth(getApplicationContext());

                try {

                    // clearing app data
                   String packageName = getApplicationContext().getPackageName();
                  Runtime runtime = Runtime.getRuntime();
                 runtime.exec("pm clear "+packageName);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        });
    }



    @Override
    protected void onDestroy() {

        Toast.makeText(this, "onDestroy MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onDestroy MainActivity");
        super.onDestroy();

    }
    @Override
    protected void onStop() {

        Toast.makeText(this, "onStop MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onStop MainActivity");

        super.onStop();

    }
    @Override
    protected void onPause() {

        Toast.makeText(this, "onPause MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onPause MainActivity");

        super.onPause();
    }
    @Override
    protected void onResume() {

        Toast.makeText(this, "onResume MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onResume MainActivity");
        super.onResume();

    }
    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        for (int i = 0; i <units.size() ; i++) {
            CleverTapDisplayUnit unit = units.get(i);
            prepareDisplayView(unit);
        }
    }

    private void prepareDisplayView(CleverTapDisplayUnit unit) {
        for (CleverTapDisplayUnitContent i:unit.getContents()) {
            titlem.setText(i.getTitle());
            msg.setText(i.getMessage());

            //Notification Viewed Event
            CleverTapAPI.getDefaultInstance(this).pushDisplayUnitViewedEventForID(unit.getUnitID());

            //Notification Clicked Event
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CleverTapAPI.getDefaultInstance(getApplicationContext()).pushDisplayUnitClickedEventForID(unit.getUnitID());

                }
            });
        }
    }

    @Override
    public void inboxDidInitialize() {


    }

    @Override
    public void inboxMessagesDidUpdate() {

    }


}