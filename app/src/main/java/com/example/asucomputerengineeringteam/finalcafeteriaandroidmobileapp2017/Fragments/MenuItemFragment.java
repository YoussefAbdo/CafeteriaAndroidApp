package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.MenuItemAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataBase.Favorites;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Interfaces.MenuItemListener;
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

public class MenuItemFragment extends Fragment {

    public MenuItemAdapter menuItemAdapter;
    public List<MenuItemDataModel> menuItemDataModelList = new ArrayList<>();
    public GridView gridView;
    Favorites dbf;
    String cat_id;
    // Search EditText
    EditText inputSearch;
    ImageView favorite_menu ,items_menu;
    Toolbar myToolBar ;

    ArrayList<MenuItemDataModel> arrayList_Fav_menuitems = new ArrayList<>();
    private MenuItemListener menuItemListener;


    public void setItems(MenuItemListener menuItemListener)
    {
        this.menuItemListener = menuItemListener;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dbf = new Favorites(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        menuItemDataModelList.clear();
        arrayList_Fav_menuitems.clear();
        menuItemAdapter.notifyDataSetChanged();
        gridView.setAdapter(menuItemAdapter);
        JsonTaskMenuItem movieTask = new JsonTaskMenuItem();
        movieTask.execute("http://cafeteriaappdemo.azurewebsites.net/api/menuitem");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menuitem, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        inputSearch = (EditText)view.findViewById(R.id.inputSearch);
        myToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        myToolBar.setTitle(getResources().getString(R.string.sentence));
        menuItemAdapter = new MenuItemAdapter(getActivity(), menuItemDataModelList);
        gridView.setAdapter(menuItemAdapter);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                menuItemAdapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

       // MenuItemInterface activity = (MenuItemInterface) getActivity();
       // cat_id = activity.getIntent().getExtras().getString("cat_id");
       // Log.v("idtomenuitem", cat_id);
      //  JsonTaskMenuItem jsonmenu = new JsonTaskMenuItem();
        //http://cafeteriaappdemo.azurewebsites.net/api/menuitem/GetByCategory/"+Integer.valueOf(cat_id)
        //http://cafeteriaappdemo.azurewebsites.net/api/menuitem
        //jsonmenu.execute("http://cafeteriaappdemo.azurewebsites.net/api/menuitem");
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "ok item", Toast.LENGTH_SHORT).show();
            }
        });*/

        items_menu = (ImageView)view.findViewById(R.id.items_menu);
        items_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList_Fav_menuitems.clear();
                menuItemAdapter.notifyDataSetChanged();
                gridView.setAdapter(menuItemAdapter);
                JsonTaskMenuItem jsonmenu = new JsonTaskMenuItem();
                //http://cafeteriaappdemo.azurewebsites.net/api/menuitem/GetByCategory/"+Integer.valueOf(cat_id)
                jsonmenu.execute("http://cafeteriaappdemo.azurewebsites.net/api/menuitem");
            }
        });
        favorite_menu = (ImageView)view.findViewById(R.id.favorite_menu);
        favorite_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "ok item", Toast.LENGTH_SHORT).show();
                menuItemDataModelList.clear();
                menuItemDataModelList = dbf.GetAllData();
                Log.v("favcount", menuItemDataModelList.size() + "");
                menuItemAdapter = new MenuItemAdapter(getContext(), menuItemDataModelList);
                menuItemAdapter.notifyDataSetChanged();
                gridView.setAdapter(menuItemAdapter);

                for (int i = 0; i < menuItemDataModelList.size(); i++) {
                    Log.v("FID", String.valueOf(menuItemDataModelList.get(i).getId()));
                }

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 MenuItemDataModel menuItemDataModel;
                if(menuItemDataModelList.size()==0)
                    menuItemDataModel = arrayList_Fav_menuitems.get(position);
                else
                    menuItemDataModel = menuItemDataModelList.get(position);
                    String name = menuItemDataModel.getName();
                    String price = menuItemDataModel.getPrice();
                    String des = menuItemDataModel.getDescription();
                    String type = menuItemDataModel.getType();
                    String image = menuItemDataModel.getImageData();
                    String review = menuItemDataModel.getAlternatetext();
                    String ids = menuItemDataModel.getId();

                menuItemListener.setSelectedMenuItem(image,name,price,review,des, type,ids);


            }
        });
        return view;
    }


    public class JsonTaskMenuItem extends AsyncTask<String, Void, List<MenuItemDataModel>> {

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
            String image = "ImageUrl";
            String id = "Id";
            String review = "alternatetext";
            String price = "Price";


            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                MenuItemDataModel menuItemDataModel = new MenuItemDataModel();
                menuItemDataModel.setName(finalObject.getString(name));
                menuItemDataModel.setId(finalObject.getString(id));
                menuItemDataModel.setPrice(finalObject.getString(price));
                menuItemDataModel.setAlternatetext(finalObject.getString(review));
                menuItemDataModel.setImageData(finalObject.getString(image));
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
               // dialog.dismiss();
              //  menuItemAdapter.menuItemDataModelList.addAll(menuitems);
               /* menuItemAdapter = new MenuItemAdapter(getActivity(),menuItemDataModelList);
                gridView.setAdapter(menuItemAdapter);
                menuItemAdapter.notifyDataSetChanged();*/
                menuItemAdapter.menuItemDataModelList.addAll(menuitems);
                menuItemAdapter.notifyDataSetChanged();
            }
        }
}

