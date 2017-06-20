package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CategoryDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.MenuDetailsFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash.MainActivity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button b ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(R.color.dark_red);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home)
        {
            Toast.makeText(HomeActivity.this , "You are in home page" , Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.nav_logout)
        {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_help)
        {
          Intent intent = new Intent(HomeActivity.this , HelpActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_contact)
        {
            Toast.makeText(HomeActivity.this , "contact feedback center" , Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_orders)
        {
            Intent intent = new Intent(HomeActivity.this, OrderDetailsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_cafeterias)
        {
            Intent intent = new Intent(HomeActivity.this,CafeteriaInterface.class);
            startActivity(intent);
         //  Toast.makeText(HomeActivity.this , "contact feedback center" , Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_account)
        {
            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_basket)
        {
            Intent intent = new Intent(HomeActivity.this,BasketActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}