package com.oracle.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private int id;
    private String adminName;
    private String password;

    public Admin() {
    }

    public Admin(int id, String adminName, String password) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
