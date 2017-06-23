package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoryInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

 // Created by Esraa Hosny on 3/11/2017.
public class AdditionAdapter extends BaseAdapter {

    List<AdditonModel> additionModelList;
    Context context;
     String addition_item_name;

    public AdditionAdapter(Context context, List<AdditonModel> additionModelList) {
        this.additionModelList = additionModelList;
        this.context = context;
    }


     @Override
     public int getCount() {
         return additionModelList.size();
     }

     @Override
     public Object getItem(int position) {
         return additionModelList.get(position);
     }

     @Override
     public long getItemId(int position) {
         return position;
     }

     @Override
     public View getView(int position, View view, ViewGroup parent) {
          view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addition_row_item, parent, false);
         final CheckBox add_checkbox = (CheckBox) view.findViewById(R.id.add_checkbox);
          add_checkbox.setText(additionModelList.get(position).getName());
         String.valueOf(additionModelList.get(position).getId());
         // holder.add_checkbox.setText(additionItems[position].getN);
         addition_item_name = additionModelList.get(position).getName();
         view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!add_checkbox.isChecked())
                    {
                        Toast.makeText(context, "Please check box to choose addition ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(context, MenuDetailInterface.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("addition_item", addition_item_name);
                        context.startActivity(intent);
                        Toast.makeText(context, "Good, addition is selected", Toast.LENGTH_SHORT).show();
                    }


                }
            });
         return view;
     }






}