package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.HomeActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer.DrawerActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class MainActivity extends AppCompatActivity {

    ImageView logo , image;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.image);
        image.setImageResource(R.drawable.frame_image_modified2);
        image.setAlpha(120);

        logo = (ImageView)findViewById(R.id.logo_image);
        logo.setImageResource(R.drawable.app_logo_final);

        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainActivity.this,DrawerActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}
