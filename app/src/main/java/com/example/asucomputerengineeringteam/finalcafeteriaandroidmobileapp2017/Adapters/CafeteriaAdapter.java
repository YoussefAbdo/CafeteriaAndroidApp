package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CafeteriaActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemDetailsActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 * Created by Esraa Hosny on 3/4/2017.

*/

public class CafeteriaAdapter extends BaseAdapter {
    //initialize context , arraylist
    private Context context;
    public List<CafeteriaModel> cafeteriaModelArrayList;


    //costructor takes context ,arraylist
    public CafeteriaAdapter(Context context, List<CafeteriaModel> arrayList) {
        super();
        this.context = context;
        this.cafeteriaModelArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return cafeteriaModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cafeteriaModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.cafeteria_row_item, parent, false);
        //  ImageView image = (ImageView) view.findViewById(R.id.imagecaf);
        // image.setImageResource(Integer.parseInt(cafeteriaModelArrayList.get(position).getImage()));
        TextView cafname = (TextView) view.findViewById(R.id.cafname);
        cafname.setText(cafeteriaModelArrayList.get(position).getName());
      return view;
    }

}
