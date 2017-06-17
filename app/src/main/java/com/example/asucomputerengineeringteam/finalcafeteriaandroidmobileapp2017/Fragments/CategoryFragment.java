package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoryInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CategoryAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CategoryDataModel;
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

/**
 * Created by Esraa Hosny on 4/25/2017.
 */

public class CategoryFragment  extends Fragment {

    public CategoryAdapter categoryAdapter;
    public List<CategoryDataModel> categoryDataModelList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView caf_upper_image ;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
    int category_images[] = {
            R.drawable.pasta ,
            R.drawable.pizza,
            R.drawable.salad ,
            };

    public CategoryFragment() {
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dialog = new ProgressDialog(getContext());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.category_recyclerView);

        caf_upper_image = (ImageView)view.findViewById(R.id.caf_upper_image);
        caf_upper_image.setImageResource(R.drawable.burger);

        /*CategoryInterface activity = (CategoryInterface) getActivity();
        // Intent intent = getActivity().getIntent();
        // int
        String caf_image = activity.getIntent().getExtras().getString("caf_image_intent");
       caf_upper_image.setImageResource(Integer.parseInt(caf_image));
        Log.v("image = " , caf_image);*/

        CategoryInterface activity = (CategoryInterface) getActivity();
        String caf_id = activity.getIntent().getExtras().getString("caf_id");
        Log.v("id" , caf_id);
        new JsonTaskCategory().execute("http://cafeteriaapptest.azurewebsites.net/api/category/GetByCafetria/"+Integer.valueOf(caf_id));
        return view;
    }

    public  class JsonTaskCategory extends AsyncTask<String, String, List<CategoryDataModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<CategoryDataModel> doInBackground(String... params) {
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
        private List<CategoryDataModel> getData(String jsontoString) throws JSONException {
            JSONObject parentObject = new JSONObject(jsontoString);
            JSONArray parentArray = parentObject.getJSONArray("categories");

            String name = "Name";
            String image = "ImageData";
            String id = "Id";

            for (int i = 0; i < parentArray.length(); i++) {
                CategoryDataModel categoryDataModel = new CategoryDataModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                categoryDataModel.setName(finalObject.getString(name));
                categoryDataModel.setImage(finalObject.getString(image));
                categoryDataModel.setId(Integer.parseInt(finalObject.getString(id)));

                //adding the final object in the list
                categoryDataModelList.add(categoryDataModel);
            }
            Log.v("size",categoryDataModelList.size()+"");
            return categoryDataModelList;
        }

        @Override
        protected void onPostExecute(List<CategoryDataModel> categories) {
            super.onPostExecute(categories);
            dialog.dismiss();
            if(categories != null) {
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                categoryAdapter = new CategoryAdapter(getContext(), categories , category_images);
                recyclerView.setAdapter(categoryAdapter);
            }
            else
            {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
