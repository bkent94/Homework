package com.example.admin.daily4.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.example.admin.daily4.adapter.RecipeListAdapter;
import com.example.admin.daily4.model.Recipe;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {
RecyclerView recyclerView;
public MyReceiver(RecyclerView recyclerView){
    this.recyclerView=recyclerView;
}
    @Override
    public void onReceive(Context context, Intent intent) {
    ArrayList<Recipe> recipes=intent.getParcelableArrayListExtra("recipes");
ArrayList<Bitmap> images= intent.getParcelableArrayListExtra("images");
recyclerView.setAdapter(new RecipeListAdapter(recipes,images));
    }
}
