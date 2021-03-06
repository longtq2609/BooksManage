package com.example.bookmanagement.model;

import java.io.Serializable;

public class User {
    private String username;
    private String password;
    private String phone;
    private String name;


    public User(String username, String password, String phone, String name) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "User{" +
                "username='" +username+'\''+
                ", password='"+password+'\''+
                ", phone='"+phone+'\''+
                ", name='"+name+'\''+
                '}';
    }
}
