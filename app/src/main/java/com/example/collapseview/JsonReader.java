package com.example.collapseview;

import android.content.Context;

import com.example.mode_model.ModeContainer;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class JsonReader<T> {
    private Context context;
    private String fileName;
    private Gson gson;

    public JsonReader(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public ModeContainer fromJson() {
        ModeContainer modeContainer = new ModeContainer();
        try {
            InputStream is = context.getAssets().open("modes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            synchronized (this) {
                modeContainer = gson.fromJson(reader, ModeContainer.class);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return modeContainer;
    }
}
