package com.example.admin.daily4;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.admin.daily4.adapter.RecipeListAdapter;
import com.example.admin.daily4.component.DaggerRetrofitComponent;
import com.example.admin.daily4.component.RetrofitComponent;
import com.example.admin.daily4.model.Hit;
import com.example.admin.daily4.model.Recipe;
import com.example.admin.daily4.module.RetroFitProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements RetroFitHelper.Callback,View.OnClickListener {

    private RecyclerView recipeList;
    private EditText etSearch;
    @Inject
    public RetroFitHelper retroFitHelper;
    public static final String TAG=MainActivity.class.getSimpleName()+"_TAG";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        recipeList = findViewById(R.id.recipeList);
        recipeList.setLayoutManager(new GridLayoutManager(this,3));




    }

    @Override
    protected void onStart() {
        super.onStart();
        RetrofitComponent retrofitComponent=DaggerRetrofitComponent.builder().build();
retrofitComponent.provideRetrofit(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSearch(View view)  {
        try {
            retroFitHelper.retrieveRecipes(etSearch.getText().toString(),this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRecipesRetrieved(final List<Recipe> recipes) {
        Log.d(TAG, "onRecipesRetrieved: ");


        new Thread(new Runnable() {
            @Override
            public void run() {

                final ArrayList<Bitmap> imageList=new ArrayList<>();
                try {

                    for (int i = 0; i <recipes.size() ; i++) {
                        URL url=new URL(recipes.get(i).getImage());
                        InputStream inputStream = (InputStream) url.getContent();
                        Bitmap   bitmap= BitmapFactory.decodeStream(inputStream);
                        imageList.add(bitmap);
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<Parcelable> parcelableRecipes= new ArrayList<>();
                parcelableRecipes.addAll(recipes);

                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        recipeList.setAdapter(new RecipeListAdapter(recipes,imageList));
                    }
                });

            }

        }).start();





    }

    @Override
    public void onClick(View view) {
        if(view instanceof ImageView){

        }
    }
}
