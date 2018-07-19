package com.example.admin.makingrestcalls.client;

import android.util.Log;

import com.example.admin.makingrestcalls.model.APIResponses.example.APIResponse;
import com.example.admin.makingrestcalls.model.APIResponses.example.Result;
import com.example.admin.makingrestcalls.model.CustomUser;
import com.example.admin.makingrestcalls.utils.RxUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RetrofitHelper {
    private static final String BASE_URL="https://randomuser.me";
    private static final String TAG=RetrofitHelper.class.getSimpleName();

    private Retrofit createClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;


    }

    public Call<APIResponse> getUsers(String results){
        Retrofit retrofit=createClient();
        Service service = retrofit.create(Service.class);
        return service.getUsers(results);
    }

    //using rxjava observable
    private io.reactivex.Observable<APIResponse> getUserRx(String results){
        return createClient().create(Service.class).getUsersRx(results);
    }

    public void makeCallSync(final String results){
        new Thread(new Runnable() {
            @Override
            public void run() {
                APIResponse response= null;
                try {
                    response = getUsers(results).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: "+ response.getResults().size());
            }
        }).start();
    }
    
    public void makeCallAsync(final String results){
        getUsers(results).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse =response.body();
                Log.d(TAG, "onResponse: "+apiResponse.getResults().size());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed to get "+t.toString());
            }
        });
    }

    public void makeCallRxJava(String results){
getUserRx(results).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<APIResponse>() {
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: "+Thread.currentThread().getName());
    }

    @Override
    public void onNext(APIResponse apiResponse) {
        Log.d(TAG, "onNext: "+ Thread.currentThread().getName());
        Log.d(TAG, "onNext: Response: "+ apiResponse.getResults().size());
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: "+Thread.currentThread().getName());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: "+Thread.currentThread().getName());
    }
});
    }

    public void makeCallCustomRx(String results, final RetrofitCallback callback){
getUserRx(results).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(RxUtils.getMappingFunction()).subscribe(new Observer<CustomUser>() {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(CustomUser customUser) {
        callback.onResults(customUser);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
});
    }

    public void makeCallCustomRxJava(String results,ListCallback callback){
        final List<Result> resultList= new ArrayList<>();
        getUserRx(results).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap(RxUtils.getResultMapper())
                .map(RxUtils.transformResult())
                .subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result result) {
                resultList.add(result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


   public interface RetrofitCallback{
        void onResults(CustomUser customUser);
    }

    public interface ListCallback{
        void onResults(List<Result> resultList);
    }

    //interface to use http verbs
    interface Service{
        @GET("api")
        Call<APIResponse> getUsers(@Query("results") String results);
        @GET("api")
        io.reactivex.Observable<APIResponse> getUsersRx(@Query("results") String results);
    }
}
