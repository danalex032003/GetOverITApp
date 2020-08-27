package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.getoveritapp.messages.MessageDisplayer;

public class CardActivity extends AppCompatActivity {

    private Button updateCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        updateCardButton = findViewById(R.id.updateCardButton);

        updateCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageDisplayer.message(getApplicationContext(),"In development");
            }
        });
    }
}