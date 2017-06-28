package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.BasketActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.AdditionAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataBase.Favorites;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
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
    ListView addition_lv;
    ImageView image, favorite, notfavorite;
    TextView item_name, item_description, item_type, item_price, quantity_text, addition_text, quantity_title_text_view, textViewReviews;
    Button add_btn, remove_btn, basket, back_btn;
    TextView tvReviewData;
    Favorites favorites;
    String ids_menus;
    int increment =0;
    int decrement = 0;

    /*TextView tvIsConnected;
    EditText addition_name;
    Button btnPost;
    AdditonModel additionModel;
*/
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favorites = new Favorites(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_details_menu_item, container, false);


        //instantiation of ui
        image = (ImageView) view.findViewById(R.id.photo_image_view);
        item_description = (TextView) view.findViewById(R.id.item_description);
        item_name = (TextView) view.findViewById(R.id.item_name);
        item_price = (TextView) view.findViewById(R.id.item_price);
        item_type = (TextView) view.findViewById(R.id.item_type);
        addition_text = (TextView) view.findViewById(R.id.additions_text_view);
        quantity_title_text_view = (TextView) view.findViewById(R.id.quantity_title_text_view);
        quantity_text = (TextView) view.findViewById(R.id.quantity_text);
        add_btn = (Button) view.findViewById(R.id.add_btn);
        remove_btn = (Button) view.findViewById(R.id.remove_btn);
        basket = (Button) view.findViewById(R.id.basket);
        favorite = (ImageView) view.findViewById(R.id.favorite);
        notfavorite = (ImageView) view.findViewById(R.id.no_favorite);
        textViewReviews = (TextView) view.findViewById(R.id.textViewReviews);
        addition_lv = (ListView) view.findViewById(R.id.addition_lv);
       // back_btn = (Button)view.findViewById(R.id.back_btn);

        //intent Bundle to get data
        final Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        if(extras == null){
            extras = getArguments();
        }
        ids_menus = extras.getString("id");
        //id
        final String id_menu_item_intent = extras.getString("id");
        //name
        final String item_name_intent = extras.getString("name");
        item_name.setText(item_name_intent);
        //name
        final String item_type_intent = extras.getString("type");
        item_type.setText("type of this item is vegeterian " + item_type_intent);
        //price
        final String item_price_intent = extras.getString("price");
        item_price.setText(item_price_intent);
        //description
        final String item_description_intent = extras.getString("description");
        item_description.setText("Description of this item is ...." + item_description_intent);
        //image
        /*String item_image_url_intent  = menuDetailInterface.getIntent().getExtras().getString("image_url");
        image.setImageResource(Integer.parseInt(item_image_url_intent));*/
        final String item_image_url_intent = extras.getString("image_url");
        Log.v("image" , item_image_url_intent);
        //image.setImageResource(Integer.parseInt(item_image_url_intent));
        image.setImageResource(R.drawable.burger);
        //reviews
        final String reviews_of_menu_item_intent = extras.getString("alternate_text");
        textViewReviews.setText("my reviews about this item is very good . I recommend this to all people " + reviews_of_menu_item_intent);


        // add to favorites
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "ok favorite", Toast.LENGTH_SHORT).show();
                Favorites db = new Favorites(getActivity());
                if(!db.search_menu_item(ids_menus))
                {
                    //boolean isInserted =
                     favorites.insertData(ids_menus ,item_price_intent ,
                            item_name_intent,item_description_intent,item_type_intent,reviews_of_menu_item_intent,item_image_url_intent);

                }

            }
        });
        //remove from favorites
        notfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Do you want to delete this movie");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        favorites.delete_menu_item(ids_menus);
                       // Toast.makeText(getContext(),item_type.getText().toString() + " is removed" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        Toast.makeText(getActivity(), "menuitem still exists", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        additionAdapter = new AdditionAdapter(getContext(), additonModelList);
        addition_lv.setAdapter(additionAdapter);
        addition_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               AdditonModel additonModel = additonModelList.get(position);
                String addition = additonModel.getName();

                if(addition == null)
                {
                    Toast.makeText(getActivity(), "Please check box to choose addition ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), MenuDetailInterface.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("addition_value" , addition);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "Good, addition is selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addition_lv.setOnTouchListener(new View.OnTouchListener() {
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


       /* back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , MenuItemInterface.class);
                getContext().startActivity(intent);
                Toast.makeText(getActivity(), "Back button", Toast.LENGTH_SHORT).show();

            }
        });*/

      /* add_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               increment++;
           }
       });
      remove_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              decrement--;
          }
      });
        quantity_text.getText().toString();

       basket.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent i = new Intent(getActivity(), BasketActivity.class);
                startActivity(i);
               //post code is here
            }
        });*/


        return view;
    }


    public class JsonTaskAddition extends AsyncTask<String, Void, List<AdditonModel>> {

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
            additionAdapter.notifyDataSetChanged();
        }

    }


}
