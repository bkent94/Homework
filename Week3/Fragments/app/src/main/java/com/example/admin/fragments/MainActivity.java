package com.example.admin.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.fragments.fragments.BlueFragment;
import com.example.admin.fragments.fragments.RedFragment;


public class MainActivity extends AppCompatActivity implements RedFragment.onFragmentInteractor, BlueFragment.OnFragmentInteractionListener   {
    public static final String TAG = MainActivity.class.getSimpleName();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void onAddingFragments(View view) {

       fragmentManager = getSupportFragmentManager();


        RedFragment redFragment = new RedFragment();
        BlueFragment blueFragment=BlueFragment.newInstance("data1","data2");


        switch (view.getId()){
            case R.id.btnAddRedFrag:
                if(fragmentManager.findFragmentById(R.id.fragmentHolder1)==null)
                fragmentManager.beginTransaction().add(R.id.fragmentHolder1,redFragment,RedFragment.STRING_TAG).addToBackStack(RedFragment.STRING_TAG).commit();
                break;
            case R.id.btnAddBlueFrag:
                if(fragmentManager.findFragmentById(R.id.fragmentHolder2)==null)
               fragmentManager.beginTransaction().add(R.id.fragmentHolder2,blueFragment).addToBackStack(BlueFragment.STRING_TAG).commit();
                break;
        }


    }

    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void onRemoveFragments(View view) {

        //Get red fragment
        fragmentManager = getSupportFragmentManager();
        RedFragment redFragment=(RedFragment) fragmentManager.findFragmentByTag(RedFragment.STRING_TAG);
        BlueFragment blueFragment=(BlueFragment) fragmentManager.findFragmentById(R.id.fragmentHolder2);

        removeFragmentIfNotNull(redFragment);
        removeFragmentIfNotNull(blueFragment);




    }

    private void removeFragmentIfNotNull(Fragment fragment) {
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment).commit();
            fragmentManager.popBackStackImmediate();
        }
    }

    @Override
public void onDataFromRed(String data){
        BlueFragment blueFragment= (BlueFragment) fragmentManager.findFragmentById(R.id.fragmentHolder2);

        if(blueFragment==null){
            blueFragment=BlueFragment.newInstance("Data1",data);
            fragmentManager.beginTransaction().add(R.id.fragmentHolder2,blueFragment).addToBackStack(null).commit();

        }
        else{
            blueFragment.updateData(data);
        }
        }
}
