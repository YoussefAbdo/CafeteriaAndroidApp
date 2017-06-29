package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import java.io.Serializable;

/**
 * Created by EngYo on 22-Jun-17.
 */

public class OrderItems implements Serializable {
    private int mQuantity;
    private String mName;
    private double mPrice;
    private String mAdditions;
    private double mTotalPrice;
    //private String mComments;

    public OrderItems(int mQuantity, String mName, double mPrice, String mAdditions, double mTotalPrice/*, String mComments*/) {
        this.mQuantity = mQuantity;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mAdditions = mAdditions;
        this.mTotalPrice = mTotalPrice;
        //this.mComments = mComments;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public String getmName() {
        return mName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public String getmAdditions() {
        return mAdditions;
    }

    public double getmTotalPrice() {
        return mTotalPrice;
    }


    //    public String getmComments() {
//        return mComments;
//    }
}
