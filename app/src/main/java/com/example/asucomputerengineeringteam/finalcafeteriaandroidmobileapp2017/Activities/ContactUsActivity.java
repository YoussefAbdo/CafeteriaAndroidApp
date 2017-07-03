package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import static com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R.id.send_email_4;

public class ContactUsActivity extends AppCompatActivity {

    Button send_email_2,send_email_4;
    //yahoo,gmail,hotmail,facebook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        send_email_2 = (Button)findViewById(R.id.send_email_2);
        send_email_4 = (Button)findViewById(R.id.send_email_4);

        send_email_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "mryoussefjoe@gmail.com");
                intent.putExtra(Intent.EXTRA_CC, "mryoussefjoe@gmail.com");
                intent.putExtra(Intent.EXTRA_TEXT, "Dear developer,");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        send_email_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW ,Uri.parse("https://www.facebook.com/groups/181138878994734/")));
            }
        });
    }
}
