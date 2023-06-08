package com.oracle.dao;

import com.oracle.entity.Admin;

import java.util.List;

public interface AdminDao {
    /**
     * 查询所有管理员
     * @return 管理员对象集合
     */
    List<Admin> selectAllAdmins();

    /**
     * 根据id修改管理员密码
     * @param id 管理员id
     * @param pwd 要修改的密码
     * @return 影响行数
     */
    int updateAdminPasswordById(int id, String pwd);
}
