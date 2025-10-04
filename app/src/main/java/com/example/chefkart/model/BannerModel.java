package com.example.chefkart.model;

public class BannerModel{
    public String banner_title;
    public String banner_pic;
    public boolean status;

    public String id;

    public BannerModel(String banner_title, String banner_pic, boolean status, String id) {
        this.banner_title = banner_title;
        this.banner_pic = banner_pic;
        this.status = status;
        this.id = id;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_pic() {
        return banner_pic;
    }

    public void setBanner_pic(String banner_pic) {
        this.banner_pic = banner_pic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
