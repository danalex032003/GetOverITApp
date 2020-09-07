package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.getoveritapp.user.ProfileActivity;
//import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity {

    private long backPressedtime;
    private Toast backToast;

    @Override
    public void onBackPressed() {

        if (backPressedtime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        else {
            backToast = Toast.makeText(getBaseContext(), "Please press BACK again to exit!", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedtime = System.currentTimeMillis();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton profileImageButton = findViewById(R.id.profileHomePageImageButton);
        ImageButton customizationImageButton = findViewById(R.id.customizationHomePageImageButton);
        ImageButton hiringImageButton = findViewById(R.id.hiringHomePageImageButton);
        ImageButton settingsImageButton = findViewById(R.id.settingsHomePageImageButton);

//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        customizationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        hiringImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, HiringActivity.class);
//                startActivity(intent);
            }
        });

        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

}