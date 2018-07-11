package com.example.admin.daily2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.daily2.model.data.Animal;
import com.example.admin.daily2.model.data.ListOfAnimalsAdapter;
import com.example.admin.daily2.model.data.LocalDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.daily2.MainActivity.localDataSource;

public class ListOfAnimalsActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    public static final String ANIMALSOUNDKEY ="ANIMALSOUND" ;
    public static final String ANIMALDETAILSKEY ="ANIMALDETAILS" ;
    private String[] category;
    private RecyclerView animalsList;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_animals);

        Intent intent=getIntent();
        category=new String[1];
        category[0] = intent.getStringExtra(ListOfCategoriesActivity.CATEGORYKEY);


      List animalList= localDataSource.getAnimalsByCategory(category);


        animalsList = findViewById(R.id.animalsList);
        ListOfAnimalsAdapter adapter=new ListOfAnimalsAdapter(animalList);
        animalsList.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager;
        layoutManager=new LinearLayoutManager(this);
        animalsList.setLayoutManager(layoutManager);
        animalsList.addOnItemTouchListener(this);


    }

    public void ViewDetailsOfAnimal(View view) {
        Intent intent=new Intent(this,DetailsOfAnimalActivity.class);

        startActivity(intent);
    }



    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        Intent intent=new Intent(this,DetailsOfAnimalActivity.class);

        TextView tvAnimalSound=(TextView) recyclerView.findViewById(R.id.tvAnimalSound);
        TextView tvAnimalDetails=(TextView) recyclerView.findViewById(R.id.tvAnimalDetails);


        String sound =tvAnimalSound.getText().toString();
        String details=tvAnimalDetails.getText().toString();
        intent.putExtra(ANIMALSOUNDKEY,sound);
        intent.putExtra(ANIMALDETAILSKEY,details);


        startActivity(intent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
