package com.example.admin.firebase.manager;

import android.util.Log;

import com.example.admin.firebase.model.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RemoteDatabaseManager {
    public static final String TAG=RemoteDatabaseManager.class.getSimpleName()+"_TAG";
    private static RemoteDatabaseManager instance=null;
    FirebaseDatabase database;
    private RemoteDatabaseManager(){

    }

    public static RemoteDatabaseManager getInstance(){
        if(instance==null){
            instance=new RemoteDatabaseManager();
        }

        return  instance;
    }

    public void updateData(String data){
        Log.d(TAG, "updateData: ");
database=FirebaseDatabase.getInstance();
//create a reference
        DatabaseReference homeReference= database.getReference("home");
        homeReference.setValue(data);
    }

    public void uploadPerson(Person person){
        database=FirebaseDatabase.getInstance();

        //create a reference for person
        DatabaseReference personReference=database.getReference("people");
        personReference.push().setValue(person);//push creates a new reference under the main reference
    }

    public void readPeople(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference personReference=database.getReference("people");
        personReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Person person=snapshot.getValue(Person.class);
                    Log.d(TAG, "onDataChange: "+person.getFirstName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
