package com.example.admin.daily2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.admin.daily2.model.data.Animal;
import com.example.admin.daily2.model.data.Category;
import com.example.admin.daily2.model.data.LocalDataSource;

public class MainActivity extends AppCompatActivity {

    public static LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localDataSource = new LocalDataSource(this);

        InsertCategoriesAndAnimals();
    }

    private void InsertCategoriesAndAnimals() {

        localDataSource.DropTables();
        localDataSource.CreateTables();

        Category bigcat=new Category("Big_Cat");
        Category rodent=new Category("Rodent");

        localDataSource.insertCategory(bigcat);
        localDataSource.insertCategory(rodent);

        Animal lion=new Animal("Lion","Big_Cat","Roar","Insert details about lion here","200000");
        Animal guineapig=new Animal("Guinea Pig","Rodent","Wheek","Insert details about guinea pig here","1000");

        localDataSource.insertAnimal(lion);
        localDataSource.insertAnimal(guineapig);
    }

    public void ViewAnimalCategories(View view) {
        Intent intent=new Intent(this,ListOfCategoriesActivity.class);
        startActivity(intent);
    }
}
