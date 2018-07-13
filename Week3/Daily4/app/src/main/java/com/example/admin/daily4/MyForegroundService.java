package com.example.admin.daily4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.MediaController;

import java.io.IOException;
import java.util.List;


public class MyForegroundService extends MediaBrowserServiceCompat {
    public static final String TAG=MyForegroundService.class.getSimpleName();
    private static final String CHANNEL_ID ="My music player" ;
    private int notificationId=1122;
    private final MediaPlayer mediaPlayer;

    public MyForegroundService() {
        mediaPlayer = new MediaPlayer();

    }

    @Override
    public void onCreate() {
        createNotificationChannel();
        try {
            mediaPlayer.setDataSource("insert data source here");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String s, int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String s, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



            startForeground(Notification.FLAG_ONGOING_EVENT,StartNotificationMediaPlayer());

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification StartNotificationMediaPlayer()  {
        Notification notification;

        //Create media session compat

        MediaSessionCompat mediaSessionCompat =new MediaSessionCompat(this,TAG);
mediaSessionCompat.setCallback(new MySessionCallBack());
MediaControllerCompat controller=mediaSessionCompat.getController();
        MediaMetadataCompat mediaMetadataCompat= controller.getMetadata();
        MediaDescriptionCompat descriptionCompat=mediaMetadataCompat.getDescription();


        //Set up intent to open activity
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent openMainActivity = PendingIntent.getActivity(this,0,intent,0);


        //Create an action

        PendingIntent playMediaPending = MediaButtonReceiver.buildMediaButtonPendingIntent(this, PlaybackStateCompat.ACTION_PLAY_PAUSE);
        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_launcher_foreground,"Play music",playMediaPending);


        //Set up notification
     NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,CHANNEL_ID);
    notification=   notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("MusicPlayer")
               .setContentText("Now playing song over the horizon").setPriority(NotificationCompat.PRIORITY_HIGH)
       .setContentIntent(openMainActivity).addAction(action).build();

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


    private class MySessionCallBack extends MediaSessionCompat.Callback{


        @Override
        public void onPlay() {

            super.onPlay();
        }
    }
}
