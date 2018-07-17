package com.example.admin.daily1;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private FiveSystemBroadcastsReceiver fiveReceiver;

    public static final String POPULATE_RECYCLER_VIEW="populaterecyclerview";
    private MyReceiver myReceiver;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

recyclerView=findViewById(R.id.myRecyclerView);
    }


    @Override
    protected void onStart() {
        super.onStart();

        //set up broadcast receiver to use 5 system broadcasts
        fiveReceiver = new FiveSystemBroadcastsReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
        registerReceiver(fiveReceiver,intentFilter);

        //set up my receiver to know when to populate the recycler view
        myReceiver = new MyReceiver(recyclerView);
        IntentFilter intentFilter1=new IntentFilter();
        intentFilter1.addAction(Action.POPULATE_RECYCLER_VIEW);
        registerReceiver(myReceiver,intentFilter1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(this);


        //Run MyIntentService to populate recyclerview
        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);


    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(fiveReceiver);
        unregisterReceiver(myReceiver);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        String field1=((TextView)recyclerView.findViewById(R.id.field1)).getText().toString();
      NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this,"1234");
      notificationBuilder.setContentTitle(field1);

      Notification notification= notificationBuilder.build();


      AlarmManager alarmManager = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
      Intent alarmIntent=new Intent(this,AlarmReceiver.class);
      alarmIntent.putExtra("key",notification);
      PendingIntent alarmPending=PendingIntent.getBroadcast(this,0,alarmIntent,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+5*1000,alarmPending);

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    public class Action{
    public static final String POPULATE_RECYCLER_VIEW="populaterecyclerview";
}

}
