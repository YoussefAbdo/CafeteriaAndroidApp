package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataBase.DataBaseFavourites;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaDataModel;
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
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class CafeteriaFragment extends Fragment {

    public CafeteriaAdapter cafeteriaAdapter;
    public List<CafeteriaDataModel> cafeteriaModelList = new ArrayList<>();
    RecyclerView recyclerView;
    TextView text;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;


    public CafeteriaFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_cafeteria, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.cafeteria_recyclerView);
        text = (TextView) view.findViewById(R.id.text1);
        new JsonTaskCafeteria().execute("http://cafeteriaapptest.azurewebsites.net/api/cafeteria");
        return view;
    }

    public class JsonTaskCafeteria extends AsyncTask<String, String, List<CafeteriaDataModel>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
        @Override
        protected List<CafeteriaDataModel> doInBackground(String... params) {
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

        private List<CafeteriaDataModel> getData(String jsontoString) throws JSONException {
            JSONObject parentObject = new JSONObject(jsontoString);
            JSONArray parentArray = parentObject.getJSONArray("cafeterias");

            String name = "Name";
            String image = "ImageData";
            Log.v("image", image);
            String id = "Id";
            for (int i = 0; i < parentArray.length(); i++) {
                CafeteriaDataModel cdm = new CafeteriaDataModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                cdm.setName(finalObject.getString(name));
                cdm.setImage(finalObject.getString(image));
                cdm.setId(Integer.parseInt(finalObject.getString(id)));

                //adding the final object in the list
                cafeteriaModelList.add(cdm);
            }
            Log.v("size", cafeteriaModelList.size() + "");
            // return cafeteriaModelList;
            return cafeteriaModelList;
        }

        @Override
        protected void onPostExecute(List<CafeteriaDataModel> cafeterias) {
            super.onPostExecute(cafeterias);
            dialog.dismiss();
            if (cafeterias != null) {
                // layoutManager = new LinearLayoutManager(getContext());
                layoutManager = new StaggeredGridLayoutManager(calculateNoOfColumns(getContext()), 1);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                //  Toast.makeText(getContext(),cafeteriaModelList.size()+" ",Toast.LENGTH_LONG).show();
                cafeteriaAdapter = new CafeteriaAdapter(getContext(), cafeterias);
                recyclerView.setAdapter(cafeteriaAdapter);
                //cafeteriaAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }

        }
        public int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 180);
            return noOfColumns >= 2 ? noOfColumns : 2;

        }

    }

}
