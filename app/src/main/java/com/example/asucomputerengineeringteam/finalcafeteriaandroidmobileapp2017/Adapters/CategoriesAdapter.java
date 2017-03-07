package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoriesActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemsActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CategoryModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

/**
 * Created by Esraa Hosny on 3/5/2017.
 */

public class CategoriesAdapter extends BaseAdapter {
    //initialize context , arraylist
    private Context context;
    public List<CategoryModel> categoryModelList;


    //costructor takes context ,arraylist
    public CategoriesAdapter(Context context, List<CategoryModel> arrayList) {
        super();
        this.context = context;
        this.categoryModelList = arrayList;
    }

    @Override
    public int getCount() {
        return categoryModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryModelList.get(position);
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
        CafeteriaModel cafeteriaModel = new CafeteriaModel();
        TextView cafname = (TextView) view.findViewById(R.id.cafname);
        cafname.setText(categoryModelList.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MenuItemsActivity.class);
                CategoryModel categoryModel;
                categoryModel= categoryModelList.get(position);
                int id =categoryModel.getId();
                intent.putExtra("idCat",id);
                context.startActivity(intent);

                // Toast.makeText(context,"yes",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}

