package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.BasketActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CafeteriaInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.ContactUsActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.OrdersActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.SettingsActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Authentication.SignupActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import java.util.ArrayList;
import java.util.List;



public class DrawerActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {


    private FlowingDrawer drawer;
    List<DrawerDataModel> drawerList;
    public static final String[] nav_label = {"Account" , "Cafeterias" , "Orders" , "Basket" , "Settings" , "Logout" ,"Contact us"};
    public static final int[] nav_item = {R.drawable.user , R.drawable.cafeteria_icon , R.drawable.orders , R.drawable.basket , R.drawable.settings_icon , R.drawable.logout_icon, R.drawable.contact_us_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        DrawerFragment drawerFragment = new DrawerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.drawer_activity_fragment , drawerFragment).commit();
        drawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer();
        drawerList = new ArrayList<>();
        drawerListItems();
        DrawerAdapter navDrawerAdapter = new DrawerAdapter(getBaseContext(), R.layout.item_navigation, drawerList);
        ListView navListView = (ListView) findViewById(R.id.list_view_navigation);
        navListView.setAdapter(navDrawerAdapter);
        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nav_label = drawerList.get(position).getLabel();
                String nav_image = String.valueOf(drawerList.get(position).getItem());

                if(nav_label == "Account" || nav_image.equals(R.drawable.user))
                {
                    Intent intent = new Intent(DrawerActivity.this , SignupActivity.class);
                    startActivity(intent);
                }
                if(nav_label == "Cafeterias" || nav_image.equals(R.drawable.cafeteria_icon))
                {
                    Intent intent = new Intent(DrawerActivity.this , CafeteriaInterface.class);
                    startActivity(intent);
                }
                if(nav_label == "Orders" || nav_image.equals(R.drawable.orders))
                {
                    Intent intent = new Intent(DrawerActivity.this , OrdersActivity.class);
                    startActivity(intent);
                }
                if(nav_label == "Basket" || nav_image.equals(R.drawable.basket))
                {
                    Intent intent = new Intent(DrawerActivity.this , BasketActivity.class);
                    startActivity(intent);
                }
                if(nav_label == "Settings" || nav_image.equals(R.drawable.settings_icon))
                {
                    Intent intent = new Intent(DrawerActivity.this , SettingsActivity.class);
                    startActivity(intent);
                }
                if(nav_label == "Logout" || nav_image.equals(R.drawable.logout_icon))
                {
                    Toast.makeText(DrawerActivity.this, "user is logged out", Toast.LENGTH_SHORT).show();
                }
                if(nav_label == "Contact us" || nav_image.equals(R.drawable.contact_us_icon))
                {
                    Intent intent = new Intent(DrawerActivity.this , ContactUsActivity.class);
                    startActivity(intent);
                }

            }
        });
        menuAction();
    }



            @Override
            protected void onResume() {
                super.onResume();
            }

            private void menuAction() {
                ImageButton drawerBtn = (ImageButton) findViewById(R.id.drawer_btn);
                drawerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawer.openMenu();
                    }
                });
               /* ImageView menu_text = (ImageView) findViewById(R.id.menu_text);
                menu_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DrawerActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                });*/

            }

            private void drawerListItems() {
                drawerList = new ArrayList<DrawerDataModel>();
                for (int i = 0; i < nav_label.length; i++) {
                    DrawerDataModel drawerDataModel = new DrawerDataModel(nav_item[i], nav_label[i]);
                    drawerList.add(drawerDataModel);
                }
            }

            private void mDrawer() {
                drawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
                drawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
                drawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
                    @Override
                    public void onDrawerStateChange(int oldState, int newState) {
                        if (newState == ElasticDrawer.STATE_CLOSED) {
                            Log.i("MainActivity", "Drawer STATE_CLOSED");
                        }
                    }

                    @Override
                    public void onDrawerSlide(float openRatio, int offsetPixels) {
                        Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
                    }
                });
            }

            @Override
            public void onBackPressed() {

                if (drawer.isMenuVisible()) {
                    drawer.closeMenu();
                } else {
                    super.onBackPressed();
                }
            }


            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }


}
