package com.oracle.studentSysDB;

import com.oracle.entity.Admin;
import com.oracle.entity.Major;
import com.oracle.entity.Student;

public class StudentSysDB {
    private Student[] students = new Student[5];
    private Major[] majors = new Major[5];
    private Admin[] admins = new Admin[5];
    private static StudentSysDB sysDB = new StudentSysDB();

////////////////////////////////constructor///////////////////////////////////
    private StudentSysDB() {
        initStudent();
        initMajor();
        initAdmin();
    }

    ////////////////////////////gettet&setter////////////////////////////////
    public static StudentSysDB getInstance(){//单例模式
        return sysDB;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Major[] getMajors() {
        return majors;
    }

    public void setMajors(Major[] majors) {
        this.majors = majors;
    }

    public Admin[] getAdmins() {
        return admins;
    }

    public void setAdmins(Admin[] admins) {
        this.admins = admins;
    }

    ///////////////////////////////////inital area/////////////////////////////////////

    private void initAdmin() {
        admins[0] = new Admin(1,"alex","7355608");
        admins[1] = new Admin(2,"jame","jame123");
        admins[2] = new Admin(3,"1","1");
    }

    private void initMajor() {
        majors[0] = new Major(1,"philosophy");
        majors[1] = new Major(2,"midageart");
        majors[2] = new Major(3,"mixmath");
        majors[3] = new Major(4,"computerscience");
    }

    private void initStudent() {
        students[0] = new Student(1,"john","male",20,"12345678909","firststreet",1);
        students[1] = new Student(2,"rachel","female",21,"08765432122","firststreet",2);
        students[2] = new Student(3,"doly","female",22,"34567899876","firststreet",3);
        students[3] = new Student(4,"donald","male",23,"96736732345","firststreet",4);
    }
}
