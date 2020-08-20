package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.getoveritapp.entities.UserEntity;
import com.example.getoveritapp.messages.NoRadioButtonChecked;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private List<UserEntity> userEntities;

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private EditText nameEditText;

    RadioGroup radioGroup;
    Button registerButton;

    public RegisterActivity() {
        userEntities = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        emailEditText = findViewById(R.id.emailRegisterEditText);
        passwordEditText = findViewById(R.id.passwordRegisterEditText);
        usernameEditText = findViewById(R.id.usernameRegisterEditText);
        nameEditText = findViewById(R.id.nameRegisterEditText);

        radioGroup = findViewById(R.id.radioGroup);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if (checkedId == -1) {
                    //no radio buttons checked
                    NoRadioButtonChecked.message(getApplicationContext(), "Please select an option.");
                }
                else {
                    //one of the radio button is checked
                    findTypeOfUserByRadioButton(checkedId);
                    if (validateRegisterInfo(emailEditText.getText().toString(), passwordEditText.getText().toString(), usernameEditText.getText().toString())) {
                        UserEntity userEntity = new UserEntity(emailEditText.getText().toString(),
                                passwordEditText.getText().toString(),
                                usernameEditText.getText().toString(),
                                nameEditText.getText().toString());
                        userEntities.add(userEntity);
                    }

                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private boolean validateRegisterInfo(String email, String password, String username) {
        UserEntity emailCheck = new UserEntity(email, null, null, null, null, null);
        UserEntity passwordCheck = new UserEntity(null, password, null, null, null, null);
        UserEntity usernameCheck = new UserEntity(null, null, username, null, null, null);
        return (userEntities.contains(emailCheck) && userEntities.contains(passwordCheck) && userEntities.contains(usernameCheck));
        //return false;
    }

    private String findTypeOfUserByRadioButton(int checkedId) {
        switch (checkedId) {
            case R.id.userAccountRegisterRadioButton:
                NoRadioButtonChecked.message(getApplicationContext(), "Selected option: USER");
                //return "user";
            case R.id.companyAccountRegisterRadioButton:
                NoRadioButtonChecked.message(getApplicationContext(), "Selected option: COMPANY");
                //return "company";
        }
        return null;
    }
}