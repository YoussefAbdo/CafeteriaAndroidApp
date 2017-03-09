package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CategoriesAdapter;
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

public class CategoriesActivity extends AppCompatActivity {

    ListView mainListView;
    Button load;
    public CategoriesAdapter adapter;
    public List<CategoryModel> arrayList = new ArrayList<>();
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id  = Integer.parseInt(extras.getString("idCaf"));

        mainListView = (ListView) findViewById(R.id.mainListView);
        load = (Button)findViewById(R.id.load);
        //  tvJsonItem = (TextView) findViewById(R.id.tvJsonIitem);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new JsonTask().execute("http://cafeteriaapptest.azurewebsites.net/api/menuitem");
                new JsonTask2().execute("http://cafeteriaapptest.azurewebsites.net/api/category/GetByCafetria/" + id);
                // http://cafeteriaapptest.azurewebsites.net/api/category/GetByCafeteria/2131558558
                //http://cafeteriaapptest.azurewebsites.net/api/cafeteria
                adapter = new CategoriesAdapter(getApplicationContext(), arrayList);
                mainListView.setAdapter(adapter);
                //adapter.arrayList.addAll(items);
                adapter.notifyDataSetChanged();
                //"http://cafeteriaapptest.azurewebsites.net/api/category"
            }
        });

    }
    public class JsonTask2 extends AsyncTask<String, Void, List<CategoryModel>> {

        @Override
        protected List<CategoryModel> doInBackground(String... params) {
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

        private List<CategoryModel> getData(String jsontoString) throws JSONException {

            JSONObject parentObject = new JSONObject(jsontoString);
            // JSONArray parentArray = parentObject.getJSONArray("menuItems");
            JSONArray parentArray = parentObject.getJSONArray("categories");
            // List<MenuItemModel> menuItemModelList = new ArrayList<>();
            String name = "Name";
            String id = "Id";
            // final int id = Integer.parseInt("Id");
            // StringBuffer finalBufferedData = new StringBuffer();
            for (int i = 0; i < parentArray.length(); i++) {
                CategoryModel categoryModel = new CategoryModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                categoryModel.setName(finalObject.getString(name));
                categoryModel.setId(finalObject.getInt(String.valueOf(id)));

                  /* List<CategoryModel.Cafeteria> cafeteriaList = new ArrayList<>();
                    for(int j=0 ;j<finalObject.getJSONArray("cafeterias").length();j++)
                    {
                        JSONObject categoryObject = finalObject.getJSONArray("category").getJSONObject(j);
                        CategoryModel.Cafeteria cafeteria = new CategoryModel.Cafeteria();
                        cafeteria.setCafId(categoryObject.getInt("Id"));
                        cafeteria.setCafName(categoryObject.getString("name"));
                        cafeteria.setImageData(categoryObject.getString("ImageData"));

                        cafeteriaList.add(cafeteria);

                    }
                    CategoryModel.setCafeteriaList(cafeteriaList);*/


                //adding the final object in the list
                arrayList.add(categoryModel);
            }


            Log.d("name data", name);
            return arrayList;
        }


    }
    }

