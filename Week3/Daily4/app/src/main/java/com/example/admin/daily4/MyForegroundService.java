package com.example.admin.daily4;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;


public class MyForegroundService extends Service {
    public static final String TAG=MyForegroundService.class.getSimpleName();
    private static final String CHANNEL_ID ="My music player" ;
    private int notificationId=1122;
    public static final String NOTIFICATION_KEY="myNotificationKey";

    private RemoteViews remoteViews;
    private ImageButton imageButton;
    private MediaPlayer mediaPlayer;

    public MyForegroundService() {



    }

    @Override
    public void onCreate() {





        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

   
   
   
   
   

   
   

   


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        switch (intent.getStringExtra("ACTION")) {
            case "start":
            mediaPlayer = MediaPlayer.create(this, R.raw.official_national_anthem);


            mediaPlayer.setLooping(true);
            //     mediaPlayer.start();

            StartNotificationMediaPlayer();


            case "playpause":
                PlayPause();

        }


        return START_STICKY;
    }





    @Override
    public void onDestroy() {
        mediaPlayer.stop();

        super.onDestroy();
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


        Intent playPauseIntent=new Intent(this,MyForegroundService.class);
        playPauseIntent.putExtra("ACTION","playpause");

        PendingIntent playMediaPending = PendingIntent.getService(this,0,playPauseIntent,0);



        remoteViews.setOnClickPendingIntent(R.id.play_pause_notification_btn,playMediaPending);

        //Set up notification
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,CHANNEL_ID);
        notification=   notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("MusicPlayer")
                .setContentText("Now playing song over the horizon").setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(openMainActivity).setCustomBigContentView(remoteViews).build();

        //Show notification
        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(notificationId,notification);




        return  notification;
    }

    private void PlayPause(){


        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

        else{
            mediaPlayer.start();
        }




    }



           }













