package com.example.admin.test_friday;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.example.admin.test_friday.model.APIResponse;
import com.example.admin.test_friday.model.Lf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class
RetrofitHelper {
    public static final String BASE_URL = "http://www.nactem.ac.uk/";
    public static final String GET_URL = "software/acromine/dictionary.py";
    public static final String QUERY_URL = "sf";
    public static final String TAG = RetrofitHelper.class.getSimpleName() + "_TAG";


    private Retrofit createClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;


    }

    private Call<List<APIResponse>> getAcronyms(String acronym) {
        Log.d(TAG, "getAcronyms: ");
        Service service = createClient().create(Service.class);
        return service.getAcronyms(acronym);
    }

    public void makeSyncCall(final String results, final ResponseProcessor responseProcessor) {
        Log.d(TAG, "makeSyncCall: " + results);

        getAcronyms(results).enqueue(new Callback<List<APIResponse>>() {
            @Override
            public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                Log.d(TAG, "onResponse: " + response.body().size());
                responseProcessor.ProcessList(response.body().get(0).getLfs());

                //responseProcessor.ProcessResponse(response.body().get(0).getLfs().toString());
            }

            @Override
            public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    interface Service {
        @GET(GET_URL)
        public Call<List<APIResponse>> getAcronyms(@Query(QUERY_URL) String acronym);

    }

    interface ResponseProcessor {
        void ProcessResponse(String responseStr);

        void ProcessList(List<Lf> lfList);
    }
}
