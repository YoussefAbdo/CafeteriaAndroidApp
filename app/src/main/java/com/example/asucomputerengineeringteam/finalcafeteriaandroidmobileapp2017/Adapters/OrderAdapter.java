package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.Order;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

/**
 * Created by EngYo on 21-Jun-17.
 */

public class OrderAdapter extends ArrayAdapter<Order> {

    private static final String DATE_SEPARATOR = "T";


    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
    }

    /**
     * Returns a list item view that displays information about the order at the given position
     * in the list of orders.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_list_item, parent, false);
        }

        Order currentOrder = getItem(position);

        TextView statusView = (TextView) listItemView.findViewById(R.id.status);
        statusView.setText(currentOrder.getmStatus());


        String originalDate = currentOrder.getmDate();

        String date;
        String time;
        String[] parts = originalDate.split(DATE_SEPARATOR);
        date = parts[0];
        time = parts[1];

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(date);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(time);

        return listItemView;
    }
}
