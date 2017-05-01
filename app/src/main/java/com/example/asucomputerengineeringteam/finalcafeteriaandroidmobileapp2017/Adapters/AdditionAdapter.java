/*package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.AdditonModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;
*/

/*
 * Created by Esraa Hosny on 3/11/2017.

/

public class AdditionAdapter extends BaseAdapter {
    //initialize context , arraylist
    public Context context;
    public List<AdditonModel> additionAdapterList;
    private static final String TAG = "ok";

    //costructor takes context ,arraylist
    public AdditionAdapter(Context context, List<AdditonModel> arrayList) {
        super();
        this.context = context;
        this.additionAdapterList = arrayList;
    }

    @Override
    public int getCount() {
        return additionAdapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return additionAdapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.addition_row_item, parent, false);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.add_checkbox);
        checkBox.setText(additionAdapterList.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoriesActivity.class);
                String addition_name= checkBox.getText().toString();
                intent.putExtra("addition_name", addition_name);
                context.startActivity(intent);

            }
        });

        //If bitmapdata is the byte array then getting Bitmap is done like this:
 Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0

 Ignored for PNGs /*
, blob);
        byte[] bitmapdata = blob.toByteArray();


        return view;
    }
}
*/