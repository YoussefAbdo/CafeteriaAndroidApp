package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.AdditionAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
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
import java.util.ArrayList;
import java.util.List;

public class DetailsMenuItem extends AppCompatActivity {

    public AdditionAdapter additionAdapter;
    public List<AdditonModel> additonModels = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
    ImageView image;
    TextView item_name , item_description , item_type , item_price , quantity_text , addition_text ,quantity_title_text_view;
    Button add , remove , basket;
    String[] additionItems = {"Cheese" , "Mayonaise" ,"Katchup" , "salt" , "pepper", "sous"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_menu_item);

        dialog = new ProgressDialog(DetailsMenuItem.this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");

        recyclerView = (RecyclerView)findViewById(R.id.addition_recyclerView);
        //instantiation of ui
        image = (ImageView)findViewById(R.id.photo_image_view);
        item_description = (TextView)findViewById(R.id.item_description);
        item_name = (TextView)findViewById(R.id.item_name);
        item_price = (TextView)findViewById(R.id.item_price);
        item_type = (TextView)findViewById(R.id.item_type);
        addition_text = (TextView)findViewById(R.id.additions_text_view);
        quantity_title_text_view = (TextView)findViewById(R.id.quantity_title_text_view);
        quantity_text = (TextView)findViewById(R.id.quantity_text);
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        basket = (Button)findViewById(R.id.basket);

        //getting data by intent
       /* final String photo = getIntent().getExtras().getString("photo");
        final String name = getIntent().getExtras().getString("name");
        final String description = getIntent().getExtras().getString("description");
        final String type = getIntent().getExtras().getString("type");
        final String price = getIntent().getExtras().getString("price");*/
        //filling ui with data
        item_name.setText("Item name is : " +"King Burger");
        item_description.setText("Item description is : " + "Grilled beef burger patties, cheese slice, lettuce, onion, pickles and sauce, serve in 2 layers of bun bread");
        item_type.setText("Item type is : " + "non_vegeterian");
        item_price.setText("Item price is : " + "20");


      /*  final int[] counter = {0};
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity_text.setText(String.valueOf(counter[0]++));
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity_text.setText(String.valueOf(counter[0]));
            }
        });*/

        new JsonTaskAddition().execute("http://cafeteriaapptest.azurewebsites.net/api/addition");
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(DetailsMenuItem.this,BasketActivity.class );
                startActivity(intent);*/
                Toast.makeText(DetailsMenuItem.this, "your item is added in basket", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public class JsonTaskAddition extends AsyncTask<String, String, List<AdditonModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
        @Override
        protected List<AdditonModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String finalJson = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                if (stream == null) {
                    // Nothing to do.
                    return null;
                }
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                finalJson = buffer.toString();
                Log.v("finalJSON", finalJson);
            } catch (IOException e1) {
                Log.e("LOG", "error", e1);
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioe) {
                        //log statement or any message
                        Log.e("LOG", "error", ioe);
                    }
                }
                try {
                    return getData(finalJson);

                } catch (JSONException e) {
                    //e.printStackTrace();
                    Log.e("LOG", "error", e);
                }
                return null;
            }
        }

        private List<AdditonModel> getData(String jsontoString) throws JSONException {
            JSONObject parentObject = new JSONObject(jsontoString);
            JSONArray parentArray = parentObject.getJSONArray("additions");
            String name = "Name";
            String id = "Id";
            for (int i = 0; i < parentArray.length(); i++) {
                AdditonModel additonModel = new AdditonModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                additonModel.setName(finalObject.getString(name));

                //adding the final object in the list
                additonModels.add(additonModel);
            }
            Log.v("size", additonModels.size() + "");
            // return cafeteriaModelList;
            return additonModels;
        }
        @Override
        protected void onPostExecute(List<AdditonModel> additonModels) {
            super.onPostExecute(additonModels);
            dialog.dismiss();
            if (additonModels != null) {
                layoutManager = new LinearLayoutManager(DetailsMenuItem.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                additionAdapter = new AdditionAdapter(DetailsMenuItem.this, additonModels,additionItems);
                recyclerView.setAdapter(additionAdapter);
            } else {
                Toast.makeText(DetailsMenuItem.this, "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
