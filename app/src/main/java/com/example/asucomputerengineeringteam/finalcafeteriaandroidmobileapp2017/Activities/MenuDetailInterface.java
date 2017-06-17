package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.MenuDetailsFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class MenuDetailInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail_interface);

        MenuDetailsFragment menuDetailsFragment = new MenuDetailsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.detail_activity,menuDetailsFragment).commit();
    }
}
