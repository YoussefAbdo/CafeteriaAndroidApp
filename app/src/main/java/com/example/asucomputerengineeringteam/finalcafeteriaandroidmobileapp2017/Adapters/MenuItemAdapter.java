package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuDetailInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Created by Esraa Hosny on 4/25/2017.
 */

public class MenuItemAdapter  extends BaseAdapter  implements Filterable {

        public List<MenuItemDataModel> menuItemDataModelList;
        private Context context;
        private LayoutInflater inflater;
        private ValueFilter valueFilter;
        private ArrayList<MenuItemDataModel> mStringFilterList;

        public MenuItemAdapter(Context context, List<MenuItemDataModel> menuItemDataModelList) {
        super();
        this.context = context;
        this.menuItemDataModelList = menuItemDataModelList;
        mStringFilterList = (ArrayList<MenuItemDataModel>) menuItemDataModelList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getFilter();
    }

        @Override
        public int getCount() {
        return menuItemDataModelList.size();
    }

        @Override
        public Object getItem(int position) {
        return menuItemDataModelList.get(position).getName();
    }

        @Override
        public long getItemId(int position) {
        return 0;
    }

        public class ViewHolder {
            TextView men_name_text ;
            ImageView men_image_view ;
            TextView men_price_text ;
            TextView men_more_text;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.menuitem_list_item, null);
            holder.men_name_text = (TextView) convertView.findViewById(R.id.men_name_text);
            holder.men_image_view = (ImageView) convertView.findViewById(R.id.men_image_view);
            holder.men_price_text = (TextView) convertView.findViewById(R.id.men_price_text);
            holder.men_more_text = (TextView)convertView.findViewById(R.id.men_more_text);


            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        String name = menuItemDataModelList.get(position).getName();
        holder.men_name_text.setText(name);
        String price = String.valueOf(menuItemDataModelList.get(position).getPrice());
        holder.men_price_text.setText(price);
        holder.men_image_view.setImageResource(R.drawable.burger);
        holder.men_more_text.setText("more...");
            // Listen for ListView Item Click
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, MenuDetailInterface.class);
                    intent.putExtra("id", menuItemDataModelList.get(position).getId());
                    intent.putExtra("name", menuItemDataModelList.get(position).getName());
                    intent.putExtra("price", menuItemDataModelList.get(position).getPrice());
                    intent.putExtra("description", menuItemDataModelList.get(position).getDescription());
                    intent.putExtra("type", menuItemDataModelList.get(position).getType());
                    intent.putExtra("image_url", menuItemDataModelList.get(position).getImageData());
                    intent.putExtra("alternate_text", menuItemDataModelList.get(position).getAlternatetext());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        return convertView;
    }

        @Override
        public Filter getFilter() {
        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

        private class ValueFilter extends Filter {

            //Invoked in a worker thread to filter the data according to the constraint.
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new Filter.FilterResults();
                if (constraint != null && constraint.length() > 0) {
                    ArrayList<MenuItemDataModel> filterList = new ArrayList<MenuItemDataModel>();
                    for (int i = 0; i < mStringFilterList.size(); i++) {
                        if ((mStringFilterList.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            MenuItemDataModel menuItemDataModel = new MenuItemDataModel();
                            menuItemDataModel.setName(mStringFilterList.get(i).getName());
                            menuItemDataModel.setId(mStringFilterList.get(i).getId());
                            filterList.add(menuItemDataModel);
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = mStringFilterList.size();
                    results.values = mStringFilterList;
                }
                return results;
            }


            //Invoked in the UI thread to publish the filtering results in the user interface.
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                menuItemDataModelList = (ArrayList<MenuItemDataModel>) results.values;
                notifyDataSetChanged();
            }
        }



    }

