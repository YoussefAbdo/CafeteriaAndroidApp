package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.MenuItemAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaDataModel;
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
import java.util.Locale;

/**
 * Created by Esraa Hosny on 4/29/2017.
 */

public class MenuItemFragment extends Fragment implements SearchView.OnQueryTextListener  {

    public MenuItemAdapter menuItemAdapter;
    public List<MenuItemDataModel> menuItemDataModelList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView men_upper_image;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
    public EditText search;

    ArrayList<CafeteriaDataModel> arrayList_Fav_menuitems = new ArrayList<>();

    int menu_images[] = {
            R.drawable.pasta,
            R.drawable.pizza,
            R.drawable.salad,
    };

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        String[] locales = Locale.getISOCountries();
        menuItemDataModelList = new ArrayList<>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            menuItemDataModelList.add(new MenuItemDataModel());
            //obj.getDisplayCountry(), obj.getISO3Country() were parameters of data model
        }
        menuItemAdapter = new MenuItemAdapter(getContext(),menuItemDataModelList,menu_images);
        recyclerView.setAdapter(menuItemAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menuitem, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.men_recyclerView);
        men_upper_image = (ImageView) view.findViewById(R.id.men_upper_image);
        men_upper_image.setImageResource(R.drawable.pasta);
        men_upper_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
            }
        });

      /*   MenuItemInterface activity = (MenuItemInterface) getActivity();
        String cat_image = activity.getIntent().getExtras().getString("cat_image_intent");
       cat_upper_image.setImageResource(Integer.parseInt(cat_image));
        Log.v("image = " , cat_image);*/

        MenuItemInterface activity = (MenuItemInterface) getActivity();
        String cat_id = activity.getIntent().getExtras().getString("cat_id");
        Log.v("idtomenuitem", cat_id);
       // addTextListener();
        new JsonTaskMenuItem().execute("http://cafeteriaapptest.azurewebsites.net/api/menuitem/GetByCategory/" + Integer.valueOf(cat_id));

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<MenuItemDataModel> filteredModelList = filter(menuItemDataModelList, newText);
        menuItemAdapter.setFilter(filteredModelList);
        return true;
    }

    private List<MenuItemDataModel> filter(List<MenuItemDataModel> models, String query) {
        query = query.toLowerCase();final List<MenuItemDataModel> filteredModelList = new ArrayList<>();
        for (MenuItemDataModel model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
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
                menuItemDataModel.setId(Integer.parseInt(finalObject.getString(id)));
                menuItemDataModel.setPrice(Double.parseDouble(finalObject.getString(price)));
                //  menuItemDataModel.setImageData(finalObject.getString(image));
                //adding the final object in the list
                menuItemDataModelList.add(menuItemDataModel);
                Log.v("size", menuItemDataModelList.size() + "");
                Log.v("size", parentArray.length() + "");
            }
                // return cafeteriaModelList;
                return menuItemDataModelList;
            }

            @Override
            protected void onPostExecute (List < MenuItemDataModel > menuitems) {
                super.onPostExecute(menuitems);
                dialog.dismiss();
                Log.v("list", String.valueOf(menuitems)); // 0
               if (menuitems != null && menuitems.size()>0) { // && menuitems

                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);//to manage the position of its items in the RecyclerView.
                    recyclerView.setHasFixedSize(true);  //To improve the performance of RecyclerView
                    menuItemAdapter = new MenuItemAdapter(getContext(), menuitems, menu_images);
                    recyclerView.setAdapter(menuItemAdapter);
                    menuItemAdapter.notifyDataSetChanged();

                    //  Toast.makeText(getContext(), "yes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                menuItemAdapter.setFilter(menuItemDataModelList);
                return true; // Return true to collapse action view
            }
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true; // Return true to expand action view
            }
        });
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {

            case R.id.action_favorites:
                menuItemDataModelList.clear();
                menuItemDataModelList = dbf.GetAllData();
                Log.v("favcount", menuItemDataModelList.size() + "");
                menuItemAdapter = new MenuItemAdapter(getContext(), menuItemDataModelList,menu_images);
                menuItemAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(menuItemAdapter);

                for (int i = 0; i < menuItemDataModelList.size(); i++) {
                    Log.v("FID", String.valueOf(menuItemDataModelList.get(i).getId()));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

