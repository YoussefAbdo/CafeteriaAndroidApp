package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataBase;

/**
 * Created by Esraa Hosny on 7/6/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HongThai
 */
public class MySharedPreference
{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Information";
    private static final String Data = "Data";

    public MySharedPreference(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveHighScoreList(String scoreString)
    {
        editor.putString(Data, scoreString);
        editor.commit();
    }

    public String getHighScoreList()
    {
        return pref.getString(Data, "");
    }
}