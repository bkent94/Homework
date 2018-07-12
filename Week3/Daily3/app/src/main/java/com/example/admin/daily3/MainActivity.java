package com.example.admin.daily3;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.admin.daily3.Data.Celebrity;
import com.example.admin.daily3.fragments.CelebrityDetails;
import com.example.admin.daily3.fragments.CelebrityListFragment;
import com.example.admin.daily3.fragments.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements CelebrityListFragment.OnListFragmentInteractionListener,CelebrityDetails.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private FrameLayout celebrityListFrameHolder;
    private FrameLayout celebrityDetailsHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();
        celebrityListFrameHolder = findViewById(R.id.celebrityListFrameHolder);
        celebrityDetailsHolder = findViewById(R.id.celebrityDetailsFrameHolder);

        fragmentManager.beginTransaction().add(celebrityListFrameHolder.getId(),new CelebrityListFragment()).addToBackStack(null).commit();



    }

    public CelebrityDetails getCelebrityDetailsFragment(){
        return (CelebrityDetails) fragmentManager.findFragmentById(celebrityDetailsHolder.getId());
    }

    @Override
    public void onListFragmentInteraction(Celebrity celebrity) {
        if(getCelebrityDetailsFragment()==null){
            fragmentManager.beginTransaction().add(celebrityDetailsHolder.getId(),CelebrityDetails.newInstance(celebrity.getImage(),celebrity.getDetails())).addToBackStack(null).commit();
        }
        else{

            getCelebrityDetailsFragment().updateData(celebrity.getImage(),celebrity.getDetails());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
