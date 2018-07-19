package com.example.admin.daily3;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GithubClient {

    public static final String URL="https://api.github.com/";
    public static final String ACCESS_TOKEN="db4ee9f0af6a58b06cc595af37d8660173f5a8a5";
public static final String TAG=GithubClient.class.getSimpleName();
private HashMap<String,String> fieldsMap;

GithubClient(){
    fieldsMap =new HashMap<>();
}


    private Retrofit createClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;


    }

    public Call<ResponseBody> getUser(String results){
        Service service=createClient().create(Service.class);
        return service.getUser(results);
    }

    public void makeCallSync(final String results){

        Log.d(TAG, "makeCallSync: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ResponseBody response=getUser(results).execute().body();
                    String responseStr=response.string();

                    Log.d(TAG, "run: "+responseStr);

                    String[] strings=responseStr.split(",");
                    String[] strings1=new String[strings.length*8];
                    int count=0;
                    for (String string : strings) {
                        String[] a1=string.split(":");



                          String key= a1[0].substring(a1[0].indexOf("\"")+1,a1[0].lastIndexOf("\""));
                        count++;
                        String value= a1[1];
                        count++;

                        Log.d(TAG, "run: key:"+key+"value: "+value);

                        fieldsMap.put(key,value);
                    }


                    Log.d(TAG, "run: "+ fieldsMap.size());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "run: Response Failed");
                }
            }
        }).start();
    }


    interface Service{
        @GET("user")
        Call<ResponseBody> getUser(@Query("access_token") String results);

    }

}
