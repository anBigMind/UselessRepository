package com.oracle.service;

import com.oracle.entity.Admin;
import com.oracle.entity.Major;
import com.oracle.entity.Student;
import com.oracle.studentSysDB.StudentSysDB;

import java.util.Scanner;

public class MajorService {
    Scanner input = new Scanner(System.in);
    private StudentSysDB sysDB = StudentSysDB.getInstance();

    ///////////////////////public methods//////////////////////////
    /**
     * 打印所有专业的列表
     */
    public void printAllMajorList() {
        System.out.println("---------专业列表---------");
        for (Major major:sysDB.getMajors()) {
            if (major != null){
                System.out.println("编号" + major.getId() + "\t" + "名称" + major.getName());
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * 添加专业的方法
     */
    public void addMajor() {
        if (sysDB.getMajors().length == countMajorAmount()){
            System.out.println("专业已满，无法添加新专业");
            return;
        }
        String majorName;
        do {
            System.out.println("请输入要添加的专业名称");
            majorName = input.next();
            if (checkMajorExist(majorName) == false){
                break;
            }
            System.out.println("已存在该专业，请重新输入");
        }while (true);
        for (int i = 0; i < sysDB.getMajors().length; i++) {
            if (sysDB.getMajors()[i] == null){
                int id = countMajorAmount()+1;
                sysDB.getMajors()[i] = new Major(id,majorName);
                System.out.println("添加成功！");
                break;
            }
        }
    }

    /**
     * 修改专业的方法
     */
    public void modifyMajor() {
        printAllMajorList();
        System.out.println("请选择要修改的专业编号");
        int id = input.nextInt();
        Major major = findMajorById(id);
        if (major != null){
            do {
                System.out.println("请输入新名称");
                String majorName = input.next();
                if (checkMajorExist(majorName) == false){
                    major.setName(majorName);
                    System.out.println("修改成功！");
                    break;
                }else {
                    System.out.println("已存在该专业，请重新输入");
                }
            }while (true);
        }else {
            System.out.println("输入的编号不存在");
        }
    }

    /**
     * 删除专业的方法
     */
    public void delMajor() {
        printAllMajorList();
        System.out.println("请输入要删除的专业编号");
        int id = input.nextInt();
        if (findMajorById(id) != null){
            moveMajorTowardById(id);
            System.out.println("删除成功！");
        }else {
            System.out.println("输入的编号不存在，删除失败");
        }
    }

    /**
     * 更改管理员密码
     */
    public void changePassword(String userName) {
        System.out.println("请输入原密码");
        String pw = input.next();
        for (int i = 0; i < sysDB.getAdmins().length; i++) {
            if (sysDB.getAdmins()[i] != null){
                if ((sysDB.getAdmins()[i].getName().equals(userName)) && (sysDB.getAdmins()[i].getPassword().equals(pw))){
                    System.out.println("请输入要更改的密码");
                    String pw1 = input.next();
                    System.out.println("请再次输入密码");
                    String pw2 = input.next();
                    if (pw1.equals(pw2)){
                        sysDB.getAdmins()[i].setPassword(pw1);
                        System.out.println("修改成功！");
                        return;
                    }else {
                        System.out.println("输入错误，修改失败");
                        return;
                    }
                }
            }
        }
        System.out.println("密码错误");
    }

    /**
     * 计算已有专业总数
     * @return 专业总数
     */
    public int countMajorAmount() {
        int c = 0;
        for (Major major: sysDB.getMajors()) {
            if (major != null){
                c++;
            }
        }
        return c;
    }
    ///////////////////////private methods//////////////////////////

    /**
     * 查看专业名称是否已存在
     * @param majorName 要查看的名称
     * @return 存在返回true，不存在返回false
     */
    private boolean checkMajorExist(String majorName) {
        for (Major major: sysDB.getMajors()) {
            if (major != null){
                if (majorName.equals(major.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据编号查找专业
     * @param id 要查找的编号
     * @return 查找到的专业，未找到返回null
     */
    private Major findMajorById(int id) {
        for (Major major: sysDB.getMajors()) {
            if (major != null){
                if (major.getId() == id){
                    return major;
                }
            }
        }
        return null;
    }

    /**
     * 覆盖删除传入编号的专业
     * @param id 需要删除的专业编号
     */
    private void moveMajorTowardById(int id) {
        int i;
        for (i = id-1; i < sysDB.getMajors().length-1; i++) {
            if (sysDB.getMajors()[i+1] == null){
                break;
            }
            sysDB.getMajors()[i] = sysDB.getMajors()[i+1];
            sysDB.getMajors()[i].setId(i+1);
        }
        sysDB.getMajors()[i] = null;
    }

}
