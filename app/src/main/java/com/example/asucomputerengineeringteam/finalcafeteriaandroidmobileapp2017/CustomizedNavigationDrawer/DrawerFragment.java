package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.HomeActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash.MainActivity;

/**
 * Created by Esraa Hosny on 6/21/2017.
 */

public class DrawerFragment extends Fragment {

    public DrawerFragment() {
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.drawer_fragment, container, false);


        return view;

    }


}
