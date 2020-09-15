package com.example.getoveritapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SetupProfileForHiringActivity extends AppCompatActivity {

    private EditText descriptionEditText, phoneEditText;
    private CheckBox isVisibleCheckBox;
    private Button updateButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_for_hiring);

        descriptionEditText = findViewById(R.id.descriptionEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        updateButton = findViewById(R.id.updateSetupUserProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                descriptionEditText.setText(documentSnapshot.getString("description"));
                phoneEditText.setText(documentSnapshot.getString("phone"));
            }
        });
    }
}