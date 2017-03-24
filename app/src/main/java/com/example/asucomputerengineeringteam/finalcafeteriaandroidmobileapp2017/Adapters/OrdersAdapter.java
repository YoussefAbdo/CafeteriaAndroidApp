//THIS CLASS MUST BE DELETED
//IT HAS NO USE


//package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrdersModel;
//import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
//
//import java.util.List;
//
///**
// * Created by Esraa Hosny on 3/11/2017.
// */
//
//public class OrdersAdapter extends BaseAdapter {
//    //initialize context , arraylist
//    public Context context;
//    public List<OrdersModel> ordersModelList;
//
//    public OrdersAdapter(Context context, List<OrdersModel> ordersModelList) {
//        this.context = context;
//        this.ordersModelList = ordersModelList;
//    }
//
//    @Override
//    public int getCount() {
//        return ordersModelList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return ordersModelList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.order_row_item, parent, false);
//
//
//        TextView cafname = (TextView) convertView.findViewById(R.id.cafname);
//        TextView order_time = (TextView) convertView.findViewById(R.id.order_time);
//
//
//        //gone
//        TextView delivered_order_text = (TextView) convertView.findViewById(R.id.delivered_order_text);
//        delivered_order_text.setText("your order is finished ");
//        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
//
//
//        return convertView;
//    }
//}
