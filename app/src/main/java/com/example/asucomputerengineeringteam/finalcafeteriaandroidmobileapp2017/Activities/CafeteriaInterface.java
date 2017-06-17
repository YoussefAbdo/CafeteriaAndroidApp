package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.CafeteriaFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


public class CafeteriaInterface extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_interface);

       CafeteriaFragment mainActivityFragment = new CafeteriaFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.caf_activity,mainActivityFragment).commit();
    }
}



