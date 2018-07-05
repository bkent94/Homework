package com.example.admin.weekend;

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
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

public class Web extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout navigationDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        navigationDrawerLayout=(DrawerLayout)findViewById(R.id.nav_drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,navigationDrawerLayout,R.string.open,R.string.close);
        navigationDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.web).setChecked(true);

        WebView webView =findViewById(R.id.myWebView);
        webView.loadUrl("https://developer.android.com");
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
        Class activityClass=MainActivity.class;

        int id=menuItem.getItemId();

        if(id==R.id.homeAsUp){
            //NavUtils.navigateUpFromSameTask(this);
            Log.d("TAG","homeAsUp pressed");
            onBackPressed();
            return true;
        }
        if(id == R.id.start){}
        if(id==R.id.web){activityClass=Web.class;}
        if(id==R.id.songs){activityClass=Media.class;}

        Intent intent=new Intent(getApplicationContext(),activityClass);


        PendingIntent pendingIntent= TaskStackBuilder.create(this).addNextIntentWithParentStack(intent).getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(pendingIntent);
        getSupportFragmentManager().beginTransaction().add(new Fragment(),"detail").addToBackStack(null).commit();
        startActivity(intent);
        return false;
    }
}
