package com.oracle.entity;

/**
 * 管理员实体类
 */
public class Admin {
    private int id;//编号
    private String name;//姓名
    private String password;//密码
    //////////////////////////////constructor//////////////////////////

    public Admin() {
    }

    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //////////////////////////getter&setter//////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
