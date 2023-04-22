package com.oracle.entity;

/**
 * 学生实体类
 */
public class Student {
    private int id;//学号
    private String name;//姓名
    private String sex;//性别
    private int age;//年龄
    private String phone;//电话
    private String address;//地址
    private int majorId;//专业编号
    /////////////////////constructor/////////////////////////////

    public Student() {
    }

    public Student(int id, String name, String sex, int age, String phone, String address, int majorId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.majorId = majorId;
    }
    ////////////////////getter&setter//////////////////////////

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }
}
