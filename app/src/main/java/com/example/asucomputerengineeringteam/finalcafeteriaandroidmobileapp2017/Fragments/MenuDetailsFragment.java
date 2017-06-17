package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.BasketActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.AdditionAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esraa Hosny on 6/17/2017.
 */

public class MenuDetailsFragment extends Fragment {

    public AdditionAdapter additionAdapter;
    public List<AdditonModel> additonModels = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;
    ImageView image, favorite, notfavorite;
    TextView item_name , item_description , item_type , item_price , quantity_text , addition_text ,quantity_title_text_view;
    Button add , remove , basket;
    String[] additionItems = {"Cheese" , "Mayonaise" ,"Katchup" , "salt" , "pepper", "sous"};

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


        return view;
    }


}
