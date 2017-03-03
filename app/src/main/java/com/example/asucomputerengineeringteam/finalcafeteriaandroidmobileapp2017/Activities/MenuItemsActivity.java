package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CustomListAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.menuitems;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;


public class MenuItemsActivity extends AppCompatActivity {



    String[] itemname ={
            "it is a kind of pie full of vegetables,sauces,garlic,meat,...",
            "it is a kind of makaroni with sauce ",
            "all types of fruits",
            "all plants , tomatos,garlic,oil,...",
            "steak with spicy sauce",

    };
    Integer[] imgid={
            R.drawable.pizza,
            R.drawable.pasta,
            R.drawable.juice,
            R.drawable.salad,
            R.drawable.meat,

    };

    public List<menuitems> arrayList ;
    public ListView list;
    //DataBaseFavourites dbf;
   // ArrayList<menuitems> arrayList_Fav_movie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

            CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
    }

