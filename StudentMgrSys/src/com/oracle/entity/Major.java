package com.oracle.entity;

/**
 * 专业实体类
 */
public class Major {
    private int id;//编号
    private String name;//名称
    ////////////////////constructor////////////////////////////

    public Major() {
    }

    public Major(int id, String name) {
        this.id = id;
        this.name = name;
    }
    ////////////////////////getter&setter///////////////////////

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
}
