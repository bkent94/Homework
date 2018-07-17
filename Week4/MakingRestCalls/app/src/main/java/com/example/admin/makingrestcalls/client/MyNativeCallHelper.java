package com.example.admin.makingrestcalls.client;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MyNativeCallHelper {
    HttpURLConnection httpURLConnection;
    public static final String BaseUrl="http://www.mocky.io/v2/5b4cc67331000064005eba27";
    public static final String TAG=MyNativeCallHelper.class.getSimpleName();

    public void makeCall(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url =new URL(BaseUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream stream= httpURLConnection.getInputStream();
                    Scanner scanner=new Scanner(stream);
                    while(scanner.hasNext()){
                        Log.d(TAG, "run: ");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
