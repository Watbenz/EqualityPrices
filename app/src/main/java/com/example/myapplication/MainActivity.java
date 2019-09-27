package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        setTitle(R.string.app_name);
        recyclerSetting();
    }

    private void recyclerSetting() {
        RecyclerView recyclerView = findViewById(R.id.menu_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                openCreatePage();
                                break;
                            case 4:
                                exit();
                                break;
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.i("click", "longClick: pos = " + position);
                    }
                })
        );

        String[] menu = {"New", "Load", "History", "Setting", "Exit"};
        AdapterRecycle adapter = new AdapterRecycle(menu);
        recyclerView.setAdapter(adapter);
    }

    private void exit() {
        finish();
        System.exit(0);
    }

    private void openCreatePage() {
        Intent intent = new Intent(getApplicationContext(), InfoPage.class);
        startActivity(intent);
    }
}
