package com.example.admin.daily4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private Button playpausebutton;
    private MediaPlayer mediaPlayer;
    private RemoteViews remoteViews;

    private static final String CHANNEL_ID ="My music player" ;
    private int notificationId=1122;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        playpausebutton = findViewById(R.id.play_pause_notification_btn);





    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onPlayMusic(View view) {


        Intent intent = new Intent(this.getApplicationContext(), MyForegroundService.class);
        intent.putExtra("ACTION","start");

        startService(intent);
     //  notification= StartNotificationMediaPlayer();





    }

    public void onPlayPause(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification StartNotificationMediaPlayer()  {
        Notification notification;












        //Set up intent to open activity
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent openMainActivity = PendingIntent.getActivity(this,0,intent,0);


        //set up RemoteView

        remoteViews = new RemoteViews(getPackageName(), R.layout.foreground_notification);


        Intent playMediaIntent =new Intent(this,MyForegroundService.class);
        playMediaIntent.putExtra(CHANNEL_ID, R.raw.official_national_anthem);
        PendingIntent playMediaPending = PendingIntent.getService(this.getApplicationContext(),1,playMediaIntent,0);


        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_launcher_foreground,"Play music",playMediaPending);
        remoteViews.setOnClickPendingIntent(R.id.play_pause_notification_btn,playMediaPending);

        //Set up notification
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,CHANNEL_ID);
        notification=   notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("MusicPlayer")
                .setContentText("Now playing song over the horizon").setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(openMainActivity).setCustomBigContentView(remoteViews).addAction(action).build();

        //Show notification
        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notificationId,notification);




        return  notification;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
