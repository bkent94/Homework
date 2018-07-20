package com.example.admin.test_friday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.test_friday.model.Lf;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements RetrofitHelper.ResponseProcessor {
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private EditText etMain;
    private TextView tvMain;
    private RetrofitHelper retrofitHelper;
    private ListView lvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);
        tvMain = findViewById(R.id.tvMain);
        retrofitHelper = new RetrofitHelper();
        lvResults = findViewById(R.id.lvResults);


    }

    public void onSearch(View view) {
        Log.d(TAG, "onSearch: ");
        retrofitHelper.makeSyncCall(etMain.getText().toString(), this);


    }

    @Override
    public void ProcessResponse(String responseStr) {
        Log.d(TAG, "ProcessResponse: ");
        tvMain.setText(responseStr);
    }

    @Override
    public void ProcessList(List<Lf> lfList) {
        List<String> stringList = new ArrayList<>();

        for(Lf definition: lfList) {
            stringList.add(definition.toString());
        }

        ArrayAdapter<String> definitionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        lvResults.setAdapter(definitionsAdapter);
    }
}
