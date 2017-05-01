// I am working now on this

package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderDetailsModel;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.google.gson.Gson;

public class OrdersActivity extends AppCompatActivity {

    //private ListView ordered_items_list_view_orders;
    //private TextView total_price_text_view;
    private TextView id_text_view_orders;
    private TextView payment_done_text_view_orders;
    private TextView order_time_text_view_orders;
    private TextView payment_method_text_view_orders;
    private TextView delivery_place_text_view_orders;
    private TextView order_status_text_view_orders;
    private TextView delivery_time_text_view_orders;
    private TextView customerid_text_view_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // Showing and Enabling clicks on the Home/Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // setting up text views and stuff
        setUpUIViews();

        // recovering data from MainActivity, sent via intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String json = bundle.getString("orderDetailsModel"); // getting the model from MainActivity send via extras
            OrderDetailsModel orderDetailsModel = new Gson().fromJson(json, OrderDetailsModel.class);

//            // Then later, when you want to display image
//            ImageLoader.getInstance().displayImage(movieModel.getImage(), ivMovieIcon, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String imageUri, View view) {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingCancelled(String imageUri, View view) {
//                    progressBar.setVisibility(View.GONE);
//                }
//            });

            //ordered_items_list_view_orders setData (I must set the data of the ordered items here)
            //total_price_text_view_orders.setText(orderDetailsModel.get); (Total Price must be set here)
            id_text_view_orders.setText("Reference Number: " + String.valueOf(orderDetailsModel.getId()));
            payment_done_text_view_orders.setText("Payment Status: " + String.valueOf(orderDetailsModel.isPaymentDone()));
            order_time_text_view_orders.setText("Order Time: " + orderDetailsModel.getOrderTime());
            payment_method_text_view_orders.setText("Payment Method: " + orderDetailsModel.getPaymentMethod());
            delivery_place_text_view_orders.setText("Delivery Place: " + orderDetailsModel.getDeliveryPlace());
            order_status_text_view_orders.setText("Delivery Place: " + orderDetailsModel.getDeliveryPlace());
            order_status_text_view_orders.setText("Order Status: " + orderDetailsModel.getOrderStatus());
            delivery_time_text_view_orders.setText("Delivery Time: " + orderDetailsModel.getDeliveryTime());
            customerid_text_view_orders.setText("Your ID: " + String.valueOf(orderDetailsModel.getCustomerid()));

//            StringBuffer stringBuffer = new StringBuffer();
//            for(MovieModel.Cast cast : movieModel.getCastList()){
//                stringBuffer.append(cast.getName() + ", ");
//            }
//
//            tvCast.setText("Cast:" + stringBuffer);
//            tvStory.setText(movieModel.getStory());


        }
    }

    private void setUpUIViews() {
        //ordered_items_list_view_orders = (ListView) findViewById(R.id.ordered_items_list_view_orders);
        //total_price_text_view_orders = (TextView) findViewById(R.id.total_price_text_view_orders);
        id_text_view_orders = (TextView) findViewById(R.id.id_text_view_orders);
        payment_done_text_view_orders = (TextView) findViewById(R.id.payment_done_text_view_orders);
        order_time_text_view_orders = (TextView) findViewById(R.id.order_time_text_view_orders);
        payment_method_text_view_orders = (TextView) findViewById(R.id.payment_method_text_view_orders);
        delivery_place_text_view_orders = (TextView) findViewById(R.id.delivery_place_text_view_orders);
        order_status_text_view_orders = (TextView) findViewById(R.id.order_status_text_view_orders);
        delivery_time_text_view_orders = (TextView) findViewById(R.id.delivery_time_text_view_orders);
        customerid_text_view_orders = (TextView) findViewById(R.id.customerid_text_view_orders);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}