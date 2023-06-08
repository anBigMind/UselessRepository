package com.oracle.services;

import com.oracle.entity.User;
import com.oracle.sysDB.DB;

import java.util.Scanner;

public class UserService {
    Scanner input = new Scanner(System.in);
    DB db = DB.getDb();

    ///////////////////////////public methods//////////////////////////

    /**
     * 用户登录的方法
     * @return 登陆成功返回用户对象，登陆失败返回null
     */
    public User userLogin() {
        System.out.println("请输入用户名");
        String userName = input.next();
        System.out.println("请输入密码");
        String password = input.next();
        for (User user:db.getUsers()) {
            if (user != null){
                if (user.getName().equals(userName) && user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public void userSignIn() {
        if (countUserAmount() == db.getUsers().length){
            System.out.println("用户数量已满，无法注册");
            return;
        }
        System.out.println("请输入要注册的用户名");
        String userName = input.next();
        if (checkUserExistByName(userName)){
            System.out.println("该用户已存在");
        }else {
            System.out.println("请输入手机号");
            String phone = input.next();
            System.out.println("请输入密码");
            String pwd1 = input.next();
            System.out.println("请再次输入密码");
            String pwd2 = input.next();
            if (pwd2.equals(pwd1)){
                int id = countUserAmount()+1;
                db.getUsers()[id-1] = new User(id,userName,pwd2,phone);
                System.out.println("注册成功");
            }else {
                System.out.println("两次密码不一致，注册失败");
            }
        }
    }

    /**
     * 打印用户列表
     */
    public void printAllUserList() {
        System.out.println("=============================================================");
        for (User user: db.getUsers()) {
            if (user != null){
                System.out.println("编号："+user.getId()+"\t用户名："+user.getName()+"\t电话号码："+user.getPhone());
            }
        }
        System.out.println("=============================================================");
    }

    /**
     * 修改用户信息
     */
    public void userModify() {
        printAllUserList();
        System.out.println("请选择要修改的用户编号");
        int id = input.nextInt();
        User user = findUserById(id);
        if (user != null){
            System.out.println("请输入要更改的用户名");
            String userName = input.next();
            System.out.println("请输入要更改的电话号码");
            String phone = input.next();
            System.out.println("是否重置密码为000");
            String c =input.next();
            if ("y".equalsIgnoreCase(c)){
                user.setPassword("000");
            }
            user.setName(userName);
            user.setPhone(phone);
            System.out.println("修改成功");
        }else {
            System.out.println("该用户不存在");
        }
    }

    /**
     * 删除用户
     */
    public void delUser() {
        printAllUserList();
        System.out.println("请选择要删除的用户编号");
        int id = input.nextInt();
        if (findUserById(id) == null){
            System.out.println("该用户不存在");
            return;
        }
        moveUserTowardById(findUserById(id).getId());
        System.out.println("删除成功");
    }

    //////////////////////////////////private methods/////////////////////////////

    /**
     * 判断用户名是否存在
     * @param userName 要判断的用户名
     * @return 已存在返回true，不存在返回false
     */
    private boolean checkUserExistByName(String userName) {
        for (User user:db.getUsers()) {
            if (user!=null){
                if (user.getName().equals(userName)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 计算已存在多少用户
     * @return 现有用户数量
     */
    private int countUserAmount() {
        int c = 0;
        for (User user: db.getUsers()) {
            if (user != null){
                c++;
            }
        }
        return c;
    }

    /**
     * 根据id查找用户
     * @param id 要查找的id
     * @return 返回查找到的用户对象，未找到返回null
     */
    private User findUserById(int id) {
        for (User user:db.getUsers()) {
            if (user != null){
                if (user.getId() == id){
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * 根据编号移动覆盖删除用户
     * @param id 要删除的用户编号
     */
    private void moveUserTowardById(int id) {
        int i;
        for (i = id; i < db.getUsers().length; i++) {
            if (db.getUsers()[i] == null){
                break;
            }
            db.getUsers()[i-1] = db.getUsers()[i];
            db.getUsers()[i-1].setId(i);
        }
        db.getUsers()[i-1] = null;
    }
}
