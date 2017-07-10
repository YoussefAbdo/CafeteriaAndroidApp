package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MyApplication;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.User;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers.UserJSONParser;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity
{
    EditText name_et;
    EditText email_et;
    EditText phoneNumber_et;
    EditText firstName_et;
    EditText lastName_et;
    ProgressBar pb;
    List<MyTask> tasks;
    List<User> userList;
    TextView output;
    Button editButton;
    Button saveChangesButton;
    Button goToCafeterias;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name_et = (EditText) findViewById(R.id.name);
        email_et = (EditText) findViewById(R.id.email);
        phoneNumber_et = (EditText) findViewById(R.id.phoneNumber);
        firstName_et = (EditText) findViewById(R.id.firstName);
        lastName_et = (EditText) findViewById(R.id.lastName);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        output = (TextView) findViewById(R.id.textView);
        editButton = (Button) findViewById(R.id.editButton);
        saveChangesButton = (Button) findViewById(R.id.saveChangesButton);
        goToCafeterias = (Button) findViewById(R.id.b2);

        tasks = new ArrayList<>();
        disableEditting();
        editButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                enableEditting();
            }
        });

        saveChangesButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                disableEditting();
            }
        });
        goToCafeterias.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent( getApplicationContext(),CafeteriaInterface.class);
                startActivity(intent);
            }
        });

        //don't forget too override this id with the global id
        //String id = "0026d455-0b0b-46db-96d9-a4824d610766";

        MyApplication mApp = ((MyApplication)getApplicationContext());
        String id = mApp.getId();
        if(isOnline())
        {
            requestData("http://cafeteriaappdemo.azurewebsites.net/api/user/" + id);

        }
        else
        {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }
    }

    private void disableEditting()
    {
        name_et.setEnabled(false);
        email_et.setEnabled(false);
        phoneNumber_et.setEnabled(false);
        firstName_et.setEnabled(false);
        lastName_et.setEnabled(false);
    }
    private void enableEditting()
    {
        name_et.setEnabled(true);
        email_et.setEnabled(true);
        phoneNumber_et.setEnabled(true);
        firstName_et.setEnabled(true);
        lastName_et.setEnabled(true);
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

    private void requestData(String uri)
    {
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        MyTask task = new MyTask();
        task.execute(p);
    }

    private class MyTask extends AsyncTask<RequestPackage, String, String>
    {
        protected void onPreExecute()
        {
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
            result = "[" + result.substring(8, result.length() - 2) + "]";
            userList = UserJSONParser.parseFeed(result);
            updateDisplay();
            tasks.remove(this);
            if(tasks.size() == 0)
            {
                pb.setVisibility(View.INVISIBLE);
            }
        }
    }

    protected void updateDisplay()
    {
        if(userList != null)
        {
            for(User user : userList)
            {
                String userName = user.getUserName();
                String email = user.getEmail();
                String phoneNumber = user.getPhoneNumber();
                String firstName = user.getFirstName();
                String lastName = user.getLastName();

                if(!userName.equals("null"))
                    name_et.setText(userName);
                if(!email.equals("null"))
                    email_et.setText(email);

                if(!phoneNumber.equals("null"))
                    phoneNumber_et.setText(phoneNumber);

                if(!firstName.equals("null"))
                    firstName_et.setText(firstName);

                if(!lastName.equals("null"))
                    lastName_et.setText(lastName);
            }
        }
    }
}
