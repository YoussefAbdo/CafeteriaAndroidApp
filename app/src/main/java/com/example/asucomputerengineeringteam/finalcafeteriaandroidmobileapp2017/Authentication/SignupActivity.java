package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Authentication;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoriesActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


public class SignupActivity extends AppCompatActivity
{
    TextView loginLink;
    Intent intent;
    Button createAccountButton;
    EditText NameEditText, EmailEditText, PasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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


        loginLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intent = new Intent( getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( getApplicationContext(),CategoriesActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Attempt",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate()
    {
        boolean valid = true;

        String name = NameEditText.getText().toString();

        String email = EmailEditText.getText().toString();
        String password = PasswordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailEditText.setError("enter a valid email address");
            valid = false;
        }
        else
        {
            EmailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6)
        {
            PasswordEditText.setError("password length should be greater than or equal 6");
            valid = false;
        }
        else
        {
            PasswordEditText.setError(null);
        }

        return valid;
    }
}


