package com.example.ctest2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splashactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startActivity(new Intent(Splashactivity.this, MainActivity.class));
                finish();
            }
        }, 1000);

//        setContentView(R.layout.activity_main2);
//        findViewById(R.id.login).setOnClickListener(v->{
//            Intent i = new Intent(Splashactivity.this,MainActivity.class);
//            Splashactivity.this.startActivity(i);
       // });

    }
}