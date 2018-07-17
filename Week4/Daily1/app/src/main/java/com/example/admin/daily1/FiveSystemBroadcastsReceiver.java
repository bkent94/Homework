package com.example.admin.daily1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FiveSystemBroadcastsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      switch(intent.getAction()){
          case Intent.ACTION_AIRPLANE_MODE_CHANGED:
              Toast.makeText(context, String.valueOf(intent.getBooleanExtra("state",false)), Toast.LENGTH_SHORT).show();
              break;

          case Intent.ACTION_BATTERY_LOW:
              Toast.makeText(context, "Warning: your battery is low", Toast.LENGTH_SHORT).show();
              break;

          case Intent.ACTION_BATTERY_OKAY:
              Toast.makeText(context, "Don't worry your battery is ok", Toast.LENGTH_SHORT).show();
              break;
          case Intent.ACTION_HEADSET_PLUG:
              Toast.makeText(context, "Your headset was succesfully plugged in", Toast.LENGTH_SHORT).show();
              break;
          case Intent.ACTION_LOCALE_CHANGED:
              Toast.makeText(context, "Your Locale has been changed", Toast.LENGTH_SHORT).show();
              break;
      }
    }
}
