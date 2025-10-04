package com.example.chefkart.model;

public class BookingModel {

    public String uid;
    public String pid;
    public int amount;

    public String name;
    public String pic;
    public int tot_amount;
    public String address;
    public String date;
    public String time;
    public String cooking_date;
    public String cooking_time;
    public int status;
    public String c_o;
    public String c_discount;
    public String c_code;

    public String id;

    public BookingModel(String uid, String pid, int amount, String name,
                        String pic, int tot_amount, String address,
                        String date, String time, String cooking_date,
                        String cooking_time, int status, String c_o,
                        String c_discount, String c_code, String id)
    {
        this.uid = uid;
        this.pid = pid;
        this.amount = amount;
        this.name = name;
        this.pic = pic;
        this.tot_amount = tot_amount;
        this.address = address;
        this.date = date;
        this.time = time;
        this.cooking_date = cooking_date;
        this.cooking_time = cooking_time;
        this.status = status;
        this.c_o = c_o;
        this.c_discount = c_discount;
        this.c_code = c_code;
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(int tot_amount) {
        this.tot_amount = tot_amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCooking_date() {
        return cooking_date;
    }

    public void setCooking_date(String cooking_date) {
        this.cooking_date = cooking_date;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getC_o() {
        return c_o;
    }

    public void setC_o(String c_o) {
        this.c_o = c_o;
    }

    public String getC_discount() {
        return c_discount;
    }

    public void setC_discount(String c_discount) {
        this.c_discount = c_discount;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}