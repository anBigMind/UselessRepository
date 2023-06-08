package com.oracle.entity;

import java.util.Date;

public class Car {
    private int id;
    private String carNo;
    private String brand;
    private String type;
    private Date buyDate;
    private int currentkm;
    private int userId;
    ///////////////////////////constructors/////////////////////////
    public Car() {
    }

    public Car(int id, String carNo, String brand, String type, Date buyDate, int currentkm, int userId) {
        this.id = id;
        this.carNo = carNo;
        this.brand = brand;
        this.type = type;
        this.buyDate = buyDate;
        this.currentkm = currentkm;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public int getCurrentkm() {
        return currentkm;
    }

    public void setCurrentkm(int currentkm) {
        this.currentkm = currentkm;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
