package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Esraa Hosny on 6/21/2017.
 */

public class DrawerAdapter  extends ArrayAdapter<DrawerDataModel> {


    Context mContext;

    public DrawerAdapter(Context mContext,int resourceId ,List<DrawerDataModel> drawerList) {
        super(mContext, resourceId, drawerList);
        this.mContext = mContext;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView textView;
        CircleImageView imageView;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerAdapter.ViewHolder holder = null;
        DrawerDataModel drawerDataModel = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_navigation, null);
            holder = new DrawerAdapter.ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.txt_navigation);
            holder.imageView=(CircleImageView)convertView.findViewById(R.id.image_navigation);
            convertView.setTag(holder);
        } else
            holder = (DrawerAdapter.ViewHolder) convertView.getTag();

        holder.textView.setText(drawerDataModel.getLabel());// name of song
        holder.imageView.setImageResource(drawerDataModel.getItem());


        return convertView;
    }
}
