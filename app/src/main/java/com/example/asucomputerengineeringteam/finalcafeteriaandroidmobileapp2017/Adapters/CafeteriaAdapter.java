package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoriesActivity;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;


import java.io.ByteArrayOutputStream;
import java.util.List;

/*
 * Created by Esraa Hosny on 3/4/2017.

*/

public class CafeteriaAdapter extends BaseAdapter {
    //initialize context , arraylist
    private Context context;
    public List<CafeteriaModel> cafeteriaModelArrayList ;


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

         ImageView image = (ImageView) view.findViewById(R.id.imagecaf);
        // image.setImageResource(Integer.parseInt(cafeteriaModelArrayList.get(position).getImage()));
        image.setImageResource(R.drawable.chef);

        TextView cafname = (TextView) view.findViewById(R.id.cafname);
        cafname.setText(cafeteriaModelArrayList.get(position).getName());

        final TextView cafid = (TextView) view.findViewById(R.id.cafid);
        cafname.setText(cafeteriaModelArrayList.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CategoriesActivity.class);
                int id = cafid.getId();
                intent.putExtra("idCaf",id);
                context.startActivity(intent);

               // Toast.makeText(context,"yes",Toast.LENGTH_LONG).show();
            }
        });

        //If bitmapdata is the byte array then getting Bitmap is done like this:
       /* Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 *//* Ignored for PNGs *//*, blob);
        byte[] bitmapdata = blob.toByteArray();*/

      return view;
    }

}