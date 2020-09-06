package com.example.geoapp.models;


public class Subscriber {

    public int accountImg;
    public String username;
    public Integer subCount;
    public Integer folCount;

    public Subscriber(int accountImg, String username, Integer subCount, Integer folCount) {
        this.accountImg = accountImg;
        this.username = username;
        this.subCount = subCount;
        this.folCount = folCount;
    }
}
