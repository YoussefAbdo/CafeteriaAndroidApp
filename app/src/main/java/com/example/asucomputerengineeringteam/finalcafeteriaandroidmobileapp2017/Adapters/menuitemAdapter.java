
package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemDetailsActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;
/**
 * Created by Esraa Hosny on 2/28/2017.
 *//*
*/
public class menuitemAdapter extends BaseAdapter {

    //initialize context , arraylist
    private Context context;
    public List<MenuItemModel> arrayList;

    //costructor takes context ,arraylist
    public menuitemAdapter(Context context, List<MenuItemModel> arrayList) {
        super();
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menuitem_row_item, parent,false);

        final ImageView imagemen = (ImageView) view.findViewById(R.id.imagemen);
        imagemen.setImageResource(R.drawable.burger);
       final TextView menname= (TextView)view.findViewById(R.id.menname);
       final TextView menprice = (TextView)view.findViewById(R.id.menprice);
        menname.setText(arrayList.get(position).getName());
        menprice.setText(arrayList.get(position).getPrice());

        MenuItemModel menuItemModel = (MenuItemModel) getItem(position);

        final String description = menuItemModel.getDescription();
        final String type = menuItemModel.getType();
        final String photo = menuItemModel.getPhoto();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuItemDetailsActivity.class);
                intent.putExtra("menuitem_name", menname.getText().toString());
                intent.putExtra("desc", description);
                intent.putExtra("type", type);
                intent.putExtra("menuitem_price", menprice.getText().toString());
                intent.putExtra("photo",photo);
                context.startActivity(intent);
            }
        });
        ImageView favorite = (ImageView)view.findViewById(R.id.favorite);
        favorite.setImageResource(R.drawable.favorite);
        ImageView nofavorite = (ImageView)view.findViewById(R.id.nofavorite);
        nofavorite.setImageResource(R.drawable.nofavorite);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context , " Item " + menname.getText().toString()+" is added correctly" , Toast.LENGTH_LONG).show();
            }
        });
        nofavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context , " Item " + menname.getText().toString()+" is deleted correctly" , Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }
}


