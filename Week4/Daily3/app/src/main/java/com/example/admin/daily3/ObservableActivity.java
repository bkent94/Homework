package com.example.admin.daily3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;

public class ObservableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);


    }


    public Observable createObservable(){
        Observable observable= Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {

            }
        });
        return observable;
    }

    public Observable deferObservable(){
        Observable observable=Observable.defer(new Callable<ObservableSource>() {
            @Override
            public ObservableSource call() throws Exception {
                return null;
            }
        });
        return observable;
    }

    public Observable timeObserverable(){
        Observable observable=Observable.timer(5,TimeUnit.SECONDS);

        return observable;
    }
}
