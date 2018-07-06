package com.example.admin.daily4;


import android.Manifest;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.ActionBarContainer;


public class ImplicitIntents extends MainActivity {



    private final int REQUEST_IMAGE_CAPTURE=1;
    private ImageView imageView;
    private Intent sharedString;
    private Intent callIntent;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);

        super.NavigationSetup(this);

        imageView = findViewById(R.id.viewPicture);

        sharedString = new Intent();
        sharedString.setAction(Intent.ACTION_SEND);
        sharedString.putExtra(Intent.EXTRA_TEXT,"I am sharing this text");
        sharedString.setType("text/plain");
        startActivity(sharedString);

        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("5084068215"));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            Bitmap bitmap=(Bitmap)bundle.get("data");
            imageView.setImageBitmap(bitmap);

        }
    }

    public void TakePicture(View view) {
        Intent takePictureIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    public void MakeCall(View view) {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(callIntent);
    }
}
