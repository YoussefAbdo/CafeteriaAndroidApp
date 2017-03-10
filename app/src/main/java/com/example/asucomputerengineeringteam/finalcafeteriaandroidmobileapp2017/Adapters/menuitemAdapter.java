
package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


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

        //ImageView image = (ImageView) view.findViewById(R.id.imageView);
       TextView menname= (TextView)view.findViewById(R.id.menname);
       TextView menprice = (TextView)view.findViewById(R.id.menprice);


        menname.setText(arrayList.get(position).getName());
        menprice.setText(arrayList.get(position).getPrice());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuItemDetailsActivity.class);
                context.startActivity(intent);
            }
        });

        return view;

    }
}


