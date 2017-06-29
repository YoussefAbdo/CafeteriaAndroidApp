package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.OrderItemsAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderItems;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detials_activity);

        Intent intent = getIntent();
        String string = intent.getStringExtra("message");
        TextView idView = (TextView)findViewById(R.id.reference_number);
        idView.setText("Order number: " + string);

        String stringpayment = intent.getStringExtra("messagePayment");
        TextView paymentView = (TextView)findViewById(R.id.payment_done_order_details);
        paymentView.setText(stringpayment);

        String orderDate = intent.getStringExtra("messageOrderDate");
        String orderTime = intent.getStringExtra("messageOrderTime");
        TextView dateOrderView = (TextView)findViewById(R.id.date_order_details);
        TextView timeOrderView = (TextView)findViewById(R.id.time_order_details);
        dateOrderView.setText(orderDate);
        timeOrderView.setText(orderTime);

        String paymentMethod = intent.getStringExtra("messagePaymentMethod");
        TextView paymentMethodView = (TextView)findViewById(R.id.payment_method_order_details);
        paymentMethodView.setText(paymentMethod);

        String deliveryPlace = intent.getStringExtra("messageDeliveryPlace");
        TextView deliveryPlaceView = (TextView)findViewById(R.id.delivery_place_order_details);
        deliveryPlaceView.setText(deliveryPlace);

        String status = intent.getStringExtra("messageStatus");
        TextView statusView = (TextView)findViewById(R.id.status_order_details);
        statusView.setText(status);

        String DeliveryDate = intent.getStringExtra("messageOrderDate");
        String DeliveryTime = intent.getStringExtra("messageOrderTime");
        TextView dateDeilveryView = (TextView)findViewById(R.id.delivery_date_order_details);
        TextView timeDeilveryView = (TextView)findViewById(R.id.delivery_time_order_details);
        dateDeilveryView.setText(DeliveryDate);
        timeDeilveryView.setText(DeliveryTime);

        String TotalPrice = intent.getStringExtra("messageTotalPrice");
        TextView TotalPriceView = (TextView)findViewById(R.id.total_price_all_order_details);
        TotalPriceView.setText("Total Price: " + TotalPrice + " EGP");

        ArrayList<OrderItems> orderItemses = (ArrayList<OrderItems>) intent.getSerializableExtra("messageOrderedItems");

        // Find a reference to the {@link ListView} in the layout
        ListView orderItemsListView = (ListView) findViewById(R.id.list_of_ordered_items);

        // Create a new adapter that takes the list of OrderItems as input
        final OrderItemsAdapter orderItemsAdapter = new OrderItemsAdapter(this, orderItemses);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        orderItemsListView.setAdapter(orderItemsAdapter);
    }
}