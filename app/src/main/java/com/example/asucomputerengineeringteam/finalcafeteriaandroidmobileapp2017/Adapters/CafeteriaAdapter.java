package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.CategoryInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CafeteriaDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Fragments.CategoryFragment;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import java.util.List;

/**
 * Created by Esraa Hosny on 4/13/2017.
 */

public class CafeteriaAdapter extends RecyclerView.Adapter<CafeteriaAdapter.MyViewHolder>  {

    List<CafeteriaDataModel> cafeteriaArrayList ;
    Context context ;
    String caf_id;

    public CafeteriaAdapter(Context context, List<CafeteriaDataModel> cafeteriaArrayList){
        this.cafeteriaArrayList = cafeteriaArrayList;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cafeteria_list_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.caf_text_view.setText(cafeteriaArrayList.get(position).getName());
        byte[] imageBytes = Base64.decode(cafeteriaArrayList.get(position).getImage().toString(),Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        holder.caf_image.setImageBitmap(decodedImage);
        Log.v("sizeImage" ,imageBytes.toString());

        caf_id  = String.valueOf(cafeteriaArrayList.get(position).getId());

    }

    @Override
    public int getItemCount()
    {
         return cafeteriaArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView caf_text_view;
        public ImageView caf_image ;
        public MyViewHolder(View itemView) {
            super(itemView);
            caf_text_view=(TextView)itemView.findViewById(R.id.caf_text_view);
            caf_image = (ImageView)itemView.findViewById(R.id.caf_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CategoryInterface.class);
                    intent.putExtra("caf_id", caf_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                   // Toast.makeText(context , "ok" , Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
