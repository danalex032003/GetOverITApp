package com.example.getoveritapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.getoveritapp.messages.MessageDisplayer;
import com.example.getoveritapp.user.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SetupProfileForHiringActivity extends AppCompatActivity {

    private EditText descriptionEditText, phoneEditText;
    private RadioGroup radioGroup;
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
        radioGroup = findViewById(R.id.radioGroupSetupProfileHiring);
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
                if (documentSnapshot.getBoolean("isVisible") == true) {
                    radioGroup.check(R.id.yesRadioButton);
                }
                else {
                    radioGroup.check(R.id.noRadioButton);
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionEditText.getText().toString();
                final String phone = phoneEditText.getText().toString().trim();
                final int checkedID = radioGroup.getCheckedRadioButtonId();

                documentReference.update("description", description);
                documentReference.update("phone", phone);
                switch (checkedID) {
                    case R.id.yesRadioButton:
                        documentReference.update("isVisible", true);
                        break;
                    case R.id.noRadioButton:
                        documentReference.update("isVisible", false);
                }


                MessageDisplayer.message(getApplicationContext(), "Information updated!");

                Intent intent = new Intent(SetupProfileForHiringActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}