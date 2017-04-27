/*
package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Authentication;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


public class SignupActivity extends AppCompatActivity {
    TextView loginLink;
    Intent intent;
    Button createAccountButton;
    EditText NameEditText, EmailEditText, PasswordEditText, ConfirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginLink = (TextView) findViewById(R.id.loginTextViewLink);
        createAccountButton = (Button) findViewById(R.id.CreateAccountButton);
        NameEditText = (EditText) findViewById(R.id.NameEditText);
        EmailEditText = (EditText) findViewById(R.id.EmailEditText);
        PasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
        ConfirmPasswordEditText = (EditText) findViewById(R.id.ConfirmPasswordEditText);

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Attempt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String name = NameEditText.getText().toString();

        String email = EmailEditText.getText().toString();
        String password = PasswordEditText.getText().toString();
        String confirmedPassword = ConfirmPasswordEditText.getText().toString();

        Animation shake = AnimationUtils.loadAnimation(SignupActivity.this, R.anim.shake);

        if (name.isEmpty()) {
            NameEditText.startAnimation(shake);
            NameEditText.setError("enter a valid user name");
            valid = false;
        } else {
            NameEditText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailEditText.startAnimation(shake);
            EmailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            EmailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            PasswordEditText.startAnimation(shake);
            PasswordEditText.setError("password length should be greater than or equal 6");
            valid = false;
        } else {
            PasswordEditText.setError(null);
        }

        if (confirmedPassword.isEmpty()) {
            ConfirmPasswordEditText.startAnimation(shake);
            ConfirmPasswordEditText.setError("Enter the password again");
            valid = false;
        } else if (!confirmedPassword.equals(password)) {
            ConfirmPasswordEditText.startAnimation(shake);
            ConfirmPasswordEditText.setError("the password and confirmed password are not equal");
            valid = false;
        } else {
            ConfirmPasswordEditText.setError(null);
        }

        return valid;
    }
}


*/
