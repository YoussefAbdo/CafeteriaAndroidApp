package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderDetailsModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by EngYo on 24-Mar-17.
 */

public class OrderDetailsAdapter extends ArrayAdapter {
    public List<OrderDetailsModel> orderDetailsModelList;
    private int resource;
    private LayoutInflater inflater;

    public OrderDetailsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<OrderDetailsModel> objects) {
        super(context, resource, objects);
        orderDetailsModelList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(resource, null);

            holder.ordered_items_list_view = (ListView) convertView.findViewById(R.id.ordered_items_list_view);

            holder.total_price_text_view = (TextView) convertView.findViewById(R.id.total_price_text_view);
            holder.payment_done_text_view = (TextView) convertView.findViewById(R.id.payment_done_text_view);
            holder.order_time_text_view = (TextView) convertView.findViewById(R.id.order_time_text_view);
            holder.payment_method_text_view = (TextView) convertView.findViewById(R.id.payment_method_text_view);
            holder.delivery_place_text_view = (TextView) convertView.findViewById(R.id.delivery_place_text_view);
            holder.order_status_text_view = (TextView) convertView.findViewById(R.id.order_status_text_view);
            holder.delivery_time_text_view = (TextView) convertView.findViewById(R.id.delivery_time_text_view);
            holder.credit_text_view = (TextView) convertView.findViewById(R.id.credit_text_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.ordered_items_list_view.

//        holder.total_price_text_view .setText("Total price: "+ orderDetailsModelList.get(position).get);

        holder.payment_done_text_view.setText("Payment Status: " + orderDetailsModelList.get(position).getPaymentMethod());
        holder.order_time_text_view.setText("Order Time: " + orderDetailsModelList.get(position).getOrderTime());
        holder.payment_method_text_view.setText("Payment Method: " + orderDetailsModelList.get(position).getPaymentMethod());
        holder.delivery_place_text_view.setText("Delivery Place: " + orderDetailsModelList.get(position).getDeliveryPlace());
        holder.order_status_text_view.setText("Order Status: " + orderDetailsModelList.get(position).getOrderStatus());
        holder.delivery_time_text_view.setText("Delivery Time: " + orderDetailsModelList.get(position).getDeliveryTime());
        holder.credit_text_view.setText("Current credit: " + orderDetailsModelList.get(position).getCredit());

        return convertView;
    }

    class ViewHolder {

        private ListView ordered_items_list_view;

        private TextView total_price_text_view;
        private TextView payment_done_text_view;
        private TextView order_time_text_view;
        private TextView payment_method_text_view;
        private TextView delivery_place_text_view;
        private TextView order_status_text_view;
        private TextView delivery_time_text_view;
        private TextView credit_text_view;
    }

}

