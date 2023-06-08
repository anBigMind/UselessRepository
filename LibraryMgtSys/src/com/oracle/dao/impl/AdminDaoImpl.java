package com.oracle.dao.impl;

import com.oracle.dao.AdminDao;
import com.oracle.dao.BaseDao;
import com.oracle.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    /**
     * 查询所有管理员
     * @return 管理员对象集合
     */
    @Override
    public List<Admin> selectAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        String sql = "select * from tb_admin";
        ResultSet rs = this.executeQuery(sql);
        Admin admin = null;
        try {
            while (rs.next()){
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setAdminName(rs.getString("admin_name"));
                admin.setPassword(rs.getString("password"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return adminList;
    }

    /**
     * 根据管理员id修改密码
     * @param id 管理员id
     * @param pwd 要修改的密码
     * @return 影响行数
     */
    @Override
    public int updateAdminPasswordById(int id, String pwd) {
        String sql = "UPDATE tb_admin SET `password` = ? WHERE id = ?;";
        return executeUpdate(sql,pwd,id);
    }
}
