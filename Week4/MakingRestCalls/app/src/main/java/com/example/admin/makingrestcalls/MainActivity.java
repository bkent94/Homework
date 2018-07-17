package com.example.admin.makingrestcalls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.makingrestcalls.client.MyNativeCallHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onNativeHTTP(View view) {
        MyNativeCallHelper callHelper = new MyNativeCallHelper();
        callHelper.makeCall();
    }
}
