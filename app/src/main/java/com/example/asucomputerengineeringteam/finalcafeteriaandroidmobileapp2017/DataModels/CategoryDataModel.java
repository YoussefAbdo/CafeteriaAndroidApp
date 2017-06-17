package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import java.util.List;

/**
 * Created by Esraa Hosny on 2/28/2017.
 */

public class CategoryDataModel {

    private int id;
    private String name ;
    private String image;
    private  int cafeteriaId;
    private List<Cafeteria> cafeteriaList;

    //constructor
    public CategoryDataModel() {
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


    public static  class Cafeteria {

        private int cafId;
        private String cafName;
        private String ImageData ;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
