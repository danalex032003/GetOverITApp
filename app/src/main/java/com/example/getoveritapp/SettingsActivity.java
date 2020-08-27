package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.getoveritapp.user.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private Button cardInfoButton, termsAndConditions, signOut;

    private ImageButton facebookImageButton, instagarmImageButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cardInfoButton = findViewById(R.id.cardSettingsButton);
        termsAndConditions = findViewById(R.id.termsAndConditionsSettingsButton);
        signOut = findViewById(R.id.signOutSettingsButton);

        facebookImageButton = findViewById(R.id.facebookSettingsImageButton);
        instagarmImageButton = findViewById(R.id.instagramSettingsImageButton);

        firebaseAuth = FirebaseAuth.getInstance();

        cardInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        facebookImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cyclingo.official/?modal=admin_todo_tour"));
                startActivity(intent);
            }
        });

        instagarmImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/cyclin.go/"));
                startActivity(intent);
            }
        });
    }
}