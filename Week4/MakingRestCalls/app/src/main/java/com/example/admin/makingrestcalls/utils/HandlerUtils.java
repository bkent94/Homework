package com.example.admin.makingrestcalls.utils;

import android.os.Bundle;
import android.os.Message;

import android.os.Handler;

public class HandlerUtils {
    public static  HandlerUtils instance=null;
    private static final String STRING_KEY ="mystringkey" ;

    Handler handler;
    Handler.Callback callBack;



    private HandlerUtils(){
        //denies initialization


    }

    public void registerOwner(Handler.Callback callBack){
        this.callBack=callBack;
        this.handler = new Handler((callBack));
    }
//Lazy initialization for singleton
    public static HandlerUtils getDefault(){
       if(instance==null){
           instance= new HandlerUtils();

       }
        return instance;
    }





    public void unregisterOwner(Object object){

        if(object instanceof Handler.Callback) {
            this.handler = null;
            this.callBack = null;
        }
    }

    public void sendMessage(String data){
        Message message= new Message();
        Bundle bundle= new Bundle();
        bundle.putString(STRING_KEY,data);
        message.setData(bundle);
        handler.sendMessage(message);

    }

    public String parseMessage(Message message){
        return message.getData().getString(STRING_KEY);
    }
}
