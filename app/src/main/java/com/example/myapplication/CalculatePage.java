package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        generateMoneyButton();
        generatePerson();
        generateNextPerson(8-1);
    }

    private void getData() {
        // Get data from last activity
        Intent intent = getIntent();
        String json = intent.getStringExtra("createData");
        Gson gson = new Gson();
        dataCreate = gson.fromJson(json, DataCreate.class);
    }

    @SuppressLint("NewApi")
    private void generateMoneyButton() {
        String[] val = {
                "clear", "1", "5", "10", "20", "50",
                "100", "500", "1k", "5k", "10k",
                "20k", "50k", "100k"
        };

        LinearLayout parent = (LinearLayout) findViewById(R.id.linearLayout_scroll_button);
        LinearLayout.LayoutParams params = getMoneyButtonParams();

        for (String each : val) {
            final Button b = new Button(getApplicationContext());
            b.setText(each);
            b.setLayoutParams(params);
            b.setTextColor(getResources().getColor(R.color.white));
            b.setBackground(getResources().getDrawable(R.drawable.ripple_money_rounded_button));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moneyUpdate(b.getText().toString());
                }
            });
            parent.addView(b);
        }
    }

    private void moneyUpdate(String s) {
        Log.i("Debug", "moneyUpdate: " + s);
    }

    private void generatePerson() {
        LinearLayout parent = (LinearLayout) findViewById(R.id.first_person);
        View child = getLayoutInflater().inflate(R.layout.activity_person, null);
        parent.addView(child);
    }

    private void generateNextPersonN(int n) {
        if (n <= 0) return;

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
        if (n <= 0) return;

        setHeadNextPerson();

        int row = n/3;

        for (int i=0; i<row; i++) {
            generateNextPersonN(3);
        }
        generateNextPersonN(n%3);
    }

    private void setHeadNextPerson() {
        TextView text = new TextView(getApplicationContext());
        text.setText("รายจ่ายของเพื่อน ๆ");
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f);
        text.setGravity(Gravity.CENTER);
        LinearLayout parent = (LinearLayout) findViewById(R.id.next_person_display);
        parent.addView(text);
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

    private LinearLayout.LayoutParams getMoneyButtonParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.width = 100;
        params.height = 100;
        params.rightMargin = 20;

        return params;
    }
}
