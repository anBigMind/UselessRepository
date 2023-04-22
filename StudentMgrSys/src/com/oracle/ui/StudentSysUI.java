package com.oracle.ui;

import java.util.Scanner;

/**
 * 学生管理系统UI界面类
 */
public class StudentSysUI {
    Scanner input = new Scanner(System.in);

    //////////////////////////唯一登录菜单///////////////////////////////
    /**
     * 管理员登陆菜单
     */
    public void adminLoginMenu(){
        System.out.println("===============================");
        System.out.println("\t欢迎使用《学生管理系统》（后台）");
        System.out.println("===============================");
        System.out.println("\t\t后台管理员登录");
        System.out.println("===============================");
        System.out.println("请输入管理员名称：");
        String userName = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        // TODO: 2023/4/22 调用管理员的业务，实现登录
        mainMenu();//后台主界面菜单
    }

//////////////////////////////一级菜单/////////////////////////////////
    /**
     * 后台主界面菜单
     */
    private void mainMenu() {
        do {
            System.out.println("===============================");
            System.out.println("\t1.专业管理");
            System.out.println("\t2.学生管理");
            System.out.println("\t3.管理员管理");
            System.out.println("\t0.退出登录");
            System.out.println("===============================");
            System.out.println("请选择：");
            String c = input.next();//获取用户选择
            switch (c){
                case "1":
                    majorMgrMenu();//专业管理菜单
                    continue;
                case "2":
                    studentMgrMenu();//学生管理菜单
                    continue;
                case "3":
                    adminMgrMenu();//管理员管理菜单
                    continue;
                case "0":
                    System.out.println("退出登录");
                    System.out.println("ヾ(￣▽￣)Bye~Bye~");
                    break;
                default:
                    System.out.println("输入错误，请重新选择!");
                    continue;
            }
            break;
        }while (true);
    }


    /////////////////////////////////二级菜单////////////////////////////////
    /**
     * 专业管理菜单
     */
    private void majorMgrMenu() {
        do {
            System.out.println("------------专业管理-----------");
            System.out.println("\t1.查看所有专业");
            System.out.println("\t2.添加专业");
            System.out.println("\t3.修改专业");
            System.out.println("\t4.删除专业");
            System.out.println("\t0.返回");
            System.out.println("------------------------------");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    continue;
                case "2":
                    continue;
                case "3":
                    continue;
                case "4":
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新选择!");
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 学生管理菜单
     */
    private void studentMgrMenu() {
        do {
            System.out.println("------------学生管理-----------");
            System.out.println("\t1.查看所有学生");
            System.out.println("\t2.添加学生");
            System.out.println("\t3.修改学生");
            System.out.println("\t4.删除学生");
            System.out.println("\t0.返回");
            System.out.println("------------------------------");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    continue;
                case "2":
                    continue;
                case "3":
                    continue;
                case "4":
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新选择!");
                    continue;
            }
            break;
        }while (true);
    }

    /**
     * 管理员管理菜单
     */
    private void adminMgrMenu() {
        do {
            System.out.println("-----------管理员管理-----------");
            System.out.println("\t1.查看所有管理员");
            System.out.println("\t2.添加管理员");
            System.out.println("\t3.修改管理员");
            System.out.println("\t4.删除管理员");
            System.out.println("\t0.返回");
            System.out.println("------------------------------");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    continue;
                case "2":
                    continue;
                case "3":
                    continue;
                case "4":
                    continue;
                case "0":
                    break;
                default:
                    System.out.println("输入错误，请重新选择!");
                    continue;
            }
            break;
        }while (true);
    }
}
