package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
        getData();
        generatePerson();
        generateNextPerson(7);
    }

    private void getData() {
        // Get data from last activity
        Intent intent = getIntent();
        String json = intent.getStringExtra("createData");
        Gson gson = new Gson();
        dataCreate = gson.fromJson(json, DataCreate.class);
    }

    private void generatePerson() {
        LinearLayout parent = (LinearLayout) findViewById(R.id.first_person);
        View child = getLayoutInflater().inflate(R.layout.activity_person, null);
        parent.addView(child);
    }

    private void generateNextPerson(int n, boolean flag) {
        LinearLayout.LayoutParams params = getEqualParams();
        LinearLayout horizon = new LinearLayout(getApplicationContext());
        horizon.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < n; i++) {
            View person = getLayoutInflater().inflate(R.layout.activity_alt_person, null);
            person.setLayoutParams(params);
            horizon.addView(person);
        }

        LinearLayout parent = (LinearLayout) findViewById(R.id.next_person_display);
        parent.addView(horizon);
    }

    private void generateNextPerson(int n) {
        int row = n/3;

        for (int i=0; i<row; i++) {
            generateNextPerson(3, true);
        }
        generateNextPerson(n%3, true);
    }

    private LinearLayout.LayoutParams getEqualParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f);
        params.topMargin = 25;
        params.leftMargin = 15;

        return params;
    }
}
