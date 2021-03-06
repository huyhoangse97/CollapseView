package com.example.collapseview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    private final String tag = "MainActivityTag";
    private ModeContainer modeContainer;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        modeContainer = new ModeContainer();

        try {
            InputStream is = getAssets().open("phones.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            synchronized (this) {
                modeContainer = gson.fromJson(reader, ModeContainer.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModeListAdapter modeListAdapter = new ModeListAdapter(MainActivity.this, modeContainer);
        expandableListView = findViewById(R.id.mode_list);
        expandableListView.setAdapter(modeListAdapter);

//        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.main_layout);
//        mainLayout.addView(expandableListView);

        setContentView(R.layout.activity_main);
    }
}