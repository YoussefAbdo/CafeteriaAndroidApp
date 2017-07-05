package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {
    TextView loginLink;
    Intent intent;
    Button createAccountButton;
    EditText NameEditText, EmailEditText, PasswordEditText, ConfirmPasswordEditText;

    TextView output;
    ProgressBar pb;
    List<MyTask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialize the TextView for vertical scrolling
        output = (TextView) findViewById(R.id.textView2);
        output.setMovementMethod(new ScrollingMovementMethod());

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<MyTask>();


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

        createAccountButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (validate())
                {
                    if(isOnline())
                    {
                        requestData("http://cafeteriaappdemo.azurewebsites.net/api/Account/Register");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Network is not available", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Attempt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void requestData(String uri)
    {
        RequestPackage p = new RequestPackage();

        p.setMethod("POST");
        p.setUri(uri);

        // key should be all a lower case with no spaces, but value could be any thing
        p.setParam("Email", EmailEditText.getText().toString());
        p.setParam("Password", PasswordEditText.getText().toString());
        p.setParam("ConfirmPassword", ConfirmPasswordEditText.getText().toString());

        MyTask task = new MyTask();
        //you can pass as many parameters as you want
        task.execute(p);
    }

    protected void updateDisplay(String message)
    {
       output.append(message + "\n");
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean validate()
    {
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

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailEditText.startAnimation(shake);
            EmailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            EmailEditText.setError(null);
        }

        String stringPassword = PasswordEditText.getText().toString();
        if (password.isEmpty() || password.length() < 6)
        {
            PasswordEditText.startAnimation(shake);
            PasswordEditText.setError("password length should be greater than or equal 6");
            valid = false;
        }
        else if(!containsSpecialCharacters(stringPassword) || !containsDigit(stringPassword) || !containsUpperCaseLetters(stringPassword) || !containsLowerCaseLetters(stringPassword))
        {
            PasswordEditText.startAnimation(shake);
            PasswordEditText.setError("Passwords must have at least one non letter or digit character. Passwords must have at least one lowercase ('a'-'z'). Passwords must have at least one uppercase ('A'-'Z')");
            valid = false;
        }
        else
        {
            PasswordEditText.setError(null);
        }

        if (confirmedPassword.isEmpty())
        {
            ConfirmPasswordEditText.startAnimation(shake);
            ConfirmPasswordEditText.setError("Enter the password again");
            valid = false;
        }
        else if (!confirmedPassword.equals(password)) {
            ConfirmPasswordEditText.startAnimation(shake);
            ConfirmPasswordEditText.setError("the password and confirmed password are not equal");
            valid = false;
        }
        else
        {
            ConfirmPasswordEditText.setError(null);
        }

        return valid;
    }

    private boolean containsDigit(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isDigit(str.charAt(i)))
                return true;
        }
        return false;
    }

    private boolean containsLowerCaseLetters(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isLowerCase(str.charAt(i)))
                return true;
        }
        return false;
    }
    private boolean containsUpperCaseLetters(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isUpperCase(str.charAt(i)))
                return true;
        }
        return false;
    }

    // checks if str contains any character that is not  a letter and not a digit
    private boolean containsSpecialCharacters(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(!Character.isLetter(str.charAt(i)) && !Character.isDigit(str.charAt(i)))
            {
                return true;
            }

        }
        return false;
    }

    //private class MyTask extends AsyncTask<Params, Progress, Result>
    private class MyTask extends AsyncTask<RequestPackage, String, String>
    {

        protected void onPreExecute() {

            if(tasks.size() == 0)
            {
                pb.setVisibility(View.VISIBLE);
                tasks.add(this);
            }
        }

        protected String doInBackground(RequestPackage... params)
        {
            String content = HttpManager.getData(params[0]);

            return content;
        }

        protected void onPostExecute(String result)
        {
            if(result == null)
            {
                Toast.makeText(getApplicationContext(), "email: " + EmailEditText.getText().toString() + " is already taken", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(result.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), CafeteriaInterface.class);
                    startActivity(intent);
                }
                else
                {
                    updateDisplay(result);
                }
            }
            tasks.remove(this);
            if(tasks.size() == 0)
            {
                pb.setVisibility(View.INVISIBLE);
            }

            //updateDisplay("Task Completed");
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            // updateDisplay(values[0]);
        }
    }
}



