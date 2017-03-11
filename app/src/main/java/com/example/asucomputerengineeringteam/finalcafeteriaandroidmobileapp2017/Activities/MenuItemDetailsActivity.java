package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import org.w3c.dom.Text;

import java.util.List;


public class MenuItemDetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView item_name , item_description , item_type , item_price , quantity_text;
    ListView addition_list;
    Button add , remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_details);

        //instantiation of ui
        image = (ImageView)findViewById(R.id.image);
        item_name = (TextView)findViewById(R.id.item_name);
        item_description = (TextView)findViewById(R.id.item_description);
        item_price = (TextView)findViewById(R.id.item_price);
        item_type = (TextView)findViewById(R.id.item_type);
        addition_list = (ListView)findViewById(R.id.addition_list);
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        quantity_text = (TextView)findViewById(R.id.quantity_text);


        //getting data by intent
        final String photo = getIntent().getExtras().getString("photo");
        final String name = getIntent().getExtras().getString("name");
        final String description = getIntent().getExtras().getString("description");
        final String type = getIntent().getExtras().getString("type");
        final String price = getIntent().getExtras().getString("price");


        //filling ui with data
        item_name.setText("Item name is : ");
        item_description.setText("Item description is : ");
        item_type.setText("Item type is : ");
        item_price.setText("Item price is : ");


       /* final int[] counter = {0};
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity_text.setText(String.valueOf(counter[0]++));
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity_text.setText(String.valueOf(counter[0]));
            }
        });*/




    }
}
