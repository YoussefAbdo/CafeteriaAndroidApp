package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.MenuDetailsFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.MenuItemFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Interfaces.MenuItemListener;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


public class MenuItemInterface extends AppCompatActivity implements MenuItemListener {


    boolean isTwopane =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_interface);

        MenuItemFragment menuItemFragment = new MenuItemFragment();
      //  getSupportFragmentManager().beginTransaction().add(R.id.men_activity, menuItemFragment).commit();

        menuItemFragment.setItems(this);
        if(getSupportFragmentManager().findFragmentById(R.id.men_activity) != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.men_activity, menuItemFragment).commit();
        }
        else {
            getSupportFragmentManager().beginTransaction().add(R.id.men_activity, menuItemFragment).commit();
        }


        //check of tablet
        if(null!= findViewById(R.id.detail_activity))
            isTwopane = true;
    }
    @Override
    public void setSelectedMenuItem(String menu_image, String menu_name, String menu_price,
                                    String menu_reviews, String menu_description, String menu_type, String menu_ids) {
        //one pane
        if(!isTwopane) {
            Intent intent = new Intent(MenuItemInterface.this,MenuDetailInterface.class);
            intent.putExtra("id", menu_ids);
            intent.putExtra("name",menu_name);
            intent.putExtra("price", menu_price);
            intent.putExtra("description",menu_description);
            intent.putExtra("type",menu_type);
            intent.putExtra("image_url", menu_image);
            intent.putExtra("alternate_text",menu_reviews);
            startActivity(intent);
        }
        else {
            //two pane
            MenuDetailsFragment menuDetailsFragment = new MenuDetailsFragment();
            Bundle extras = new Bundle();
            extras.putString("id", String.valueOf(menu_ids));
            extras.putString("name",menu_name);
            extras.putString("price", String.valueOf(menu_price));
            extras.putString("description",menu_description);
            extras.putString("type",menu_type);
            extras.putString("image_url", menu_image);
            extras.putString("alternate_text",menu_reviews);
            menuDetailsFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_activity,menuDetailsFragment).commit();
        }
    }



}



