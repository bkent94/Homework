package com.example.admin.daily4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Person;

public class ViewPeople extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_people);


        ListView listOfPeopleView=findViewById(R.id.listOfPeople);

        Intent intent=getIntent();
       ArrayList listOfPeople= intent.getParcelableArrayListExtra(getString(R.string.list_of_people));

        ArrayAdapter<Person> personArrayAdapter =new ArrayAdapter<Person>(this,R.layout.listview,listOfPeople);

        listOfPeopleView.setAdapter(personArrayAdapter);



    }
}
