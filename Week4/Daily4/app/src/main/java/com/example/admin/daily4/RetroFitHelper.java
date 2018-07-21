package com.example.admin.daily4;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.admin.daily4.model.APIResponse;
import com.example.admin.daily4.model.Hit;
import com.example.admin.daily4.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ServiceConfigurationError;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetroFitHelper {



    public static final String BASE_URL="https://api.edamam.com/";


    public static final String APP_ID="b5fdb30e";
    public static final String APP_KEY="062648fc51464532b0644cdf5a8184ab";
    public static final String GET="search";
    public static final String TAG=RetroFitHelper.class.getSimpleName()+"_TAG";

    private Retrofit createClient(){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit;
    }

    private io.reactivex.Observable<APIResponse> getRecipes(String recipes){
Service service=createClient().create(Service.class);
return service.getRecipes(recipes,APP_ID,APP_KEY);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void retrieveRecipes(final String recipes, final Callback callback) throws IOException {

        Log.d(TAG, "retrieveRecipes: ");
        
        getRecipes(recipes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<APIResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: "+Thread.currentThread().getName());
                List<Bitmap> imageList=new ArrayList<>();



            }

            @Override
            public void onNext(final APIResponse apiResponse) {
                Log.d(TAG, "onNext: "+apiResponse.getHits().size());



                ArrayList<Recipe> recipes = new ArrayList<>();
                for (Hit hit : apiResponse.getHits()) {
                    recipes.add(hit.getRecipe());
                }

                callback.onRecipesRetrieved(recipes);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
        
    }

  interface Service{
      @GET(GET)
      io.reactivex.Observable<APIResponse> getRecipes(@Query("q")  String recipes,@Query("app_id") String appID,@Query("app_key") String appKey);
  }

  interface Callback{
        void onRecipesRetrieved(List<Recipe> recipes);
  }
}
