package com.oracle.services;

import com.oracle.entity.Admin;
import com.oracle.entity.User;
import com.oracle.sysDB.DB;

import java.util.Scanner;

public class AdminService {
    Scanner input = new Scanner(System.in);
    DB db = DB.getDb();

    ///////////////////////////public methods////////////////////////

    /**
     * 管理员登陆
     * @return 返回登陆的管理员对象，登陆失败返回null
     */
    public Admin adminLogin() {
        System.out.println("请输入用户名");
        String userName = input.next();
        System.out.println("请输入密码");
        String password = input.next();
        for (Admin admin:db.getAdmins()) {
            if (admin != null){
                if (admin.getName().equals(userName) && admin.getPassword().equals(password)){
                    return admin;
                }
            }
        }
        return null;
    }

    public void printAllAdminList() {
        System.out.println("=============================================================");
        for (Admin admin: db.getAdmins()) {
            if (admin != null){
                System.out.println("编号："+admin.getId()+"\t用户名："+admin.getName());
            }
        }
        System.out.println("=============================================================");
    }

    public void adminModify() {
        printAllAdminList();
        System.out.println("请选择要修改的管理员编号");
        int id = input.nextInt();
        Admin admin = findAdminById(id);
        if (admin != null){
            System.out.println("请输入要更改的用户名");
            String userName = input.next();
            System.out.println("是否重置密码为000");
            String c =input.next();
            if ("y".equalsIgnoreCase(c)){
                admin.setPassword("000");
            }
            admin.setName(userName);
            System.out.println("修改成功");
        }else {
            System.out.println("该用户不存在");
        }
    }

    /**
     * 删除管理员
     */
    public void delAdmin() {
        printAllAdminList();
        System.out.println("请选择要删除的用户编号");
        int id = input.nextInt();
        if (findAdminById(id) == null){
            System.out.println("该用户不存在");
            return;
        }
        moveAdminTowardById(findAdminById(id).getId());
        System.out.println("删除成功");
    }

    /**
     * 添加管理员
     */
    public void addAdmin() {
        if (countAdminAmount() == db.getAdmins().length){
            System.out.println("管理员数量已满，无法注册");
            return;
        }
        System.out.println("请输入要添加的管理员的用户名");
        String userName = input.next();
        if (checkAdminExistByName(userName)){
            System.out.println("该管理员已存在");
        }else {
            System.out.println("请输入密码");
            String pwd1 = input.next();
            System.out.println("请再次输入密码");
            String pwd2 = input.next();
            if (pwd2.equals(pwd1)){
                int id = countAdminAmount()+1;
                db.getAdmins()[id-1] = new Admin(id,userName,pwd2);
                System.out.println("添加成功");
            }else {
                System.out.println("两次密码不一致，添加失败");
            }
        }
    }




    ////////////////////////////////private methods///////////////////////////////////

    /**
     * 根据编号查找管理员
     * @param id 要查找的编号
     * @return 返回管理员对象，未找到返回null
     */
    private Admin findAdminById(int id) {
        for (Admin admin:db.getAdmins()) {
            if (admin != null){
                if (admin.getId() == id){
                    return admin;
                }
            }
        }
        return null;
    }

    /**
     * 根据编号移动覆盖删除管理员
     * @param id 要删除的管理员编号
     */
    private void moveAdminTowardById(int id) {
        int i;
        for (i = id; i < db.getAdmins().length; i++) {
            if (db.getAdmins()[i] == null){
                break;
            }
            db.getAdmins()[i-1] = db.getAdmins()[i];
            db.getAdmins()[i-1].setId(i);
        }
        db.getAdmins()[i-1] = null;
    }

    /**
     * 计算已存在的管理员总数
     * @return 已存在的管理员数量
     */
    private int countAdminAmount() {
        int c = 0;
        for (Admin admin: db.getAdmins()) {
            if (admin != null){
                c++;
            }
        }
        return c;
    }

    /**
     * 根据用户名判断管理员是否存在
     * @param userName
     * @return
     */
    private boolean checkAdminExistByName(String userName) {
        for (Admin admin:db.getAdmins()) {
            if (admin!=null){
                if (admin.getName().equals(userName)){
                    return true;
                }
            }
        }
        return false;
    }
}
