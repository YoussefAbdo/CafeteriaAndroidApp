package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by Esraa Hosny on 3/11/2017.
 */

public class AdditonModel {
    private int id;
    private String name;
    boolean selected = false;

    public AdditonModel() {
    }

    public AdditonModel(boolean selected, int id, String name) {
        super();
        this.selected = selected;
        this.id = id;
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
