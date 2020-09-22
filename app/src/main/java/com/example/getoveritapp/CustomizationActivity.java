package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getoveritapp.messages.MessageDisplayer;

public class CustomizationActivity extends AppCompatActivity {

    //clickable textview
    private TextView signalsTextView, logoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);

        signalsTextView = findViewById(R.id.signalsCustomizationTextView);
        logoTextView = findViewById(R.id.logoCustomizationTextView);

        signalsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomizationActivity.this, SignalsCustomizationActivity.class);
                startActivity(intent);
            }
        });

        logoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}