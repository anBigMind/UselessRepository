package com.oracle.service.impl;

import com.oracle.dao.AdminDao;
import com.oracle.dao.impl.AdminDaoImpl;
import com.oracle.entity.Admin;
import com.oracle.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();

    /**
     * 获取所有用户对象
     * @return 返回用户对象集合
     */
    @Override
    public List<Admin> getAllAdmins() {
        return adminDao.selectAllAdmins();
    }

    /**
     * 用户登陆验证
     * @param adminName 用户名
     * @param adminPassword 密码
     * @return 登录的用户，输入错误返回null
     */
    @Override
    public Admin adminLogin(String adminName, String adminPassword) {
        List<Admin> adminList = adminDao.selectAllAdmins();
        if (adminList != null)
            for (Admin a:adminList)
                if (a.getAdminName().equals(adminName) && a.getPassword().equals(adminPassword))
                    return a;
        return null;
    }

    /**
     * 修改管理员密码
     * @param admin 要修改密码的管理员
     * @param pwd 要修改的密码
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean modifyAdminPassword(Admin admin, String pwd) {
        int res = adminDao.updateAdminPasswordById(admin.getId(), pwd);
        return res > 0 ? true : false;
    }
}
