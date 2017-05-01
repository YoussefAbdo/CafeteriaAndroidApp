package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;


public class BasketActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private ListView basket_list;
    private Button add_more_button;
    private Button edit_button;
    private EditText comments_edit_text;
    private TextView total_title_text_view;
    private TextView total_text_view;
    private TextView loction_text_view;
    private EditText location_edit_text;
    private ImageView gps_image_view;
    private RadioButton cash_radio_button;
    private RadioButton wallet_radio_button;
    private Button order_button;

    private final String TAG = "Location BasketActivity";
    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        // setting up text views and stuff
        setUpUIViews();
        buildGoogleApiClient();

//        public List<String> arrayList = new ArrayList<>();
//        final String menuitem_name = getIntent().getExtras().getString("menuitem_name");
//        final String menuitem_price = getIntent().getExtras().getString("menuitem_price");
//        final String addition_name = getIntent().getExtras().getString("addition_name");
//
//        List<String> menuitems = new List<String> ;
//        menuitems.add(menuitem_name);
//        List<String> additions = new List<String> ;
//        menuitems.add(menuitem_price);
//        basketAdapter = new BasketAdapter(Basket.this,menuitems , additions);
//        basket_list.setAdapter(basketAdapter);
//        basketAdapter.notifyDataSetChanged();

        add_more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent(BasketActivity.this , MenuItemsActivity.class);
//                startActivity(intent1);
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(BasketActivity.this, EditActivity.class);
                startActivity(intent2);
            }
        });

        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gps_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BasketActivity.this, "We got your location", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // connect the client}
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

*
     * Runs when a GoogleApiClient object successfully connects.


    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            getLatitudeAndLongitude();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    if (mLastLocation != null) {
                        getLatitudeAndLongitude();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, location.toString());
        //txtOutput.setText(location.toString());
        getLatitudeAndLongitude();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

* Called by Google Play services if the connection to GoogleApiClient drops because of an
* error.


    public void onDisconnected() {
        Log.i(TAG, "Disconnected");
    }

    private void setUpUIViews() {
        basket_list = (ListView) findViewById(R.id.basket_list);
        add_more_button = (Button) findViewById(R.id.add_more_button);
        edit_button = (Button) findViewById(R.id.edit_button);
        comments_edit_text = (EditText) findViewById(R.id.comments_edit_text);
        total_title_text_view = (TextView) findViewById(R.id.total_title_text_view);
        total_text_view = (TextView) findViewById(R.id.total_text_view);
        loction_text_view = (TextView) findViewById(R.id.loction_text_view);
        location_edit_text = (EditText) findViewById(R.id.location_edit_text);
        gps_image_view = (ImageView) findViewById(R.id.gps_image_view);
        cash_radio_button = (RadioButton) findViewById(R.id.cash_radio_button);
        wallet_radio_button = (RadioButton) findViewById(R.id.wallet_radio_button);
        order_button = (Button) findViewById(R.id.order_button);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void getLatitudeAndLongitude() {
        location_edit_text.setText(String.valueOf(mLastLocation.getLatitude() + "," + String.valueOf(mLastLocation.getLongitude())));
    }
}











