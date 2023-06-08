package com.oracle.entity;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private int id;
    private Date borrowTime;
    private Date returnTime;
    private String ISBN;
    private int userId;

    public Record() {
    }

    public Record(int id, Date borrowTime, Date returnTime, String ISBN, int userId) {
        this.id = id;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.ISBN = ISBN;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
