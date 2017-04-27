package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("OrderItems")
    private List<OrderItems> orderItemsList;

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

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public static class OrderItems {

        private int ID;
        private int Quantity;
        private int OrderID;
        private int Order;
        private int MenuItemId;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int orderID) {
            OrderID = orderID;
        }

        public int getOrder() {
            return Order;
        }

        public void setOrder(int order) {
            Order = order;
        }

        public int getMenuItemId() {
            return MenuItemId;
        }

        public void setMenuItemId(int menuItemId) {
            MenuItemId = menuItemId;
        }
    }
}
