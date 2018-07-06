package com.example.admin.daily4;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout navigationDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

NavigationSetup(this);

    }

    protected void NavigationSetup(MainActivity activity){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(activity);
        navigationDrawerLayout = (DrawerLayout)findViewById(R.id.nav_drawer);


        actionBarDrawerToggle=new ActionBarDrawerToggle(activity,navigationDrawerLayout,R.string.open,R.string.close);

        navigationDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Class activityClass =MainActivity.class;

        switch (menuItem.getItemId()){

            case R.id.homeAsUp:
                onBackPressed();
                break;

            case R.id.implicit_intents:
                activityClass=ImplicitIntents.class;
                break;
            case R.id.emi_calculator:
                activityClass=EMICalculator.class;
                break;
            case R.id.add_people:
                activityClass=AddPeople.class;
                break;

            default:

        }

        Intent intent=new Intent(getApplicationContext(),activityClass);


        PendingIntent pendingIntent= TaskStackBuilder.create(this).addNextIntentWithParentStack(intent).getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(pendingIntent);
        getSupportFragmentManager().beginTransaction().add(new Fragment(),"detail").addToBackStack(null).commit();
        startActivity(intent);

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
