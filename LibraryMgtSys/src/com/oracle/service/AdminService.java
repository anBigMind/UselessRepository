package com.oracle.service;

import com.oracle.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 获得所有的管理员对象
     * @return 管理员对象集合
     */
    List<Admin> getAllAdmins();

    /**
     * 管理员登录验证
     * @param adminName 用户名
     * @param adminPassword 密码
     * @return 要登陆的管理员，输入错误返回null
     */
    Admin adminLogin(String adminName,String adminPassword);

    /**
     * 修改管理员密码
     * @param admin 要修改密码的管理员
     * @param pwd 要修改的密码
     * @return 成功返回true，失败返回false
     */
    boolean modifyAdminPassword(Admin admin, String pwd);
}
