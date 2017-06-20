package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.BasketActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.AdditionAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CategoryAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
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

/**
 * Created by Esraa Hosny on 6/17/2017.
 */

public class MenuDetailsFragment extends Fragment {

    public AdditionAdapter additionAdapter;
    public List<AdditonModel> additonModelList = new ArrayList<>();
    RecyclerView addition_recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
    ImageView image, favorite, notfavorite;
    TextView item_name , item_description , item_type , item_price , quantity_text , addition_text ,quantity_title_text_view ,textViewReviews;
    Button add , remove , basket;
    /*TextView tvIsConnected;
    EditText addition_name;
    Button btnPost;
    AdditonModel additionModel;
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_details_menu_item, container, false);


        //instantiation of ui
        image = (ImageView)view.findViewById(R.id.photo_image_view);
        item_description = (TextView)view.findViewById(R.id.item_description);
        item_name = (TextView)view.findViewById(R.id.item_name);
        item_price = (TextView)view.findViewById(R.id.item_price);
        item_type = (TextView)view.findViewById(R.id.item_type);
        addition_text = (TextView)view.findViewById(R.id.additions_text_view);
        quantity_title_text_view = (TextView)view.findViewById(R.id.quantity_title_text_view);
        quantity_text = (TextView)view.findViewById(R.id.quantity_text);
        add = (Button)view.findViewById(R.id.add);
        remove = (Button)view.findViewById(R.id.remove);
        basket = (Button)view.findViewById(R.id.basket);
        favorite = (ImageView)view.findViewById(R.id.favorite);
        notfavorite = (ImageView)view.findViewById(R.id.no_favorite);
        textViewReviews = (TextView)view.findViewById(R.id.textViewReviews);
        addition_recyclerView = (RecyclerView)view.findViewById(R.id.addition_recyclerView);


        // setting reviews in text view
        MenuItemInterface activity = (MenuItemInterface) getActivity();
        String reviews_of_menu_item = activity.getIntent().getExtras().getString("reviews");
        textViewReviews.setText(reviews_of_menu_item);
        Log.v("reviews", reviews_of_menu_item);

           favorite.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText(getContext(), "ok favorite", Toast.LENGTH_SHORT).show();
               }
           });

        notfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ok not favorite", Toast.LENGTH_SHORT).show();
            }
        });

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(getActivity() , BasketActivity.class);
                startActivity(i);
            }
        });

        additionAdapter = new AdditionAdapter(getActivity(), additonModelList);
        addition_recyclerView.setAdapter(additionAdapter);

        addition_recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);

                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                }
                v.onTouchEvent(event);
                return true;
            }
        });

        // addition json execution
        JsonTaskAddition jsonTaskAddition = new JsonTaskAddition();
        jsonTaskAddition.execute("http://cafeteriaappdemo.azurewebsites.net/api/addition");

       /* // quantity json execution
        JsonTaskQuantity jsonTaskQuantity = new JsonTaskQuantity();
        jsonTaskQuantity.execute()*/;

        return view;
    }


    public class JsonTaskAddition extends AsyncTask<String, String, List<AdditonModel>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
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
            JSONArray parentArray = parentObject.getJSONArray("additions");

            String name = "Name";
            Log.v("nameofaddition", name);
            String id = "Id";
            for (int i = 0; i < parentArray.length(); i++) {
                AdditonModel additonModel = new AdditonModel();
                JSONObject finalObject = parentArray.getJSONObject(i);
                additonModel.setName(finalObject.getString(name));
                additonModel.setId(Integer.parseInt(finalObject.getString(id)));

                //adding the final object in the list
                additonModelList.add(additonModel);
            }
            Log.v("size", additonModelList.size() + "");
            // return cafeteriaModelList;
            return additonModelList;
        }

        @Override
        protected void onPostExecute(List<AdditonModel> additonModels) {
            super.onPostExecute(additonModels);
            dialog.dismiss();
            if (additonModels != null) {
                layoutManager = new LinearLayoutManager(getContext());
                addition_recyclerView.setLayoutManager(layoutManager);
                addition_recyclerView.setHasFixedSize(true);
                additionAdapter = new AdditionAdapter(getContext(), additonModels);
                addition_recyclerView.setAdapter(additionAdapter);
            } else {
                Toast.makeText(getContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
