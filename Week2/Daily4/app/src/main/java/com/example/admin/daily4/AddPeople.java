package com.example.admin.daily4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import model.Person;

public class AddPeople extends MainActivity{

    private EditText personName;
    private EditText personAge;
    private RadioGroup personGender;
    private ArrayList<Person> listOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        super.NavigationSetup(this);

        personName = findViewById(R.id.personName);
        personAge = findViewById(R.id.personAge);
        personGender = findViewById(R.id.personGender);

        listOfPeople = new ArrayList<Person>();
    }

    public void AddPerson(View view) {
        if(personName.getText().equals("") || personAge.getText().equals("") ){
            return;
        }

        RadioButton currentGender=findViewById(personGender.getCheckedRadioButtonId());


        String name=personName.getText().toString();
        String ageStr=personAge.getText().toString();
        int age=Integer.valueOf(ageStr);
        String gender=currentGender.getText().toString();

        Person person=new Person(name,age,gender);


        listOfPeople.add(person);
    }

    public void ViewPeople(View view) {

        Intent intent=new Intent(getApplicationContext(),ViewPeople.class);
        intent.putParcelableArrayListExtra(getString(R.string.list_of_people),listOfPeople);
        startActivity(intent);
    }
}
