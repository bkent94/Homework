package com.example.admin.services.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    public static final String TAG=MyBoundService.class.getSimpleName();
    IBinder iBinder=new MyBinder();

    public MyBoundService() {
     
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    public IBinder getiBinder() {
        Log.d(TAG, "getiBinder: ");
        return iBinder;
    }

    public String getDataFromServer(){
        Log.d(TAG, "getDataFromServer: ");
        return "This is data from the server";
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /**
     * Binder class to communicate with clients
     */
    public class  MyBinder extends Binder{
        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }
}
