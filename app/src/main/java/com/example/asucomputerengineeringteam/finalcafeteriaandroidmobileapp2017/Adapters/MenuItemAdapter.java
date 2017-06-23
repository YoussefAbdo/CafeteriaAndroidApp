package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Esraa Hosny on 4/25/2017.
 */

public class MenuItemAdapter extends BaseAdapter {

    List<MenuItemDataModel> menuItemDataModelList;
    Context context;
    String review;

    public MenuItemAdapter(Context context, List<MenuItemDataModel> menuItemDataModelList) {
        super();
        this.menuItemDataModelList = menuItemDataModelList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return menuItemDataModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItemDataModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menuitem_list_item, parent, false);
        TextView men_name_text = (TextView) view.findViewById(R.id.men_name_text);
        final ImageView men_image_view = (ImageView) view.findViewById(R.id.men_image_view);
        TextView men_price_text = (TextView) view.findViewById(R.id.men_price_text);
        TextView men_more_text = (TextView)view.findViewById(R.id.men_more_text);
        String name = menuItemDataModelList.get(position).getName();
        men_name_text.setText(name);
        String price = String.valueOf(menuItemDataModelList.get(position).getPrice());
        men_price_text.setText(price);
        men_image_view.setImageResource(R.drawable.burger);
        men_more_text.setText("more...");

        review = menuItemDataModelList.get(position).getAlternatetext();
        //  http://cafeteriaappdemo.azurewebsites.net/Scripts/CustomerTheme/images/page3_img1.jpg
//        String path = "http://menuitemappdemo.azurewebsites.net";
//        Picasso.with(context).load(path + menuItemDataModelList.get(position).getImageData()).into(men_image_view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuDetailInterface.class);
                intent.putExtra("name", menuItemDataModelList.get(position).getName());
                intent.putExtra("price", menuItemDataModelList.get(position).getPrice());
                intent.putExtra("description", menuItemDataModelList.get(position).getDescription());
                intent.putExtra("type", menuItemDataModelList.get(position).getType());
                intent.putExtra("image_url", menuItemDataModelList.get(position).getImageData());
                intent.putExtra("alternate_text", menuItemDataModelList.get(position).getAlternatetext());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
               // Toast.makeText(context, "ok item", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}

