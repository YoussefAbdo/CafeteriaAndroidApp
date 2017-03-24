// THIS CLASS MUST BE DELETED
// IT HAS NO USE



package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;


import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.OrdersAdapter;
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrdersModel;
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
////import com.google.android.gms.maps.model.LatLng;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrdersModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.R.attr.id;

public class OrdersActivity extends AppCompatActivity {

//    public OrdersAdapter ordersAdapter;
//    public List<OrdersModel> ordersModelList = new ArrayList<>();
//    ListView list;
//    Button load;
//    Calendar calendar;
//    private ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_orders);
//
//        list = (ListView) findViewById(R.id.list);
//        load = (Button) findViewById(R.id.load);
//        load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new JsonTask4().execute("http://cafeteriaapptest.azurewebsites.net/api/order");
//                ordersAdapter = new OrdersAdapter(getApplicationContext(), ordersModelList);
//                list.setAdapter(ordersAdapter);
//                ordersAdapter.notifyDataSetChanged();
//            }
//        });
//
////        SimpleDateFormat parserSDF=new SimpleDateFormat("M/d/yyyy h:m:s a");
////        Date d = parserSDF.parse(dateField,0);
//    }
//
//    public class JsonTask4 extends AsyncTask<String, Void, List<OrdersModel>> {
//
//        @Override
//        protected List<OrdersModel> doInBackground(String... params) {
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//            String finalJson = null;
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.connect();
//                InputStream stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
//                if (stream == null) {
//                    // Nothing to do.
//                    return null;
//                }
//                StringBuffer buffer = new StringBuffer();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line);
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//                }
//                if (buffer.length() == 0) {
//                    // Stream was empty.  No point in parsing.
//                    return null;
//                }
//                finalJson = buffer.toString();
//                Log.v("finalJSON", finalJson);
//            } catch (IOException e1) {
//                Log.e("LOG", "error", e1);
//                return null;
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException ioe) {
//                        //log statement or any message
//                        Log.e("LOG", "error", ioe);
//                    }
//                }
//                try {
//                    return getData(finalJson);
//                } catch (JSONException e) {
//                    //e.printStackTrace();
//                    Log.e("LOG", "error", e);
//                }
//                return null;
//            }
//        }
//
//        private List<OrdersModel> getData(String jsontoString) throws JSONException {
//            JSONObject parentObject = new JSONObject(jsontoString);
//            JSONArray parentArray = parentObject.getJSONArray("orders");
//            String Id = "Id";
//            Time DeliveryTime = Time.valueOf("DeliveryTime");
//            boolean PaymentDone = Boolean.parseBoolean("PaymentDone");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ITALY);
//            calendar = Calendar.getInstance();
//            String strDate = dateFormat.format(calendar.getTime());
//            String PaymnetMethod = "PaymnetMethod";
//            //LatLng DeliveryPlace = "DeliveryPlace";
//            String OrderStatus = "OrderStatus";
//
//
//            for (int i = 0; i < parentArray.length(); i++) {
//                OrdersModel ordersModel = new OrdersModel();
//                JSONObject finalObject = parentArray.getJSONObject(i);
//                ordersModel.setId(finalObject.getInt(String.valueOf(id)));
//                ordersModel.setOrderStatus(String.valueOf(finalObject.getInt(OrderStatus)));
//                ordersModel.setOrderTime(Time.valueOf(String.valueOf(finalObject.getInt((strDate)))));
//                ordersModel.setPaymentMethod(String.valueOf(finalObject.getInt(PaymnetMethod)));
////                ordersModel.setDeliveryPlace(finalObject.getInt(DeliveryPlace);
//                //ordersModel.setDeliverytTime(finalObject.getInt(String.valueOf(DeliveryTime)));
//                // String [] values = strDate.split("/",0);
//                //ordersModel.setPaymentDone(finalObject.getInt(String.valueOf((PaymentDone))));
//
//                //adding the final object in the list
//                ordersModelList.add(ordersModel);
//            }
//            return ordersModelList;
//        }
//    }

}
