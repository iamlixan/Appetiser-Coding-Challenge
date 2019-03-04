package me.com.lixan.appetisercodingchallenge.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.com.lixan.appetisercodingchallenge.R;
import me.com.lixan.appetisercodingchallenge.interfaces.DataInterface;
import me.com.lixan.appetisercodingchallenge.model.APIModel;
import me.com.lixan.appetisercodingchallenge.presenter.DataPresenter;
import me.com.lixan.appetisercodingchallenge.view.adapter.APIListAdapter;

/**
 * Created by LeakSun on 03/01/2019.
 */

public class MainActivity extends AppCompatActivity implements DataInterface{

    private final String TAG = "MainActivity";
    public static Context context;

    ListView listView;
    TextView dateTxt;
    DataPresenter dataPresenter;
    APIListAdapter apiListAdapter;
    ArrayList<APIModel.Results> resultsArrayList;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        listView = findViewById(R.id.listView);
        dateTxt = findViewById(R.id.datetxt);

        sharedPreferences = getSharedPreferences("visit_date", MODE_PRIVATE);

        getLastVisit();

        dataPresenter = new DataPresenter(this, context);
        dataPresenter.getAPIData();
    }

    @Override
    public void onAPIResults(List<APIModel.Results> resultsList) {
        resultsArrayList = (ArrayList<APIModel.Results>) resultsList;
        apiListAdapter = new APIListAdapter(resultsArrayList, context);
        listView.setAdapter(apiListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                APIModel.Results data = resultsArrayList.get(i);

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("BIG_PICTURE", data.artworkUrl100);
                intent.putExtra("LONG_DESCRIPTION", data.longDescription);
                startActivity(intent);
            }
        });

    }

    /**
     * Saves the time and date the user visited the app.
     * @param milis time the user last visted the app.
     */
    private void saveLastVisit(long milis){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            long currentTime = System.currentTimeMillis();
            editor.putLong("last_visit", currentTime);
            editor.apply();
    }

    /**
     * Gets the user's last visit time and date.
     */
    private void getLastVisit(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String lastVisit;
        if(sharedPreferences.contains("last_visit")){
            long zlastVisit = sharedPreferences.getLong("last_visit", 0);
             lastVisit = simpleDateFormat.format(zlastVisit);
        }else{
             lastVisit = simpleDateFormat.format(System.currentTimeMillis());
        }
        dateTxt.setText("Last User Visit: " + lastVisit);
    }

    @Override
    protected void onPause() {
        super.onPause();
        long currentTime = System.currentTimeMillis();
        saveLastVisit(currentTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long currentTime = System.currentTimeMillis();
        saveLastVisit(currentTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLastVisit();
    }
}
