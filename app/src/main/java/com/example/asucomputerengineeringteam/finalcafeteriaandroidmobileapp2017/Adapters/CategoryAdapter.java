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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.CategoryDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Esraa Hosny on 4/25/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>  {

    List<CategoryDataModel> categoryDataModelList ;
    Context context ;
    String cat_id;


    public CategoryAdapter(Context context, List<CategoryDataModel> categoryDataModelList){
        this.categoryDataModelList = categoryDataModelList;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
       MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.cat_text_view.setText(categoryDataModelList.get(position).getName());

       /* byte[] imageBytes = Base64.decode(categoryDataModelList.get(position).getImage().toString(),Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        holder.cat_image.setImageBitmap(decodedImage);*/
        String path = "http://categoryappdemo.azurewebsites.net";
        Picasso.with(context).load(path+categoryDataModelList.get(position).getImage()).into(holder.cat_image);
        cat_id  = String.valueOf(categoryDataModelList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return categoryDataModelList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView cat_text_view;
        public ImageView cat_image ;
        public MyViewHolder(View itemView) {
            super(itemView);
            cat_text_view=(TextView)itemView.findViewById(R.id.cat_text_view);
            cat_image = (ImageView)itemView.findViewById(R.id.cat_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context,MenuItemInterface.class);
                    intent.putExtra("cat_id", cat_id);
                    Log.v("cat_id" , cat_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });
        }
    }
}

