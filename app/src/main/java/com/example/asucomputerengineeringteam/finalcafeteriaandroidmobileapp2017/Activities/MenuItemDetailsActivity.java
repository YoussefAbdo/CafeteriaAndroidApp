package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.AdditionAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.menuitemAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MenuItemDetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView item_name , item_description , item_type , item_price , quantity_text;
   // ListView addition_list ;
    Button add , remove , basket;
    ListView addition_list;
    public AdditionAdapter additionAdapter ;
    public List<AdditonModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_details);

        //instantiation of ui
        image = (ImageView)findViewById(R.id.image);
        item_name = (TextView)findViewById(R.id.item_name);
        item_description = (TextView)findViewById(R.id.item_description);
        item_price = (TextView)findViewById(R.id.item_price);
        item_type = (TextView)findViewById(R.id.item_type);
        addition_list = (ListView)findViewById(R.id.addition_list);
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        quantity_text = (TextView)findViewById(R.id.quantity_text);
        basket = (Button)findViewById(R.id.basket);


        //getting data by intent
        final String photo = getIntent().getExtras().getString("photo");
        final String name = getIntent().getExtras().getString("name");
        final String description = getIntent().getExtras().getString("description");
        final String type = getIntent().getExtras().getString("type");
        final String price = getIntent().getExtras().getString("price");


        //filling ui with data
        item_name.setText("Item name is : " + name);
        item_description.setText("Item description is : " + description);
        item_type.setText("Item type is : " + type);
        item_price.setText("Item price is : " + price);


       /* final int[] counter = {0};
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


        //http://cafeteriaapptest.azurewebsites.net/api/addition

        new JsonTask4().execute("http://cafeteriaapptest.azurewebsites.net/api/addition");
       additionAdapter = new AdditionAdapter(getApplicationContext(), arrayList);
        addition_list.setAdapter(additionAdapter);
        additionAdapter.notifyDataSetChanged();


        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuItemDetailsActivity.this ,BasketActivity.class );
                startActivity(intent);
            }
        });
    }
    public class JsonTask4 extends AsyncTask<String, Void, List<AdditonModel>> {

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
            JSONArray parentArray = parentObject.getJSONArray("menuItems");
            // List<MenuItemModel> menuItemModelList = new ArrayList<>();
            String name = "Name";
            for (int i = 0; i < parentArray.length(); i++) {
                AdditonModel additionModel = new AdditonModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                additionModel.setName(finalObject.getString(name));


                //adding the final object in the list
                arrayList.add(additionModel);
            }
            Log.d("name data", name);
            return arrayList;
        }
    }
}
