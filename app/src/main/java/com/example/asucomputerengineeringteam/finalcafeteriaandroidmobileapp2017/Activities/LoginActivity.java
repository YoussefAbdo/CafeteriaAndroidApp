package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.LoginResponse;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers.LoginResponseJSONParser;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    TextView registerLink;
    Button loginButton;
    Button cancelButton;
    EditText EmailEditText, PasswordEditText;
    List<MyTask> tasks;
    List<LoginResponse> loginResponseList ;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailEditText = (EditText) findViewById(R.id.loginEmailEditText);
        PasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        registerLink = (TextView)findViewById(R.id.textview2);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<MyTask>();


        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    if(isOnline())
                        requestData("http://cafeteriaappdemo.azurewebsites.net/api/Account/Login");
                    else
                        Toast.makeText(getApplicationContext(), "Network is not available", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong Attempt",Toast.LENGTH_SHORT).show();
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


    private void requestData(String uri)
    {
        RequestPackage p = new RequestPackage();

        p.setMethod("POST");
        p.setUri(uri);

        p.setParam("Username", EmailEditText.getText().toString());
        p.setParam("Password", PasswordEditText.getText().toString());
        p.setParam("RemmberMe", "false");

        MyTask task = new MyTask();
        //you can pass as many parameters as you want
        task.execute(p);
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
                Toast.makeText(getApplicationContext(), "The user name or password is incorrect.",Toast.LENGTH_SHORT).show();
            }
            else if(result.contains("access_token"))
            {
                //loginResponseList = LoginResponseJSONParser.parseFeed("[" + result + "]");
                Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( getApplicationContext(),CafeteriaInterface.class);
                startActivity(intent);
            }


            tasks.remove(this);
            if(tasks.size() == 0)
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            // updateDisplay(values[0]);
        }
    }

}


