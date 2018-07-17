package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyReceiver extends BroadcastReceiver {
TextView textView;

    public MyReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
textView.setText(intent.getStringExtra(MainActivity.MY_KEY));
    }
}
