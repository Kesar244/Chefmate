package com.example.chefkart.model;

public class CategoryModel{
    public String cat_name;
    public String cat_pic;

    public String id;

    public CategoryModel(String cat_name, String cat_pic, String id) {
        this.cat_name = cat_name;
        this.cat_pic = cat_pic;
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_pic() {
        return cat_pic;
    }

    public void setCat_pic(String cat_pic) {
        this.cat_pic = cat_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

