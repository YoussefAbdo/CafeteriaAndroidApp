package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class Congratulation extends AppCompatActivity {

    Button button;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent = new Intent( getApplicationContext(), CafeteriaInterface.class);
                startActivity(intent);
            }
        });
    }
}
