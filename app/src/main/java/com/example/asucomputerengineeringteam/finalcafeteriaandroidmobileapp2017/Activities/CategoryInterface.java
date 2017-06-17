package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.CategoryFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class CategoryInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_interface);

        CategoryFragment categoryFragment = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cat_activity,categoryFragment).commit();
    }
}
