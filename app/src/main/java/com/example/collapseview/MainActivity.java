package com.example.collapseview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.mode_model.ModeContainer;
import com.example.mode_model.ExpandableModeListAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private final String tag = "MainActivityTag";
    private ModeContainer modeContainer;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Gson gson = new Gson();
        modeContainer = new ModeContainer();

        try {
            InputStream is = getAssets().open("modes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            synchronized (this) {
                modeContainer = gson.fromJson(reader, ModeContainer.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExpandableModeListAdapter modeListAdapter = new ExpandableModeListAdapter(MainActivity.this, modeContainer);
        expandableListView = findViewById(R.id.mode_list);
        expandableListView.setAdapter(modeListAdapter);
    }
}