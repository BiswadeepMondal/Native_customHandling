package com.example.ctest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.google.gson.Gson;

import java.io.Serializable;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
      //  Serializable hashMap;
        Bundle bundle = this.getIntent().getExtras();
//                Log.d("afterclick","afterclick: "+ bundle);
//        Log.d("afterclick","afterclick: "+  new Gson().toJson(bundle));
//            hashMap = bundle.getSerializable("HashMap");
//        Log.d("afterclick","afterclick: "+ hashMap);
        Log.d("afterclick",bundle.get("key").toString());
//        for (String key : bundle.keySet()) {
//            Object value = bundle.get(key);
//            Log.d("afterclick", String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
//        }
        findViewById(R.id.login).setOnClickListener(v->{
            Intent i = new Intent(MainActivity2.this,MainActivity.class);
            MainActivity2.this.startActivity(i);
        });

    }
//    @Override
//    protected void onNewIntent(Intent intent)
//    {
//        super.onNewIntent(intent);
//        Log.d("afterclick","afterclick:");
//
////        Log.d("afterclick","afterclick: "+ intent.getStringExtra("key"));
//    }
}