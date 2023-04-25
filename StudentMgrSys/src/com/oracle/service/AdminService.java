package com.oracle.service;

import com.oracle.entity.Admin;
import com.oracle.entity.Major;
import com.oracle.studentSysDB.StudentSysDB;

import java.util.Scanner;

public class AdminService {
    Scanner input = new Scanner(System.in);
    StudentSysDB sysDB = StudentSysDB.getInstance();

    //////////////////////////public methods///////////////////////////
    /**
     * 验证用户名和密码是否正确
     * @param userName 用户名
     * @param password 密码
     * @return 正确返回true，错误返回false
     */
    public boolean verifyAdmin(String userName, String password) {
        for (Admin admin:sysDB.getAdmins()) {
            if (admin != null){
                if (userName.equals(admin.getName()) && password.equals(admin.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打印所有的管理员列表
     */
    public void printAllAdminList() {
        System.out.println("---------管理员列表---------");
        for (Admin admin:sysDB.getAdmins()) {
            if (admin != null){
                System.out.println("编号" + admin.getId() + "\t" + "名称" + admin.getName());
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * 添加管理员的方法
     */
    public void addAdmin() {
        if (sysDB.getAdmins().length == countAdminAmount()){
            System.out.println("管理员已满，无法添加新管理员");
            return;
        }
        String adminName;
        String password;
        do {
            System.out.println("请输入要添加的管理员名称");
            adminName = input.next();
            if (checkAdminExist(adminName) == false){
                System.out.println("请输入密码");
                password = input.next();
                break;
            }
            System.out.println("已存在该管理员，请重新输入");
        }while (true);
        for (int i = 0; i < sysDB.getAdmins().length; i++) {// TODO: 2023/4/25
            if (sysDB.getAdmins()[i] == null){
                int id = countAdminAmount()+1;
                sysDB.getAdmins()[i] = new Admin(id,adminName,password);
                System.out.println("添加成功！");
                break;
            }
        }
    }

    /**
     * 修改管理员的方法
     */
    public void modifyAdmin() {
        printAllAdminList();
        System.out.println("请选择要修改的管理员编号");
        int id = input.nextInt();
        Admin admin = findAdminById(id);
        if (admin != null){
            do {
                System.out.println("请输入新名称");
                String adminName = input.next();
                if (checkAdminExist(adminName) == false){
                    admin.setName(adminName);
                    System.out.println("是否重置密码为000");
                    String c = input.next();
                    if ("y".equalsIgnoreCase(c)){
                        admin.setPassword("000");
                    }
                    System.out.println("修改成功！");
                    break;
                }else {
                    System.out.println("已存在该管理员，请重新输入");
                }
            }while (true);
        }else {
            System.out.println("输入的编号不存在");
        }
    }

    /**
     * 删除管理员的方法
     */
    public void delAdmin() {
        printAllAdminList();
        System.out.println("请输入要删除的管理员编号");
        int id = input.nextInt();
        if (findAdminById(id) != null){
            moveAdminTowardById(id);
            System.out.println("删除成功！");
        }else {
            System.out.println("输入的编号不存在，删除失败");
        }
    }


    ////////////////////////////////private methods///////////////////////////////////

    /**
     * 计算管理员总数的方法
     * @return 管理员总数量
     */
    private int countAdminAmount() {
        int c = 0;
        for (Admin admin: sysDB.getAdmins()) {
            if (admin != null){
                c++;
            }
        }
        return c;
    }

    /**
     * 根据管理员名字查看该管理员是否存在
     * @param adminName 管理员名称
     * @return 存在返回true，不存在返回false
     */
    private boolean checkAdminExist(String adminName) {
        for (Admin admin: sysDB.getAdmins()) {
            if (admin != null){
                if (adminName.equals(admin.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据编号查找管理员
     * @param id 要查找的编号
     * @return 找到的管理员，未找到返回null
     */
    private Admin findAdminById(int id) {
        for (Admin admin: sysDB.getAdmins()) {
            if (admin != null){
                if (admin.getId() == id){
                    return admin;
                }
            }
        }
        return null;
    }

    /**
     * 覆盖删除对应id的管理员
     * @param id 要删除管理员的id
     */
    private void moveAdminTowardById(int id) {
        int i;
        for (i = id-1; i < sysDB.getAdmins().length-1; i++) {
            if (sysDB.getAdmins()[i+1] == null){
                break;
            }
            sysDB.getAdmins()[i] = sysDB.getAdmins()[i+1];
            sysDB.getAdmins()[i].setId(i+1);
        }
        sysDB.getAdmins()[i] = null;
    }
}
