package com.oracle.ui;

import com.oracle.service.AdminService;
import com.oracle.service.MajorService;
import com.oracle.service.StudentService;

import java.util.Scanner;

/**
 * 学生管理系统UI界面类
 */
public class StudentSysUI {
    Scanner input = new Scanner(System.in);
    MajorService majorService = new MajorService();
    StudentService studentService = new StudentService();
    AdminService adminService = new AdminService();

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
        String userName;
        do {
            System.out.println("请输入管理员名称：");
            userName = input.next();
            System.out.println("请输入密码：");
            String password = input.next();
            if (adminService.verifyAdmin(userName,password) == false){
                System.out.println("用户名或密码错误，请重新输入");
                continue;
            }
            System.out.println("登录成功!");
            break;
        }while (true);
        mainMenu(userName);//后台主界面菜单
    }

//////////////////////////////一级菜单/////////////////////////////////
    /**
     * 后台主界面菜单
     */
    private void mainMenu(String userName) {
        do {
            System.out.println("===============================");
            System.out.println("欢迎"+userName+"，目前有学生"+studentService.countStudentsAmount()+
                    "人，专业"+majorService.countMajorAmount()+"种");
            System.out.println("===============================");
            System.out.println("\t1.专业管理");
            System.out.println("\t2.学生管理");
            System.out.println("\t3.管理员管理");
            System.out.println("\t4.修改密码");
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
                case "4":
                    majorService.changePassword(userName);
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
                    majorService.printAllMajorList();
                    continue;
                case "2":
                    majorService.addMajor();
                    continue;
                case "3":
                    majorService.modifyMajor();
                    continue;
                case "4":
                    majorService.delMajor();
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
            System.out.println("\t2.根据姓氏查看学生");
            System.out.println("\t3.根据性别查看学生");
            System.out.println("\t4.根据专业查看学生");
            System.out.println("\t5.添加学生");
            System.out.println("\t6.修改学生");
            System.out.println("\t7.删除学生");
            System.out.println("\t0.返回");
            System.out.println("------------------------------");
            System.out.println("请选择");
            String c = input.next();
            switch (c){
                case "1":
                    studentService.printAllStudentList();
                    continue;
                case "2":
                    studentService.printStudentByLastName();
                    continue;
                case "3":
                    studentService.printStudentByGender();
                    continue;
                case "4":
                    studentService.printStudentByGrade();
                    continue;
                case "5":
                    studentService.addStudent();
                    continue;
                case "6":
                    studentService.modifyStudent();
                    continue;
                case "7":
                    studentService.delStudent();
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
                    adminService.printAllAdminList();
                    continue;
                case "2":
                    adminService.addAdmin();
                    continue;
                case "3":
                    adminService.modifyAdmin();
                    continue;
                case "4":
                    adminService.delAdmin();
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
