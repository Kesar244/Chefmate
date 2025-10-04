package com.example.chefkart.model;

public class CouponOutputModel {
    public boolean status;
    public String message;
    public CouponModel coupon_data;

    public CouponOutputModel(boolean status, String message, CouponModel coupon_data) {
        this.status = status;
        this.message = message;
        this.coupon_data = coupon_data;

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CouponModel getCoupon_data() {
        return coupon_data;
    }

    public void setCoupon_data(CouponModel coupon_data) {
        this.coupon_data = coupon_data;
    }
}
