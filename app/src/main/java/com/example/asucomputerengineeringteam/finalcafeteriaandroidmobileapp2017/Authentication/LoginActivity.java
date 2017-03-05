package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoriesActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


public class LoginActivity extends AppCompatActivity {

    TextView registerLink;
    Button loginButton;
    Button cancelButton;
    EditText EmailEditText, PasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailEditText = (EditText) findViewById(R.id.loginEmailEditText);
        PasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        registerLink = (TextView)findViewById(R.id.textview2);


        loginButton.setOnClickListener(new View.OnClickListener()
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

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent( getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean validate()
    {
        boolean valid = true;

        String email = EmailEditText.getText().toString();
        String password = PasswordEditText.getText().toString();

        Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailEditText.startAnimation(shake);
            EmailEditText.setError("enter a valid email address");
            valid = false;
        }
        else
        {
            EmailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6)
        {
            PasswordEditText.startAnimation(shake);
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
