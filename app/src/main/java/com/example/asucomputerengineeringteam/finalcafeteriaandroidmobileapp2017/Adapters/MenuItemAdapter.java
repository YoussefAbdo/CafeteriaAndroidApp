package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Esraa Hosny on 4/25/2017.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MyViewHolder> {

    List<MenuItemDataModel> menuItemDataModelList;
    Context context;
    String cat_id;
    int menu_images[];
    String idItem;

   // public MenuItemAdapter(Context context, List<MenuItemDataModel> menuItemDataModels , int menu_images[]) {
    public MenuItemAdapter(Context context, List<MenuItemDataModel> menuItemDataModels , int menu_images[]) {
        menuItemDataModelList = menuItemDataModels;
        this.context = context;
        this.menu_images = menu_images;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menuitem_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name = menuItemDataModelList.get(position).getName();
        holder.men_name_text.setText("Name is " + name);

        String price = String.valueOf(menuItemDataModelList.get(position).getPrice());
        holder.men_price_text.setText("Price = " +price + "");

        cat_id = String.valueOf(menuItemDataModelList.get(position).getId());

       /* holder.favorite.setImageResource(R.drawable.favorite);
        holder.no_favorite.setImageResource(R.drawable.nofavorite);

*/
       /* byte[] imageBytes = Base64.decode(menuItemDataModelList.get(position).getImageData().toString(), Base64.DEFAULT);
        Log.v("sizeImage", imageBytes.toString());
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.men_image_view.setImageBitmap(decodedImage);*/
        holder.men_image_view.setImageResource(menu_images[position]);
    }


    @Override
    public int getItemCount() {
        return menuItemDataModelList.size();
    }

    public void setFilter(List<MenuItemDataModel> menuItemDataModelList1) {
        menuItemDataModelList = new ArrayList<>();
        menuItemDataModelList.addAll(menuItemDataModelList1);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView men_name_text;
        public ImageView men_image_view;
        public TextView men_price_text;
       /* public ImageView favorite;
        public ImageView no_favorite;*/

        public MyViewHolder(View itemView) {
            super(itemView);
            men_name_text = (TextView)itemView.findViewById(R.id.men_name_text);
            men_image_view = (ImageView)itemView.findViewById(R.id.men_image_view);
            men_price_text = (TextView)itemView.findViewById(R.id.men_price_text);
          /*  favorite = (ImageView)itemView.findViewById(R.id.favorite);
            no_favorite = (ImageView)itemView.findViewById(R.id.no_favorite);*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MenuDetailInterface.class);
                    intent.putExtra("cat_id", cat_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    // Toast.makeText(context, "ok item clicked", Toast.LENGTH_SHORT).show();
                }
            });
         /*   favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(context, "ok item clicked", Toast.LENGTH_SHORT).show();
                    //save menu item in favourite list
                    DataBaseFavourites db = new DataBaseFavourites(context);
                    if (!db.Search_item(idItem)) {
                        boolean isInserted = dbf.insertData(idItem , men_name_text.toString());
                        Log.v("ifins", String.valueOf(isInserted));
                    }
                }
            });
            no_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "ok item clicked", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Do you want to delete this movie");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            dbf.Delete_item(idItem);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            Toast.makeText(context, "movie still exists", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });*/
        }
    }
}


