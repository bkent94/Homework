package com.example.admin.mvpdagger.view.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpdagger.R;
import com.example.admin.mvpdagger.view.di.DaggerGithubComponent;

import org.w3c.dom.Text;

import javax.inject.Inject;

public class GithubActivity extends AppCompatActivity implements GithubContract.View {

    private TextView tvMain;
    private EditText etMain;
    //inject the presenter instance using @Inject as target
    @Inject
    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);
        tvMain = findViewById(R.id.tvMain);
    //    presenter = new GithubPresenter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //initialize the dagger component
        DaggerGithubComponent.create().inject(this);
        presenter.attachView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    public void onDisplayName(View view) {
        presenter.validateName(etMain.getText().toString());
    }

    @Override
    public void onValidationResults(String validName) {
        tvMain.setText(validName);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
