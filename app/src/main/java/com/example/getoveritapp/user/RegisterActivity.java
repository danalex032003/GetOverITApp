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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getoveritapp.HomeActivity;
import com.example.getoveritapp.HomePageActivity;
import com.example.getoveritapp.R;
import com.example.getoveritapp.user.entities.UserEntity;
import com.example.getoveritapp.messages.MessageDisplayer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private EditText nameEditText;
    private TextView goToLoginTextView;

    private String userID;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    RadioGroup radioGroup;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        emailEditText = findViewById(R.id.emailRegisterEditText);
        passwordEditText = findViewById(R.id.passwordRegisterEditText);
        usernameEditText = findViewById(R.id.usernameRegisterEditText);
        nameEditText = findViewById(R.id.nameRegisterEditText);
        goToLoginTextView = findViewById(R.id.goToLoginTextView);

        radioGroup = findViewById(R.id.radioGroup);
        registerButton = findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        goToLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedId = radioGroup.getCheckedRadioButtonId();

                final String typeOfUser = findTypeOfUserByRadioButton(checkedId);
                final String email = emailEditText.getText().toString().trim();
                final String password = passwordEditText.getText().toString().trim();
                final String username = usernameEditText.getText().toString().trim();
                final String name = nameEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Enter an email!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Enter a password!");
                    return;
                }

                if (password.length() < 6) {
                    passwordEditText.setError("Password must contain minimum 6 caracters!");
                    return;
                }


                // register the user in firebase

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User created.", Toast.LENGTH_SHORT).show();

                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();

                            user.put("email", email);
                            user.put("username", username);
                            user.put("name", name);
                            user.put("type", typeOfUser);
                            user.put("isVisible", false);
                            user.put("description", "");
                            user.put("phone", "");

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created " + userID);
                                }
                            });

                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }

    private String findTypeOfUserByRadioButton(int checkedId) {
        switch (checkedId) {
            case R.id.userAccountRegisterRadioButton:
                return "user";
            case R.id.companyAccountRegisterRadioButton:
                return "company";
        }
        return null;
    }
}