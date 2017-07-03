package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Adapters.OrderAdapter;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.Order;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderItems;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.order;

public class OrdersActivity extends AppCompatActivity {

    public static final String LOG_TAG = OrdersActivity.class.getName();
    private static final String DATE_SEPARATOR = "T";

    private final String URL_TO_HIT = "http://cafeteriaappdemo.azurewebsites.net/api/order";
    private ProgressDialog dialog;

    private OrderAdapter mAdapter;
    ArrayList<OrderItems> orderItemses;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_activity);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data

        // Find a reference to the {@link ListView} in the layout
        ListView orderListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        orderListView.setEmptyView(mEmptyStateTextView);

        // Create a new mAdapter that takes the list of orders as input
        mAdapter = new OrderAdapter(this, new ArrayList<Order>());

        // Set the mAdapter on the {@link ListView}
        // so the list can be populated in the user interface
        orderListView.setAdapter(mAdapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Order currentOrder = mAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), OrderDetailsActivity.class);

                int id = currentOrder.getId();
                String orderId = Integer.toString(id);
                intent.putExtra("message", orderId);

                boolean paymentDone = currentOrder.ismPaymentDone();
                String paymentDonestring;
                if (paymentDone = true) {
                    paymentDonestring = "We received your payment";
                }
                else {
                    paymentDonestring = "Note paid yet";
                }
                intent.putExtra("messagePayment", paymentDonestring);

                String originalOrderDate = currentOrder.getmDate();
                String orderDate;
                String orderTime;
                String[] parts = originalOrderDate.split(DATE_SEPARATOR);
                orderDate = parts[0];
                orderTime = parts[1];
                intent.putExtra("messageOrderDate", orderDate);
                intent.putExtra("messageOrderTime", orderTime);

                String paymentMethod = currentOrder.getmPaymentMethod();
                intent.putExtra("messagePaymentMethod", paymentMethod);

                String deliveryPlace = currentOrder.getmDeliveryPlace();
                intent.putExtra("messageDeliveryPlace", deliveryPlace);

                String status = currentOrder.getmStatus();
                intent.putExtra("messageStatus", status);

                String originalDeliveryDate = currentOrder.getmDeliveryTime();
                String DeliveryDate;
                String DeliveryTime;
                String[] DeliveryTimeParts = originalDeliveryDate.split(DATE_SEPARATOR);
                DeliveryDate = DeliveryTimeParts[0];
                DeliveryTime = DeliveryTimeParts[1];
                intent.putExtra("messageDeliveryDate", DeliveryDate);
                intent.putExtra("messageDeliveryTime", DeliveryTime);

                String TotalPrice = String.valueOf(currentOrder.getmTotalPriceAll());
                intent.putExtra("messageTotalPrice", TotalPrice);

                intent.putExtra("messageOrderedItems", orderItemses);

                startActivity(intent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a networkd connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Start the AsyncTask to fetch the earthquake data
            OrderAsyncTask task = new OrderAsyncTask();
            task.execute(URL_TO_HIT);
        } else {
            dialog.dismiss();

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    private  class OrderAsyncTask extends AsyncTask<String, Void, OrderQueryUtils.DataRapper> {

        @Override
        protected OrderQueryUtils.DataRapper doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            OrderQueryUtils.DataRapper result = OrderQueryUtils.fetchOrderData(urls[0]);
            return result;        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(OrderQueryUtils.DataRapper orders) {
            super.onPostExecute(orders);
            dialog.dismiss();

            // Set empty state text to display "No orders found."
            mEmptyStateTextView.setText(R.string.no_orders);

            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (orders.getmOrder() != null && !orders.getmOrder().isEmpty()) {
                mAdapter.addAll(orders.getmOrder());
                orderItemses = orders.getmOrderItems();
            }
        }
    }
}

