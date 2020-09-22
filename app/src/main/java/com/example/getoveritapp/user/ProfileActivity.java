package com.example.getoveritapp.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getoveritapp.HomeActivity;
import com.example.getoveritapp.R;
import com.example.getoveritapp.messages.MessageDisplayer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, usernameTextView;

    private EditText nameEditText, usernameEditText, emailEditText;

    private Button updateButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView = findViewById(R.id.nameProfileTextView);
        usernameTextView = findViewById(R.id.usernameProfileTextView);

        nameEditText = findViewById(R.id.nameProfileEditText);
        usernameEditText = findViewById(R.id.usernameProfileEditText);

        updateButton = findViewById(R.id.updateProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nameTextView.setText(documentSnapshot.getString("name"));
                usernameTextView.setText(documentSnapshot.getString("username"));

                nameEditText.setText(documentSnapshot.getString("name"));
                usernameEditText.setText(documentSnapshot.getString("username"));
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameEditText.getText().toString().trim();
                final String name = nameEditText.getText().toString();

                documentReference.update("name", name);
                documentReference.update("username", username);

                MessageDisplayer.message(getApplicationContext(), "User updated!");

                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}