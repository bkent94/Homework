package com.example.admin.daily3;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GithubProfile.OnFragmentInteractionListener,GithubRepositories.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubClient githubClient=new GithubClient();
        githubClient.makeCallSync(GithubClient.ACCESS_TOKEN);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
