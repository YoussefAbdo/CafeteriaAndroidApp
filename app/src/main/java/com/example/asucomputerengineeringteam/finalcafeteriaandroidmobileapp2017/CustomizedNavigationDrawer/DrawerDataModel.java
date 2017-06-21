package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.CustomizedNavigationDrawer;

/**
 * Created by Esraa Hosny on 6/21/2017.
 */

public class DrawerDataModel {
    private int Item;
    private String Label;

    public DrawerDataModel(int item, String label) {
        this.Item = item;
        this.Label=label;
    }

    public int getItem() {
        return Item;
    }

    public void setItem(int item) {
        Item = item;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
