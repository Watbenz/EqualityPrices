package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

public class CalculatePage extends AppCompatActivity {

    private DataCreate dataCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_page);

        init();
    }

    private void init() {
        // Get data from last activity
        Intent intent = getIntent();
        String json = intent.getStringExtra("createData");
        Gson gson = new Gson();
        dataCreate = gson.fromJson(json, DataCreate.class);
    }
}
