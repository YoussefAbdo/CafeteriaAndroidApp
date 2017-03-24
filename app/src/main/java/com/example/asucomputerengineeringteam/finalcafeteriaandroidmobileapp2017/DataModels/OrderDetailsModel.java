package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by EngYo on 24-Mar-17.
 */

public class OrderDetailsModel {
    private int Id;
    private boolean PaymentDone;
    private String OrderTime;
    private String PaymentMethod;
    private String DeliveryPlace;
    private String OrderStatus;
    private String DeliveryTime;
    private int customerid;
    private int Credit;
//    private Customer customer;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isPaymentDone() {
        return PaymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        PaymentDone = paymentDone;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getDeliveryPlace() {
        return DeliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        DeliveryPlace = deliveryPlace;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    //    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
}
