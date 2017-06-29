package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by EngYo on 21-Jun-17.
 */

public class Order {
    private int mId;
    private boolean mPaymentDone;
    private String mDate;
    private String mPaymentMethod;
    private String mDeliveryPlace;
    private String mStatus;
    private String mDeliveryTime;
    private double mTotalPriceAll;

    public Order(String mDate, String mStatus, int mId, boolean mPaymentDone, String mPaymentMethod, String mDeliveryPlace, String mDeliveryTime, double mTotalPriceAll) {
        this.mDate = mDate;
        this.mStatus = mStatus;
        this.mId = mId;
        this.mPaymentDone = mPaymentDone;
        this.mPaymentMethod = mPaymentMethod;
        this.mDeliveryPlace = mDeliveryPlace;
        this.mDeliveryTime = mDeliveryTime;
        this.mTotalPriceAll = mTotalPriceAll;
    }

    public int getId() {
        return mId;
    }

    public boolean ismPaymentDone() {
        return mPaymentDone;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmPaymentMethod() {
        return mPaymentMethod;
    }

    public String getmDeliveryPlace() {
        return mDeliveryPlace;
    }

    public String getmStatus() {

        return mStatus;
    }

    public String getmDeliveryTime() {
        return mDeliveryTime;
    }

    public double getmTotalPriceAll() {
        return mTotalPriceAll;
    }
}