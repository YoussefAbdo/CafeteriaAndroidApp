package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderItems;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

/**
 * Created by EngYo on 22-Jun-17.
 */

public class OrderItemsAdapter extends ArrayAdapter<OrderItems> {

    public OrderItemsAdapter(Context context, List<OrderItems> orderItemses) {
        super(context, 0, orderItemses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_items_list_item, parent, false);
        }

        OrderItems currentOrderItems = getItem(position);

        TextView quantityView = (TextView) listItemView.findViewById(R.id.quantity_order_items_list_item);
        quantityView.setText(Integer.toString(currentOrderItems.getmQuantity()));

        TextView nameView = (TextView) listItemView.findViewById(R.id.name_order_items_list_item);
        nameView.setText(currentOrderItems.getmName());

        TextView additionsView = (TextView) listItemView.findViewById(R.id.additions_order_items_list_item);
        additionsView.setText(currentOrderItems.getmAdditions());

        TextView priceView = (TextView) listItemView.findViewById(R.id.price);
        priceView.setText("EGP " + Integer.toString(currentOrderItems.getmQuantity()) + "x " + String.valueOf(currentOrderItems.getmPrice()));

        TextView totalPriceView = (TextView) listItemView.findViewById(R.id.total_price_order_details);
        String totalPrice = Double.toString(currentOrderItems.getmTotalPrice());
        totalPriceView.setText(String.format(totalPrice, R.string.total_price));

//        TextView commentsView = (TextView) listItemView.findViewById(R.id.comments_order_items_list_item);
//        commentsView.setText(currentOrderItems.getmComments());

        return listItemView;
    }

}
