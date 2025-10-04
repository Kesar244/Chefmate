package com.example.chefkart.model;

import java.io.Serializable;

public class PersonModel implements Serializable {

    String id,username,email,password,phone,status,updated_at,created_at;

    public PersonModel(String id, String username, String email, String password, String phone, String status, String updatede_at, String created_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.updated_at = updatede_at;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatede_at() {
        return updated_at;
    }

    public void setUpdatede_at(String updatede_at) {
        this.updated_at = updatede_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
