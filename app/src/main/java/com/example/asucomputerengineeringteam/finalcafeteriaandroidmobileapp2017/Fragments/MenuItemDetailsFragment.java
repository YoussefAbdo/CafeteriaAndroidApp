/*
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

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.MenuItemAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
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

*/
/**
 * Created by Esraa Hosny on 5/1/2017.
 *//*


public class MenuItemDetailsFragment extends Fragment {

    public MenuItemAdapter menuItemAdapter;
    public List<MenuItemDataModel> menuItemDataModelList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView men_upper_image ;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
   */
/* int menu_images[] = {
            R.drawable.pasta ,
            R.drawable.pizza,
            R.drawable.salad ,
    };*//*


    public MenuItemFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_menuitem, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.men_recyclerView);

        men_upper_image = (ImageView)view.findViewById(R.id.men_upper_image);
        men_upper_image.setImageResource(R.drawable.pasta);
        men_upper_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , )
            }
        });
      */
/*   MenuItemInterface activity = (MenuItemInterface) getActivity();
        String cat_image = activity.getIntent().getExtras().getString("cat_image_intent");
       cat_upper_image.setImageResource(Integer.parseInt(cat_image));
        Log.v("image = " , cat_image);*//*


        MenuItemInterface activity = (MenuItemInterface) getActivity();
        String cat_id = activity.getIntent().getExtras().getString("cat_id");
        Log.v("id", cat_id);
        new MenuItemFragment.JsonTaskMenuItem().execute("http://cafeteriaapptest.azurewebsites.net/api/menuitem/GetByCategory/" + cat_id);

        return view;
    }


    public class JsonTaskMenuItem extends AsyncTask<String, String, List<MenuItemDataModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<MenuItemDataModel> doInBackground(String... params) {
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

        private List<MenuItemDataModel> getData(String jsontoString) throws JSONException {
            JSONObject parentObject = new JSONObject(jsontoString);
            JSONArray parentArray = parentObject.getJSONArray("menuItems");

            String name = "Name";
            String image = "ImageData";
            String id = "Id";
            String price = "Price";

            for (int i = 0; i < parentArray.length(); i++) {
                MenuItemDataModel menuItemDataModel = new MenuItemDataModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                menuItemDataModel.setName(finalObject.getString(name));
                menuItemDataModel.setId(finalObject.getString(id));
                menuItemDataModel.setImageData(finalObject.getString(image));
                menuItemDataModel.setPrice(finalObject.getString(price));

                //adding the final object in the list
                menuItemDataModelList.add(menuItemDataModel);
            }
            Log.v("size", menuItemDataModelList.size() + "");
            // return cafeteriaModelList;
            return menuItemDataModelList;
        }

        @Override
        protected void onPostExecute(List<MenuItemDataModel> menuitems) {
            super.onPostExecute(menuitems);
            dialog.dismiss();
            if (menuitems != null) {
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                menuItemAdapter = new MenuItemAdapter(getContext(), menuitems);
                recyclerView.setAdapter(menuItemAdapter);
            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
*/
