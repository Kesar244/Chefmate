package com.example.chefkart.model;

import java.io.Serializable;

public class ChefModel implements Serializable {
    public String chef_name;
    public String chef_pic;
    public String mob_no;
    public String email_id;
    public String address;
    public String veg_nonveg_both;
    public String cat_name;
    public String amount;
    public String ratings;

    public String id;

    public ChefModel(String chef_name, String chef_pic, String mob_no, String email_id, String address, String veg_nonveg_both, String cat_name, String amount,String ratings, String id) {
        this.chef_name = chef_name;
        this.chef_pic = chef_pic;
        this.mob_no = mob_no;
        this.email_id = email_id;
        this.address = address;
        this.veg_nonveg_both = veg_nonveg_both;
        this.cat_name = cat_name;
        this.amount = amount;
        this.ratings=ratings;
        this.id = id;
    }

    public String getChef_name() {
        return chef_name;
    }

    public void setChef_name(String chef_name) {
        this.chef_name = chef_name;
    }

    public String getChef_pic() {
        return chef_pic;
    }

    public void setChef_pic(String chef_pic) {
        this.chef_pic = chef_pic;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVeg_nonveg_both() {
        return veg_nonveg_both;
    }

    public void setVeg_nonveg_both(String veg_nonveg_both) {
        this.veg_nonveg_both = veg_nonveg_both;
    }

    public String getcat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRatings() {return ratings;}

    public void setRatings(String ratings){this.ratings=ratings;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
