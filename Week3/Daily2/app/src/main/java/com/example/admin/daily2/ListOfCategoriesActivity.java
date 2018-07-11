package com.example.admin.daily2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.daily2.model.data.Category;
import com.example.admin.daily2.model.data.ListOfCategoriesAdapter;
import com.example.admin.daily2.model.data.LocalDataSource;

import static com.example.admin.daily2.MainActivity.localDataSource;

public class ListOfCategoriesActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String CATEGORYKEY = "CATEGORY";
    private ListView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_categories);


        PopulateCategoryList();
    }

    private void PopulateCategoryList() {

        categoriesList = findViewById(R.id.categoriesList);
        ListOfCategoriesAdapter adapter=new ListOfCategoriesAdapter(this,R.layout.list_of_categories,localDataSource.getAllCategories());
        categoriesList.setAdapter(adapter);
        categoriesList.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,ListOfAnimalsActivity.class);

        TextView textView=(TextView) view.findViewById(R.id.tvCategory);

        String category =textView.getText().toString();
        intent.putExtra(CATEGORYKEY,category);


        startActivity(intent);
    }
}
