package com.example.getoveritapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.getoveritapp.user.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity {

    private long backPressedtime;
    private Toast backToast;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;
    private String type;

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                type = documentSnapshot.getString("type");
            }
        });

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
                Intent intent = new Intent(HomeActivity.this, CustomizationActivity.class);
                startActivity(intent);
            }
        });

        hiringImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("company")) {
                    Intent intent = new Intent(HomeActivity.this, HiringActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(HomeActivity.this, SetupProfileForHiringActivity.class);
                    startActivity(intent);
                }

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