package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by Esraa Hosny on 4/13/2017.
 */

public class CafeteriaDataModel {
    private int id ;
    private String name ;
    private String image;

    //constructor
    public CafeteriaDataModel()
    {}

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
