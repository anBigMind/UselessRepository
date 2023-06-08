package com.oracle.sysUI;

import com.oracle.entity.Admin;
import com.oracle.entity.User;
import com.oracle.services.AdminService;
import com.oracle.services.UserService;

import java.util.Scanner;

public class UI {
    Scanner input = new Scanner(System.in);
    UserService userService = new UserService();
    AdminService adminService = new AdminService();

    ////////////////////////////////////一级菜单/////////////////////////////////

    /**
     * 登录选择菜单
     */
    public void mainMenu(){
        do {
            System.out.println("=============欢迎使用汽车保养系统=============");
            System.out.println("\t1.用户登录");
            System.out.println("\t2.管理员登录");
            System.out.println("\t3.用户注册");
            System.out.println("\t0.退出系统");
            System.out.println("===========================================");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    User user = userService.userLogin();
                    if (user != null){
                        userMainMenu(user);
                    }else {
                        System.out.println("用户名或密码错误");
                    }
                    continue;
                case "2":
                    Admin admin = adminService.adminLogin();
                    if (admin != null){
                        adminMainMenu(admin);
                    }else {
                        System.out.println("用户名或密码错误");
                    }
                    continue;
                case "3":
                    userService.userSignIn();
                    continue;
                case "0":
                    System.out.println("退出系统  ヾ(•ω•`)o");
                    break;
                default:
                    System.out.println("输入错误，请重新选择");
                    continue;
            }
            break;
        }while (true);
    }


    //////////////////////////////二级菜单/////////////////////////////////

    /**
     * 用户主菜单
     * @param user 登陆的用户
     */
    private void userMainMenu(User user) {
        System.out.println("=============欢迎,"+user.getName()+"=============");
        System.out.println("\t1.查看我的车辆");
        System.out.println("\t2.添加车辆");
        System.out.println("\t0.退出登录");
        System.out.println("===========================================");
    }

    /**
     * 管理员主菜单
     * @param admin 登陆的管理员
     */
    private void adminMainMenu(Admin admin) {
        do {
            System.out.println("=============欢迎,"+admin.getName()+"=============");
            System.out.println("\t1.用户管理");
            System.out.println("\t2.管理员管理");
            System.out.println("\t3.保养项目管理");
            System.out.println("\t4.车辆管理");
            System.out.println("\t0.退出登录");
            System.out.println("===========================================");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    userMgmt();
                    continue;
                case "2":
                    adminMgmt();
                    continue;
                case "3":
                    continue;
                case "4":
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
            break;
        }while (true);
    }


    ///////////////////////////////////管理员二级菜单//////////////////////////////////////

    /**
     * 用户管理菜单
     */
    private void userMgmt() {
        do {
            System.out.println("================用户管理菜单================");
            System.out.println("\t1.查看用户");
            System.out.println("\t2.修改用户");
            System.out.println("\t3.删除用户");
            System.out.println("\t4.添加用户");
            System.out.println("\t0.返回");
            System.out.println("===========================================");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    userService.printAllUserList();
                    continue;
                case "2":
                    userService.userModify();
                    continue;
                case "3":
                    userService.delUser();
                    continue;
                case "4":
                    userService.userSignIn();
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
            break;
        }while (true);
    }

    /**
     * 管理员管理
     */
    private void adminMgmt() {
        do {
            System.out.println("================管理员管理菜单================");
            System.out.println("\t1.查看管理员");
            System.out.println("\t2.修改管理员");
            System.out.println("\t3.删除管理员");
            System.out.println("\t4.添加管理员");
            System.out.println("\t0.返回");
            System.out.println("===========================================");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    adminService.printAllAdminList();
                    continue;
                case "2":
                    adminService.adminModify();
                    continue;
                case "3":
                    adminService.delAdmin();
                    continue;
                case "4":
                    adminService.addAdmin();
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
            break;
        }while (true);
    }
}
