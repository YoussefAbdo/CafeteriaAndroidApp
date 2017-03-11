package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.BasketAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.CafeteriaAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;
import java.util.List;

public class Basket extends AppCompatActivity {

    ListView basket_list ;
    public BasketAdapter basketAdapter;
    public List<String> arrayList = new ArrayList<>();
    Button add_more , edit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        //instantiation of ui
        basket_list = (ListView)findViewById(R.id.basket_list);
        add_more = (Button)findViewById(R.id.add_more);
        edit = (Button)findViewById(R.id.edit);


        final String menuitem_name = getIntent().getExtras().getString("menuitem_name");
        final String menuitem_price = getIntent().getExtras().getString("menuitem_price");
        final String addition_name = getIntent().getExtras().getString("addition_name");

       /* List<String> menuitems = new List<String> ;
        menuitems.add(menuitem_name);
        List<String> additions = new List<String> ;
        menuitems.add(menuitem_price);
        basketAdapter = new BasketAdapter(Basket.this,menuitems , additions);
        basket_list.setAdapter(basketAdapter);
        basketAdapter.notifyDataSetChanged();*/

        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Basket.this , MenuItemsActivity.class);
                startActivity(intent1);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Basket.this ,EditActivity.class);
                startActivity(intent2);
            }
        });


    }
}
