package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class SignalsCustomizationActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signals_customization);

        listView = findViewById(R.id.signalsListView);

        //teh ArrayList for the custom ListView
    }
}