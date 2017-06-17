package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import java.util.List;

/**
 * Created by Esraa Hosny on 2/28/2017.
 */

public class MenuItemDataModel {

    private int id ;
    private double price;
    private String name ;
    private int categoryId ;
    private String Description;
    private String Type ;
    private String alternatetext;
    private String ImageData;
    private List<Category> categoryList;


    public MenuItemDataModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAlternatetext() {
        return alternatetext;
    }

    public void setAlternatetext(String alternatetext) {
        this.alternatetext = alternatetext;
    }

    public String getImageData() {
        return ImageData;
    }

    public void setImageData(String imageData) {
        ImageData = imageData;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }



    public static  class Category {

        private int Id;
        private String cafeteriaId;
        private String cafeteria ;
        private String name ;

    }


}
