package com.example.admin.services.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.admin.services.Services.action.FOO";
    public static final String ACTION_BAZ = "com.example.admin.services.Services.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.admin.services.Services.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.admin.services.Services.extra.PARAM2";
public static final String TAG=MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        Log.d(TAG, "onHandleIntent: Thread: "+Thread.currentThread().getName());
        Log.d(TAG, "onHandleIntent: Task starting");
        for (int i = 0; i <4 ; i++) {
            try {
                Thread.sleep(500);
                Log.d(TAG, "onHandleIntent: "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.d(TAG, "onHandleIntent: Task complete");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
