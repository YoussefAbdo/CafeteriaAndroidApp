package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.menuitemAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemModel;
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


public class MenuItemsActivity extends AppCompatActivity {

    ListView list;
    public menuitemAdapter menuitemAdapter;
    public List<MenuItemModel> arrayList = new ArrayList<>();
    Button load ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);
        final String id = getIntent().getExtras().getString("idCat");
        list = (ListView) findViewById(R.id.list);
        load = (Button)findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JsonTask3().execute("http://cafeteriaapptest.azurewebsites.net/api/menuitem/GetByCategory/"+id);
                menuitemAdapter = new menuitemAdapter(getApplicationContext(), arrayList);
                list.setAdapter(menuitemAdapter);
                menuitemAdapter.notifyDataSetChanged();
            }
        });
    }
    public class JsonTask3 extends AsyncTask<String, Void, List<MenuItemModel>> {

        @Override
        protected List<MenuItemModel> doInBackground(String... params) {
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

        private List<MenuItemModel> getData(String jsontoString) throws JSONException {

            JSONObject parentObject = new JSONObject(jsontoString);
            JSONArray parentArray = parentObject.getJSONArray("menuItems");
            // List<MenuItemModel> menuItemModelList = new ArrayList<>();
            String name = "Name";
            String price = "Price";
            for (int i = 0; i < parentArray.length(); i++) {
                MenuItemModel menuItemModel = new MenuItemModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                menuItemModel.setName(finalObject.getString(name));
                menuItemModel.setName(finalObject.getString(price));

                //adding the final object in the list
                arrayList.add(menuItemModel);
            }
            Log.d("name data", name);
            return arrayList;
        }
    }
}

