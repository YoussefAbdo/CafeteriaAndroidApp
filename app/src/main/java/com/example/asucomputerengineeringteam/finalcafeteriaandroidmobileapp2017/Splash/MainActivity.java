package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.HomeActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView app_name , order;
    Button start;

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        logo = (ImageView)findViewById(R.id.logo_image);
        logo.setImageResource(R.drawable.app_logo);


     /*    *//* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*//*
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                *//* Create an Intent that will start the Menu-Activity. *//*
                Intent mainIntent = new Intent(MainActivity.this,HomeActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);*/
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}
