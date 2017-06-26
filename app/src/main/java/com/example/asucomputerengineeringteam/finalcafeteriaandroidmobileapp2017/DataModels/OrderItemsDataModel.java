package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esraa Hosny on 6/26/2017.
 */

public class OrderItemsDataModel  {

    private int id;
    private int quantity;
    private int menuitem_id;
    private int customer_id;

//    @SerializedName("orderItems")
//    private List<OrderList> orderList ;
//
//    @SerializedName("orderItems")
//    private List<MenuItemList> orderList ;


    public OrderItemsDataModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMenuitem_id() {
        return menuitem_id;
    }

    public void setMenuitem_id(int menuitem_id) {
        this.menuitem_id = menuitem_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

   /* public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }*/
}
