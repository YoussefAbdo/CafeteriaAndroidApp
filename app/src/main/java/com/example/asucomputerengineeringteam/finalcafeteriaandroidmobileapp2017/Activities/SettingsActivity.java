package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

public class SettingsActivity extends AppCompatActivity {


    TextView languageTextView,fontTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        languageTextView = (TextView)findViewById(R.id.languageTextView);
        fontTextView = (TextView)findViewById(R.id.fontTextView) ;

        Spinner languageSpinner = (Spinner)findViewById(R.id.languageSpinner);
        String[] languages = new String[]{"         English", "         Arabic"};
        ArrayAdapter<String> languagesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        languageSpinner.setAdapter(languagesAdapter);


        Spinner fontSpinner = (Spinner)findViewById(R.id.fontSpinner);
        String[] fonts = new String[]{"         Bold", "         Italic"};
        ArrayAdapter<String> fontsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fonts);
        fontSpinner.setAdapter(fontsAdapter);
    }
}
