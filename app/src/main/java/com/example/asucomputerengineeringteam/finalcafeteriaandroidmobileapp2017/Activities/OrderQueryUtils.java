package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities;

import android.text.TextUtils;
import android.util.Log;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.Order;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.OrderItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.OrdersActivity.LOG_TAG;

/**
 * Created by EngYo on 21-Jun-17.
 */

public final class OrderQueryUtils {

    private OrderQueryUtils() {
    }

    public static DataRapper fetchOrderData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Order}s
        DataRapper orders = extractOrders(jsonResponse);

        // Return the list of {@link Order}s
        return orders;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static DataRapper extractOrders(String orderJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(orderJSON)) {
            return null;
        }

        ArrayList<Order> orders = new ArrayList<>();

        ArrayList<OrderItems> orderItemses = new ArrayList<>();

        DataRapper dataRapper = null;

        double TotalPriceAll = 0.0;


        try {
            JSONObject baseJsonResponse = new JSONObject(orderJSON);
            JSONArray orderArray = baseJsonResponse.getJSONArray("orders");
            for (int i = 0; i < orderArray.length(); i++) {
                JSONObject currentOrder = orderArray.getJSONObject(i);

                String orderTime = currentOrder.getString("OrderTime");
                String orderStatus = currentOrder.getString("OrderStatus");
                int id = currentOrder.getInt("Id");
                boolean paymentDone = currentOrder.getBoolean("PaymentDone");
                String paymentMethod  = currentOrder.getString("PaymentMethod");
                String deliveryPlace = currentOrder.getString("DeliveryPlace");
                String deliveryTime  = currentOrder.getString("DeliveryTime");



                JSONArray currentOrderItems = currentOrder.getJSONArray("OrderItems");
                for (int j = 0; j < currentOrderItems.length(); j++) {
                    JSONObject currentItem = currentOrderItems.getJSONObject(j);

                    int quantity = currentItem.getInt("Quantity");

                    JSONObject menuItem = currentItem.getJSONObject("MenuItem");
                    String name = menuItem.getString("Name");
                    String additions = menuItem.getString("Additions");
                    //String comments = menuItem.getString("Comments");
                    double price = menuItem.getInt("Price");

                    double TotalPrice = 0.0;
                    TotalPrice = price*quantity;

                    TotalPriceAll += TotalPrice;

                    OrderItems orderItems = new OrderItems(quantity, name, price, additions, TotalPrice/*, comments*/);
                    orderItemses.add(orderItems);

                    dataRapper = new DataRapper(orders,orderItemses);
                }

                Order order = new Order(orderTime, orderStatus, id, paymentDone, paymentMethod, deliveryPlace, deliveryTime, TotalPriceAll);
                orders.add(order);
            }
        }
        catch (JSONException e) {
            Log.e("OrderQueryUtils", "Problem parsing the order JSON results", e);
        }

        return dataRapper;
    }

    public static class DataRapper {
        private ArrayList<Order> mOrder;
        private ArrayList<OrderItems> mOrderItems;

        public DataRapper(ArrayList<Order> mOrder, ArrayList<OrderItems> mOrderItems) {
            this.mOrder = mOrder;
            this.mOrderItems = mOrderItems;
        }

        public ArrayList<Order> getmOrder() {
            return mOrder;
        }

        public ArrayList<OrderItems> getmOrderItems() {
            return mOrderItems;
        }
    }
}
