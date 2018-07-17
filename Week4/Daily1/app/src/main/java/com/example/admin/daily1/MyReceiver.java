package com.example.admin.daily1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyReceiver extends BroadcastReceiver {
    public static final String TAG=MyReceiver.class.getSimpleName();

    RecyclerView recyclerView;
    public MyReceiver(RecyclerView recyclerView){
this.recyclerView=recyclerView;
    }

    public static final String POPULATE_RECYCLER_VIEW="populaterecyclerview";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: broadcast received");
        Bundle bundle= intent.getBundleExtra(MyIntentService.KEY);
        ArrayList<Parcelable>objectList=bundle.getParcelableArrayList(MyIntentService.KEY);
        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(objectList);
        recyclerView.setAdapter(adapter);

    }
}
