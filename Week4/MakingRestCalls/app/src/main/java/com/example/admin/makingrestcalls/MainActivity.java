package com.example.admin.makingrestcalls;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.makingrestcalls.client.MyNativeCallHelper;
import com.example.admin.makingrestcalls.client.OkhttpHelper;
import com.example.admin.makingrestcalls.client.RetrofitHelper;
import com.example.admin.makingrestcalls.model.APIResponses.example.Result;
import com.example.admin.makingrestcalls.model.CustomUser;
import com.example.admin.makingrestcalls.utils.HandlerUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvResult;
    private MyNativeCallHelper callHelper;
    private OkhttpHelper okhttpHelper;
    private RetrofitHelper retrofitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResults);
        retrofitHelper = new RetrofitHelper();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        initClients();

    }

    @Override
    protected void onStart() {
        super.onStart();
        HandlerUtils.getDefault().registerOwner(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerUtils.getDefault().unregisterOwner(this);
    }

    private void initClients(){
        callHelper = new MyNativeCallHelper();
        okhttpHelper = new OkhttpHelper();
    }

    public void onNativeCall(){
        callHelper.makeCall();
    }

    public void onNativeHTTP(View view) {
        MyNativeCallHelper callHelper = new MyNativeCallHelper();
        callHelper.makeCall();
    }

    public void onOkhttpSync(View view) {
        okhttpHelper.executeSyncCall();
    }

    public void onOkhttpAsync(View view) {
    }

    @Override
    public boolean handleMessage(Message message) {
        tvResult.setText(HandlerUtils.getDefault().parseMessage(message));
        return true;
    }

    public void onRetrofit(View view) {
        String results="20";

        switch (view.getId()){
            case R.id.btnRetroSync:

                retrofitHelper.makeCallSync(results);
            break;
            case R.id.btnRetroAsync:
                retrofitHelper.makeCallAsync(results);
                break;
            case R.id.btnRetroRxJava:
                retrofitHelper.makeCallRxJava(results);
                break;
            case R.id.btnRetroRxCustom:
                retrofitHelper.makeCallCustomRx(results, new RetrofitHelper.RetrofitCallback() {
                    @Override
                    public void onResults(CustomUser customUser) {
                        tvResult.setText(customUser.getDepartment());
                    }
                });
                break;
            case R.id.btnRetroCustomRxJava:
                Log.d(TAG, "onRetrofit: Start");
                retrofitHelper.makeCallCustomRxJava(results, new RetrofitHelper.ListCallback() {
                    @Override
                    public void onResults(List<Result> resultList) {
                        for (Result result : resultList) {
                            Log.d(TAG, "onResults: "+ result);
                        }
                    }
                });
                break;

        }
    }
}
