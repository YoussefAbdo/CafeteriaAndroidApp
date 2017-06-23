package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.HomeActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash.MainActivity;

/**
 * Created by Esraa Hosny on 6/21/2017.
 */

public class DrawerFragment extends Fragment implements Animation.AnimationListener {

    Animation animFadein;

    public DrawerFragment() {
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.drawer_fragment, container, false);

        TextView text_chef = (TextView)view.findViewById(R.id.text_chef);
        text_chef.setText("Open This");
        animFadein = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        text_chef.startAnimation(animFadein);

        ImageView image_chef = (ImageView)view.findViewById(R.id.image_chef);
        image_chef.setImageResource(R.drawable.chef2);


        return view;

    }
    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == animFadein) {
            Toast.makeText(getContext(), "Animation Stopped",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // Animation is repeating
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // Animation started
    }


}
