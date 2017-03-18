package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CafeteriaActivity extends AppCompatActivity {

    ListView list;
    public CafeteriaAdapter cafeteriaAdapter;
    public List<CafeteriaModel> cafeteriaModelList = new ArrayList<>();
    private ProgressDialog progressDialog ;
    Button load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria);
        list = (ListView) findViewById(R.id.list);
        load = (Button)findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JsonTask1().execute("http://cafeteriaapptest.azurewebsites.net/api/cafeteria");
                cafeteriaAdapter = new CafeteriaAdapter(getApplicationContext(), cafeteriaModelList);
                list.setAdapter(cafeteriaAdapter);
                cafeteriaAdapter.notifyDataSetChanged();
            }
        });
    }


      public  class JsonTask1 extends AsyncTask<String, Void, List<CafeteriaModel>> {

            @Override
            protected List<CafeteriaModel> doInBackground(String... params) {
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
          private List<CafeteriaModel> getData(String jsontoString) throws JSONException {
                JSONObject parentObject = new JSONObject(jsontoString);
                JSONArray parentArray = parentObject.getJSONArray("cafeterias");
                String name = "Name";
                String id = "Id";
                String image = "ImageData";
                for (int i = 0; i < parentArray.length(); i++) {
                    CafeteriaModel menuItemModel = new CafeteriaModel();
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    menuItemModel.setName(finalObject.getString(name));
                    menuItemModel.setId(finalObject.getInt(String.valueOf(id)));
                   // menuItemModel.setId(finalObject.getInt(String.valueOf(image)));

                    //encode image to base64 string
                   /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Integer.parseInt(image));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);  //base64 image string
*/
                    // convert base64 image string to an image again
                  /*  byte [] imageBytes = Base64.decode(image, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    Intent intent = new Intent(CafeteriaActivity.this , CafeteriaAdapter.class);
                    intent.putExtra("image" , decodedImage);
                    startActivity(intent);*/
                   // image.setImageBitmap(decodedImage);

                    //adding the final object in the list
                    cafeteriaModelList.add(menuItemModel);
                }
                Log.d("id", id);
                Log.d("name data", name);
                return cafeteriaModelList;
            }
        }

    }
