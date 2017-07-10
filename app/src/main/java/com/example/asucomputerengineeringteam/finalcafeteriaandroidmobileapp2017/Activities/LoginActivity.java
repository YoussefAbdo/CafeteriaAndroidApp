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
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MyApplication;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers.LoginResponseJSONParser;

import java.util.ArrayList;
import java.util.List;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.UserInfo;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers.UserInfoJSONParser;



public class LoginActivity extends AppCompatActivity {

    TextView registerLink;
    TextView output;
    Button loginButton;
    Button cancelButton;
    EditText EmailEditText, PasswordEditText;
    List<MyTask> tasks;
    List<LoginResponse> loginResponseList;
    ProgressBar pb;
    String id = "";
    String accessToken = "";
    List<UserInfo> userInfoList;
    boolean accessed = false;


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
        output = (TextView) findViewById(R.id.textView3);

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
                    {
                        requestData("http://cafeteriaappdemo.azurewebsites.net/api/Account/Login");
                    }

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
    // this method requests id of user
    private void requestData2(String uri)
    {
        RequestPackage p2 = new RequestPackage();
        p2.setMethod("GET");
        p2.setUri(uri);
        MyTask2 task2 = new MyTask2();

        task2.execute(p2);
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
                tasks.clear();
                if(tasks.size() == 0)
                {
                    pb.setVisibility(View.INVISIBLE);
                }
            }
            else if(result.contains("access_token"))
            {
                loginResponseList = LoginResponseJSONParser.parseFeed("[" + result + "]");
                accessToken = loginResponseList.get(0).getToken_type() + " " + loginResponseList.get(0).getAccess_token();
                requestData2("http://cafeteriaappdemo.azurewebsites.net/api/Account/userinfo");
            }
        }
    }

    private class MyTask2 extends AsyncTask <RequestPackage, String, String>
    {

        protected void onPreExecute() {
            HttpManager2.authorization = accessToken;
            //HttpManager2.authorization = "bearer EJuWL-TMgOIxORwtIo7hXqjEGMMBaH3Lkg8Rpn_8Vbwe239XCNvFVWowQzOzVcOupi_j5dghKcpiHY3d9ARFnzuBxEWkfbaIOviMPIrcf8l-A4C8mGTXFe91wS1Hsd69Gmw9esGYabuUDRZVPwTDxeO-CEM2Vnz-emuz9hy86X4Jidh1ZB7puMca2HGjlsHqlCmn3jAUVyCy8AFNeXdI9I_Q_925sdNG0tUQjAEDOzna9_fzv4dOUmhu7QRPT9OTxCsm6FgMF2Gc7uQQekJ8iF6Qpc-9K810cx3yguI9ArfKqaZYUEq6JikqJj1wgsxhQ6_rX0rkNNegOGFyluyqYviP7_XOhE3ibFc0CErAN0EMPOZ1UR_XLa7Lu39oQjwLw-DPjNnZjW326TbUoBo-v0sGRA0Zw1XPW-VrWbN-W1dXaMGzDZAv_f3Fwj9A70MkZP_avnrnj8WFyEauH0sEKA1foPkNVzTIkPct6YDx6t0raE97Phyac96EqCoHngfP4QRPIB31cng0_zbQ3j8W3w";
        }

        protected String doInBackground(RequestPackage... params)
        {
            String content = HttpManager2.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result)
        {
            userInfoList = UserInfoJSONParser.parseFeed("[" + result + "]");
            id = userInfoList.get(0).getUserId();

            saveID(id);

           // MyApplication mApp = ((MyApplication)getApplicationContext());
            //output.append(mApp.getId());


            tasks.clear();
            if(tasks.size() == 0)
            {
                pb.setVisibility(View.INVISIBLE);
            }
            //Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent( getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }
    }

    // saves user id throught all the application
    //u have to log in so that this method executes
    private void saveID(String id)
    {
        MyApplication mApp = ((MyApplication)getApplicationContext());
        mApp.setId(id);
    }


}


