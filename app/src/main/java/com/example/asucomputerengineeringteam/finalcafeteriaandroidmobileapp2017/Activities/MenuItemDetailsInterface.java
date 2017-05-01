package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.CafeteriaFragment;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class MenuItemDetailsInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_details_interface);

        Toast.makeText(MenuItemDetailsInterface.this, "ok right interface", Toast.LENGTH_SHORT).show();
       /* MenuItemDetailsFragment menuItemDetailsFragment = new MenuItemDetailsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.men_details_activity,menuItemDetailsFragment).commit();*/
    }
}
