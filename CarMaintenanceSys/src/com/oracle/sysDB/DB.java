package com.oracle.sysDB;

import com.oracle.entity.Admin;
import com.oracle.entity.Car;
import com.oracle.entity.User;

import java.util.Date;

public class DB {
    private User[] users = new User[10];
    private Admin[] admins = new Admin[10];
    private Car[] cars = new Car[20];
    private static DB db = new DB();
    /////////////////////////constructors////////////////////////

    private DB() {
        users[0] = new User(1,"howard","7355608","123456789");
        users[1] = new User(2,"henry","rtyu678","123456789");
        users[2] = new User(3,"alex","ju5678","123456789");
        users[3] = new User(4,"jame","7355","123456789");
        users[4] = new User(5,"1","1","010011010");

        admins[0] = new Admin(1,"bill","bill123");
        admins[1] = new Admin(2,"peter","iamadminhhh");
        admins[2] = new Admin(3,"1","1");

        cars[0] = new Car(1,"38971","ford","van",new Date(),1421,1);
        cars[1] = new Car(2,"32631","ford","van",new Date(),333,2);
        cars[2] = new Car(3,"17231","ford","van",new Date(),1716,3);
        cars[3] = new Car(4,"323631","ford","van",new Date(),21522,4);
        cars[4] = new Car(5,"salhl","ford","van",new Date(),1252,5);
        cars[5] = new Car(6,"sd89a8","fiat","car",new Date(),12,1);
        cars[6] = new Car(7,"2k2q2k","fiat","car",new Date(),16666,2);
        cars[7] = new Car(8,"sa99d","fiat","car",new Date(),63445,3);
        cars[8] = new Car(9,"98uj","fiat","car",new Date(),8344,4);
        cars[9] = new Car(10,"gtyu890","fiat","car",new Date(),7437,5);
    }
    ////////////////////////getter&setter////////////////////////
    public static DB getDb(){
        return db;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Admin[] getAdmins() {
        return admins;
    }

    public void setAdmins(Admin[] admins) {
        this.admins = admins;
    }
}
