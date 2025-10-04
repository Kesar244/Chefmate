package com.example.chefkart.model;
import java.util.ArrayList;
public class BookingOutputModel {

    public boolean status;
    public String message;
    public ArrayList<BookingModel> booking;

    public BookingOutputModel(boolean status, String message, ArrayList<BookingModel> order) {
        this.status = status;
        this.message = message;
        this.booking = order;
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

    public ArrayList<BookingModel> getOrder() {
        return booking;
    }

    public void setOrder(ArrayList<BookingModel> order) {
        this.booking = order;
    }
}
