package com.oracle.start;

import com.oracle.ui.StudentSysUI;

/**
 * 学生管理系统启动类
 */
public class StudentMgrSysStart {
    public static void main(String[] args) {
        StudentSysUI sysUI = new StudentSysUI();//创建系统UI类实例
        sysUI.adminLoginMenu();//管理员登陆菜单
    }
}
