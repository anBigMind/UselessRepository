package com.oracle.service;


import com.oracle.entity.Student;
import com.oracle.studentSysDB.StudentSysDB;

import java.util.Scanner;

public class StudentService {
    Scanner input = new Scanner(System.in);
    private StudentSysDB sysDB = StudentSysDB.getInstance();
    ///////////////////////////////public methods/////////////////////////////

    /**
     * 打印所有学生列表
     */
    public void printAllStudentList() {
        System.out.println("----------------------------------------学生列表-------------------------------------------");
        for (Student student:sysDB.getStudents()) {
            if (student != null){
                System.out.println("学号：" + student.getId() + "\t姓名：" + student.getName() + "\t年龄" + student.getAge() +
                        "\t性别：" + student.getSex() + "\t专业编号：" + student.getMajorId() + "\t地址：" + student.getAddress() +
                        "\t电话：" + student.getPhone());
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    /**
     * 添加学生的方法
     */
    public void addStudent() {
        if (sysDB.getStudents().length == countStudentsAmount()){
            System.out.println("学生已满，无法添加新学生");
            return;
        }
        String studentName;
        do {
            System.out.println("请输入要添加的学生名称");
            studentName = input.next();
            if (checkStudentExist(studentName) == false){
                break;
            }
            System.out.println("已存在该学生，请重新输入");
        }while (true);
        System.out.println("请输入学生年龄");
        int age = input.nextInt();
        System.out.println("请输入学生性别");
        String sex = input.next();
        System.out.println("请输入学生专业编号");
        int majorId = input.nextInt();
        System.out.println("请输入学生电话");
        String phone = input.next();
        System.out.println("请输入学生地址");
        String address = input.next();
        for (int i = 0; i < sysDB.getStudents().length; i++) {
            if (sysDB.getStudents()[i] == null){
                int id = countStudentsAmount()+1;
                sysDB.getStudents()[i] = new Student(id,studentName,sex,age,phone,address,majorId);
                System.out.println("添加成功！");
                break;
            }
        }
    }

    /**
     * 修改学生的方法
     */
    public void modifyStudent() {
        printAllStudentList();
        System.out.println("请选择要修改的学生学号");
        int id = input.nextInt();
        Student student = findStudentById(id);
        if (student != null){
            System.out.println("请输入学生姓名");
            String studentName = input.next();
            System.out.println("请输入学生年龄");
            int age = input.nextInt();
            System.out.println("请输入学生性别");
            String sex = input.next();
            System.out.println("请输入学生专业编号");
            int majorId = input.nextInt();
            System.out.println("请输入学生电话");
            String phone = input.next();
            System.out.println("请输入学生地址");
            String address = input.next();
            sysDB.getStudents()[id-1] = new Student(id,studentName,sex,age,phone,address,majorId);
            System.out.println("修改成功!");
        }else {
            System.out.println("输入的学号不存在");
        }
    }

    /**
     * 删除学生的方法
     */
    public void delStudent() {
        printAllStudentList();
        System.out.println("请输入要删除的学生学号");
        int id = input.nextInt();
        if (findStudentById(id) != null){
            moveStudentTowardById(id);
            System.out.println("删除成功！");
        }else {
            System.out.println("输入的学号不存在，删除失败");
        }
    }


    //////////////////////////////////private methods///////////////////////////////
    /**
     * 查看该学生是否存在
     * @param studentName 学生姓名
     * @return 存在返回true，不存在返回false
     */
    private boolean checkStudentExist(String studentName) {
        for (Student student: sysDB.getStudents()) {
            if (student != null){
                if (studentName.equals(student.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 计算学生总数
     * @return 学生的总数
     */
    private int countStudentsAmount() {
        int c = 0;
        for (Student student: sysDB.getStudents()) {
            if (student != null){
                c++;
            }
        }
        return c;
    }

    /**
     * 根据编号查找学生
     * @param id 要查找的编号
     * @return 返回查找到的学生，未找到返回false
     */
    private Student findStudentById(int id) {
        for (Student student: sysDB.getStudents()) {
            if (student != null){
                if (student.getId() == id){
                    return student;
                }
            }
        }
        return null;
    }

    /**
     * 覆盖删除所选的学生
     * @param id 所选的学生编号
     */
    private void moveStudentTowardById(int id) {
        int i;
        for (i = id-1; i < sysDB.getStudents().length-1; i++) {
            if (sysDB.getStudents()[i+1] == null){
                break;
            }
            sysDB.getStudents()[i] = sysDB.getStudents()[i+1];
            sysDB.getStudents()[i].setId(i+1);
        }
        sysDB.getStudents()[i] = null;
    }
}
