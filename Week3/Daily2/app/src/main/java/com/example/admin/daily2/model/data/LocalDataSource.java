package com.example.admin.daily2.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource extends SQLiteOpenHelper {
    public LocalDataSource(Context context) {
        super(context, LocalDataContract.NAME, null, LocalDataContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {




    }

    public void DropTables(){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(LocalDataContract.DDL.DROP_ANIMALS_TABLE);
        database.execSQL(LocalDataContract.DDL.DROP_CATEGORY_TABLE);
    }

    public void CreateTables(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(LocalDataContract.DDL.CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(LocalDataContract.DDL.CREATE_ANIMALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertAnimal(Animal animal){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(LocalDataContract.Animals.NAME,animal.getName());
        contentValues.put(LocalDataContract.Animals.CATEGORY,animal.getCategory());
        contentValues.put(LocalDataContract.Animals.DETAILS,animal.getDetails());
        contentValues.put(LocalDataContract.Animals.SOUND,animal.getSound());
        contentValues.put(LocalDataContract.Animals.WEIGHT,animal.getWeight());
        long rowNumber=database.insert(LocalDataContract.ANIMALS_TABLE_NAME,null,contentValues);

        return rowNumber;
    }

    public long insertCategory(Category category){

        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(LocalDataContract.Category.NAME,category.getName());

        long rowNumber=database.insert(LocalDataContract.CATEGORY_TABLE_NAME,null,contentValues);

        return rowNumber;
    }

    public void insertAllAnimals(List<Animal> animalsList){
        for (Animal animal:animalsList) {
            insertAnimal(animal);
        }
    }

    public void insertAllCategories(List<Category> categoryList){
        for (Category category:categoryList) {
            insertCategory(category);
        }
    }

    public List<Animal> getAnimalsByCategory( String[] categories){


        SQLiteDatabase database=getReadableDatabase();

        List<Animal> animalList=new ArrayList<>();

      // Cursor cursor= database.query(LocalDataContract.ANIMALS_TABLE_NAME,null,LocalDataContract.Animals.CATEGORY,categories,null,null,null);
        String query=LocalDataContract.DML.GET_ANIMALS_BY_CATEGORY+"'"+categories[0]+"'";
        Log.d("", "getAnimalsByCategory query: "+query);
        Cursor cursor=database.rawQuery(query,null);

       if(cursor.moveToFirst()){
           do{
               String name=cursor.getString(cursor.getColumnIndex(LocalDataContract.Animals.NAME));
               String category=cursor.getString(cursor.getColumnIndex(LocalDataContract.Animals.CATEGORY));
               String sound=cursor.getString(cursor.getColumnIndex(LocalDataContract.Animals.SOUND));
               String details=cursor.getString(cursor.getColumnIndex(LocalDataContract.Animals.DETAILS));
               String weight=cursor.getString(cursor.getColumnIndex(LocalDataContract.Animals.WEIGHT));

               Animal animal=new Animal(name,category,sound,details,weight);
               animalList.add(animal);
           }while(cursor.moveToNext());
       }

       return animalList;
    }

    public List<Category> getAllCategories(){

        List<Category> categoryList =new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();

        Cursor cursor=database.rawQuery(LocalDataContract.DML.GET_ALL_CATEGORIES,null);

        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(LocalDataContract.Category.NAME));
                Category category=new Category(name);
                categoryList.add(category);
            }while (cursor.moveToNext());
        }

        return categoryList;
    }
}
