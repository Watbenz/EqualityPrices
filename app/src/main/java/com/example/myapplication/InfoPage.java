package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;

public class InfoPage extends AppCompatActivity {

    private Spinner dropdown;
    private int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        init();
    }

    private void init() {
        // Create back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set dropdown
        dropdown = findViewById(R.id.amountDropDown);
        String[] dropdownList = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, R.id.dropdown_item, dropdownList);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                amount = adapterView.getPositionForView(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                amount = -1;
            }
        });
    }

    public void confirmButtonClick(View view) {

        if (amount == -1) {
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG).show();
        }
        else {
            DataCreate dataCreate = new DataCreate(amount);
            Intent intent = new Intent(InfoPage.this, CalculatePage.class);
            Gson gson = new Gson();
            String data = gson.toJson(dataCreate);
            intent.putExtra("createData", data);
            startActivity(intent);
        }
    }

    // Arrow navigate to home
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
