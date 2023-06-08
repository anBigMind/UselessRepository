package com.oracle.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private int recordNum;
    private String password;

    public User() {
    }

    public User(int id, String userName, int recordNum, String password) {
        this.id = id;
        this.userName = userName;
        this.recordNum = recordNum;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
