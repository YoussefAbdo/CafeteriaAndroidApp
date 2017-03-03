package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.HomeActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class MainActivity extends AppCompatActivity {

    ImageView image , go_button ;
    TextView app_name , order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.image);
        app_name = (TextView)findViewById(R.id.app_name);
        order = (TextView)findViewById(R.id.order);
        go_button = (ImageView)findViewById(R.id.go_button);


        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
