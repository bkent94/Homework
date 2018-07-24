package com.example.admin.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.admin.firebase.manager.AuthManager;
import com.example.admin.firebase.manager.RemoteDatabaseManager;
import com.example.admin.firebase.model.Person;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements AuthManager.Callback {
    public static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";
    private AuthManager authManager;
    private RemoteDatabaseManager remoteDatabaseManager;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        authManager = AuthManager.getInstance(this);
        remoteDatabaseManager = RemoteDatabaseManager.getInstance();
        etData = findViewById(R.id.etData);
    }

    public void onSignOut(View view) {
        Log.d(TAG, "onSignOut: ");
        authManager.signOut();

    }

    @Override
    public void onLoginResults(FirebaseUser user) {

    }

    @Override
    public void onSignOut() {
        Log.d(TAG, "onSignOut: ");
        startLoginActivity();
    }

    private void startLoginActivity() {
        Log.d(TAG, "startLoginActivity: ");
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void onUploadData(View view) {
        Log.d(TAG, "onUploadData: ");
        remoteDatabaseManager.updateData(etData.getText().toString());
    }

    public void onUploadPerson(View view){
        String firstName= "John" + getRandomValue();
        String lastName="Doe"+getRandomValue();
        remoteDatabaseManager.uploadPerson(new Person(firstName,lastName));
    }

    private String getRandomValue(){
        return String.valueOf(new Random().nextInt());
    }

    public void onReadPeople(View view) {
        remoteDatabaseManager.readPeople();
    }
}
