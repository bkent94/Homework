package com.example.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyReceiver myReceiver;
    public static final String ACTION_OUTSIDE="actionOutside";
    public static final String MY_KEY="myKey";
    private IntentFilter intentFilter;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvMain);
    }

    @Override
    protected void onStart() {
        super.onStart();

        intentFilter = new IntentFilter(ACTION_OUTSIDE);
        myReceiver=new MyReceiver(textView);

        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // unregisterReceiver(myReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
