package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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
            //holder.ordered_items_list_view = (ListView) convertView.findViewById(R.id.ordered_items_list_view);
            //holder.total_price_text_view = (TextView) convertView.findViewById(R.id.total_price_text_view);
            holder.id_text_view = (TextView) convertView.findViewById(R.id.id_text_view);
            holder.payment_done_text_view = (TextView) convertView.findViewById(R.id.payment_done_text_view);
            holder.order_time_text_view = (TextView) convertView.findViewById(R.id.order_time_text_view);
            holder.payment_method_text_view = (TextView) convertView.findViewById(R.id.payment_method_text_view);
            holder.delivery_place_text_view = (TextView) convertView.findViewById(R.id.delivery_place_text_view);
            holder.order_status_text_view = (TextView) convertView.findViewById(R.id.order_status_text_view);
            holder.delivery_time_text_view = (TextView) convertView.findViewById(R.id.delivery_time_text_view);
            holder.customerid_text_view = (TextView) convertView.findViewById(R.id.customerid_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        // Image progressBar
//        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
//
//        // Then later, when you want to display image
//        final ViewHolder finalHolder = holder;
//        ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), holder.ivMovieIcon, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                progressBar.setVisibility(View.VISIBLE);
//                finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                progressBar.setVisibility(View.GONE);
//                finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                progressBar.setVisibility(View.GONE);
//                finalHolder.ivMovieIcon.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//                progressBar.setVisibility(View.GONE);
//                finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
//            }
//        });


//        holder.ordered_items_list_view.

//        holder.total_price_text_view .setText("Total price: "+ orderDetailsModelList.get(position).get);

        holder.id_text_view.setText("Reference Number: " + orderDetailsModelList.get(position).getId());
        holder.payment_done_text_view.setText("Payment Status: " + orderDetailsModelList.get(position).isPaymentDone());
        holder.order_time_text_view.setText("Order Time: " + orderDetailsModelList.get(position).getOrderTime());
        holder.payment_method_text_view.setText("Payment Method: " + orderDetailsModelList.get(position).getPaymentMethod());
        holder.delivery_place_text_view.setText("Delivery Place: " + orderDetailsModelList.get(position).getDeliveryPlace());
        holder.order_status_text_view.setText("Order Status: " + orderDetailsModelList.get(position).getOrderStatus());
        holder.delivery_time_text_view.setText("Delivery Time: " + orderDetailsModelList.get(position).getDeliveryTime());
        holder.customerid_text_view.setText("Your ID: " + orderDetailsModelList.get(position).getCustomerid());
        return convertView;
    }

    class ViewHolder {

        //private ListView ordered_items_list_view;
        //private TextView total_price_text_view;
        private TextView id_text_view;
        private TextView payment_done_text_view;
        private TextView order_time_text_view;
        private TextView payment_method_text_view;
        private TextView delivery_place_text_view;
        private TextView order_status_text_view;
        private TextView delivery_time_text_view;
        private TextView customerid_text_view;
    }

}