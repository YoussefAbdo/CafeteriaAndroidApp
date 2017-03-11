package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

import java.util.List;

/**
 * Created by Esraa Hosny on 2/28/2017.
 */

public class MenuItemModel {

    private String id ;
    private String name ;
    private String categoryId ;
    private String price;
    private String Description;
    private String Type ;
    private String photo;
    private String alternatetext ;
    private List<Category> categoryList;


    public MenuItemModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return Type;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public String getAlternatetext() {
        return alternatetext;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAlternatetext(String alternatetext) {
        this.alternatetext = alternatetext;
    }

    public String getPhoto() {
        return photo;
    }

    public static  class Category {

        private int Id;
        private String cafeteriaId;
        private String cafeteria ;
        private String name ;

    }
}
