package com.example.admin.makingrestcalls.client;

import android.util.Log;

import com.example.admin.makingrestcalls.model.APIResponses.example.APIResponse;
import com.example.admin.makingrestcalls.utils.HandlerUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkhttpHelper {
public static final String TAG=OkhttpHelper.class.getSimpleName();
    public static final String BASE_URL="https://randomuser.me/api?results=10";
    public static final String PATH="api";
    public static final String QUERY_RESULTS="results";

    OkHttpClient client;
    private final Request request;
    private String responseStr;

    public OkhttpHelper(){
        client=new OkHttpClient();
         request =new Request.Builder().url(getUrl()).build();
    }

    public HttpUrl getUrl(){
        HttpUrl url = new HttpUrl.Builder().scheme("https").host("randomuser.me").addPathSegment(PATH).addQueryParameter(QUERY_RESULTS,"10").build();

        return url;
    }

    public void executeSyncCall(){

        Log.d(TAG, "executeSyncCall: ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    responseStr = client.newCall(request).execute().body().string();

                    Gson gson= new Gson();
                    APIResponse APIResponse = gson.fromJson(responseStr, APIResponse.class);
                    HandlerUtils.getDefault().sendMessage(String.valueOf(APIResponse.getResults().size()));
                    Log.d(TAG, "run: "+ APIResponse.getResults().size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void executeAsyncCall(){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
APIResponse apiAPIResponse =gson.fromJson(response.body().string(), APIResponse.class);
                Log.d(TAG, "onResponse: Thread"+Thread.currentThread().getName());
                Log.d(TAG, "onResponse: Size"+ apiAPIResponse.getResults().size());
            }
        });
    }


}
