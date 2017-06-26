package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Esraa Hosny on 3/10/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";


    @Override
    public void onTokenRefresh() {

        String recent_token = FirebaseInstanceId.getInstance().getToken();

        Log.d(REG_TOKEN, recent_token);
    }
}
