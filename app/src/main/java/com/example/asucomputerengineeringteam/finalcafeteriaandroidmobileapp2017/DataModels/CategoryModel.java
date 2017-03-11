package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import java.util.List;

/**
 * Created by Esraa Hosny on 2/28/2017.
 */

public class CategoryModel {

    private int id;
    private String name ;
    private  int cafeteriaId;
    private List<Cafeteria> cafeteriaList;

    //constructor
    public CategoryModel() {
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

}
