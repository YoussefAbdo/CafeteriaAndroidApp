package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.OrderDetailsAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderDetailsModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Splash.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    private final String URL_TO_HIT = "http://cafeteriaapptest.azurewebsites.net/api/order";
    private ListView order_details_list_view;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data

//        // Create default options which will be used for every
//        //  displayImage(...) call if no options will be passed to this method
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//                .defaultDisplayImageOptions(defaultOptions)
//                .build();
//        ImageLoader.getInstance().init(config); // Do it on Application start

        order_details_list_view = (ListView) findViewById(R.id.order_details_list_view);

        new JSONTask().execute(URL_TO_HIT);
    }


    public class JSONTask extends AsyncTask<String, String, List<OrderDetailsModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<OrderDetailsModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("orders");

                List<OrderDetailsModel> orderDetailsModelList = new ArrayList<>();


                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself
                     * which is commented below
                     */
                    OrderDetailsModel orderDetailsModel = gson.fromJson(finalObject.toString(), OrderDetailsModel.class); // a single line json parsing using Gson

                    orderDetailsModelList.add(orderDetailsModel);
                }

                return orderDetailsModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(final List<OrderDetailsModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result != null) {
                OrderDetailsAdapter adapter = new OrderDetailsAdapter(getApplicationContext(), R.layout.order_row_item, result);
                order_details_list_view.setAdapter(adapter);

                order_details_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        OrderDetailsModel orderDetailsModel = result.get(position); // getting the model
                        Intent intent = new Intent(OrderDetailsActivity.this, OrdersActivity.class);
                        intent.putExtra("orderDetailsModel", new Gson().toJson(orderDetailsModel)); // converting model json into string type and sending it via intent
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();

            }
        }
    }
}


