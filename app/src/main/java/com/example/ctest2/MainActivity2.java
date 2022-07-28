package com.example.ctest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.clevertap.android.sdk.ActivityLifecycleCallback;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        findViewById(R.id.login).setOnClickListener(v->{
            Intent i = new Intent(MainActivity2.this,MainActivity.class);
            MainActivity2.this.startActivity(i);
        });

    }
}