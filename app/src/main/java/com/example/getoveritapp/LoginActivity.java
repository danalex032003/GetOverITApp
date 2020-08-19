package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private boolean validateEmail(String email) {
        if (email.equals("admin")) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validatePassword(String password) {
        if (password.equals("admin")) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText)findViewById(R.id.emailLoginEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordLoginEditText);
        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail(emailEditText.getText().toString()) && validatePassword(passwordEditText.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}