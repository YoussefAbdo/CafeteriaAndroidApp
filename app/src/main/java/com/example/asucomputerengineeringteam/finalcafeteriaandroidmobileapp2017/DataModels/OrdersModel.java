package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import android.location.Location;

import java.sql.Time;
import java.util.Calendar;

import static android.R.attr.id;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Esraa Hosny on 3/11/2017.
 */

public class OrdersModel {
    private int Id;
    private Time DeliverytTime;   //get
    private Time OrderTime;  // stt
    private boolean PaymentDone;
    private String PaymentMethod;
    private String OrderStatus;
    private LatLng DeliveryPlace;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LatLng getDeliveryPlace() {
        return DeliveryPlace;
    }

    public void setDeliveryPlace(LatLng deliveryPlace) {
        DeliveryPlace = deliveryPlace;
    }

    public Time getDeliverytTime() {
        return DeliverytTime;
    }

    public void setDeliverytTime(Time deliverytTime) {
        DeliverytTime = deliverytTime;
    }

    public Time getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(Time orderTime) {
        OrderTime = orderTime;
    }

    public boolean isPaymentDone() {
        return PaymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        PaymentDone = paymentDone;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
