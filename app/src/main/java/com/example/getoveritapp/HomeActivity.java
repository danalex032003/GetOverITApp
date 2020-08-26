package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.getoveritapp.messages.MessageDisplayer;
import com.example.getoveritapp.user.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity {

    private ImageButton profileImageButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profileImageButton = findViewById(R.id.profileHomePageImageButton);

        firebaseAuth = FirebaseAuth.getInstance();

        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}