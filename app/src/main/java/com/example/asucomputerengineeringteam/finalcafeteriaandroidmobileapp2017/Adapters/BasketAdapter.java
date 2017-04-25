/*
package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoriesActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

*/
/**
 * Created by Esraa Hosny on 3/11/2017.
 *//*


public class BasketAdapter extends BaseAdapter {

    //initialize context , arraylist
    public Context context;

    public List<MenuItemModel> menuItemModelList ;
    public  List<AdditonModel> additonModelList;


    private static final String TAG = "ok";

    //costructor takes context ,arraylist
    public BasketAdapter(Context context, List<MenuItemModel> arrayList_menu, List<AdditonModel> arrayList_addition) {
        super();
        this.context = context;
        this.menuItemModelList = arrayList_menu;
        this.additonModelList = arrayList_addition;

    }
    @Override
    public int getCount() {
        return menuItemModelList.size() + additonModelList.size();
    }

    @Override
    // public Object getItem(int position1 , int position2)
    public Object getItem(int position1) {
       // return menuItemModelList.get(position1) + additonModelList.get(position2);
        return menuItemModelList.get(position1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.basket_row_item, parent, false);
        ImageView image = (ImageView) view.findViewById(R.id.imagecaf);
        image.setImageResource(R.drawable.chef);
        TextView cafname = (TextView) view.findViewById(R.id.cafname);
        cafname.setText(menuItemModelList.get(position).getName());
        final TextView cafid = (TextView) view.findViewById(R.id.cafid);
        cafid.setText((menuItemModelList.get(position).getId())+ "");
        return view;
    }
}
*/
