package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class ContactUsActivity extends AppCompatActivity {

    Button send_email_1,send_email_2,send_email_3,send_email_4;
    //yahoo,gmail,hotmail,facebook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);



        send_email_1 = (Button)findViewById(R.id.send_email_1);
        send_email_2 = (Button)findViewById(R.id.send_email_2);
        send_email_3 = (Button)findViewById(R.id.send_email_3);
        send_email_4 = (Button)findViewById(R.id.send_email_4);


        send_email_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://maktoob.yahoo.com/?p=us")));
            }
        });
        send_email_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW ,Uri.parse("https://accounts.google.com/signin")));
            }
        });

        send_email_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW ,Uri.parse("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1498398671&rver=6.7.6643.0&wp=MBI_SSL_SHARED&wreply=https:%2F%2Fmail.live.com%2Fdefault.aspx&lc=1033&id=64855&mkt=en-us&cbcxt=mai")));

            }
        });

        send_email_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW ,Uri.parse("https://www.facebook.com/")));

            }
        });



    }
}
