package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CategoryModel;
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

public class CafeteriaActivity extends AppCompatActivity {

    // TextView tvJsonItem ;
    ListView listView;
    public CafeteriaAdapter adapter;
    public List<CafeteriaModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);

        listView = (ListView) findViewById(R.id.list);

        //  tvJsonItem = (TextView) findViewById(R.id.tvJsonIitem);
        Button btnHit = (Button) findViewById(R.id.btnHit);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new JsonTask().execute("http://cafeteriaapptest.azurewebsites.net/api/menuitem");
                new JsonTask().execute("http://cafeteriaapptest.azurewebsites.net/api/cafeteria");
                //http://cafeteriaapptest.azurewebsites.net/api/cafeteria
                adapter = new CafeteriaAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
                //adapter.arrayList.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
        //"http://cafeteriaapptest.azurewebsites.net/api/category"
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MenuItemsActivity.this,MenuItemDetailsActivity.class);
                //  intent.putExtra()
                //  startActivity(intent);

            }
        });
    }


    public class JsonTask extends AsyncTask<String, Void, List<CafeteriaModel>> {

        @Override
        protected List<CafeteriaModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String finalJson = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                //connection.setRequestMethod("GET");
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


                // return buffer.toString();
                //  return categoryName + "-" + categoryId;
                // tvJsonItem.setText(buffer.toString());
                //  return finalBufferedData.toString();

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

        private List<CafeteriaModel> getData(String jsontoString) throws JSONException {

            JSONObject parentObject = new JSONObject(jsontoString);
            // JSONArray parentArray = parentObject.getJSONArray("menuItems");
            JSONArray parentArray = parentObject.getJSONArray("cafeterias");
            // List<MenuItemModel> menuItemModelList = new ArrayList<>();
            String name = "Name";
            // final int id = Integer.parseInt("Id");
            // StringBuffer finalBufferedData = new StringBuffer();
            for (int i = 0; i < parentArray.length(); i++) {
                CafeteriaModel menuItemModel = new CafeteriaModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                menuItemModel.setName(finalObject.getString(name));


                //adding the final object in the list
                arrayList.add(menuItemModel);
            }

            Log.d("name data",name);
            return arrayList;
        }


    }
}
