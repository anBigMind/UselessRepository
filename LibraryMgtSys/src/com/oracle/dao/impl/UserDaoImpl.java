package com.oracle.dao.impl;

import com.oracle.dao.BaseDao;
import com.oracle.dao.UserDao;
import com.oracle.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 查询所有用户
     * @return 用户对象集合
     */
    @Override
    public List<User> selectAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from tb_user";
        ResultSet rs = this.executeQuery(sql);
        User user = null;
        try {
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setRecordNum(rs.getInt("record_num"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return userList;
    }

    /**
     * 更改用户密码
     * @param id 用户id
     * @param pwd 要更改的密码
     * @return 影响行数
     */
    @Override
    public int updateUserPasswordById(int id, String pwd) {
        String sql = "UPDATE tb_user SET `password` = ? WHERE id = ?;";
        return executeUpdate(sql,pwd,id);
    }

    @Override
    public int selectUserRecordNum(int id) {
        String sql = "SELECT record_num FROM tb_user WHERE id = ?;";
        ResultSet rs = this.executeQuery(sql,id);
        int res = 0;
        try {
            if (rs.next())
                res = rs.getInt("record_num");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll(rs,ps,conn);
        }
        return res;
    }

    /**
     * 更新用户借书记录数量
     * @param i 操作符，1为加一，0为减一
     * @return 影响行数
     */
    @Override
    public int updateUserRecordNum(int i,int id) {
        String sql = "UPDATE tb_user SET record_num = ? WHERE id = ?;";
        int num = selectUserRecordNum(id);
        int res = 0;
        if (i == 1){
            res = executeUpdate(sql,num+1,id);
        } else if (i == 0) {
            res = executeUpdate(sql,num-1,id);
        }
        return res;
    }

    /**
     * 插入新用户
     * @param userName
     * @param pwd
     * @return 影响行数
     */
    @Override
    public int insertUser(String userName, String pwd) {
        String sql = "INSERT INTO tb_user(user_name,`password`)VALUES(?,?);";
        return executeUpdate(sql,userName,pwd);
    }

    /**
     * 根据编号删除用户
     * @param id
     * @return 影响行数
     */
    @Override
    public int deleteUserById(int id) {
        String sql = "DELETE FROM tb_user WHERE id = ?;";
        return executeUpdate(sql,id);
    }

    @Override
    public List<User> selectUserByName(String userName) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM tb_user WHERE user_name LIKE ?;";
        userName = "%"+userName+"%";
        ResultSet rs = this.executeQuery(sql,userName);
        User user = null;
        try {
            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setRecordNum(rs.getInt("record_num"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return userList;
    }
}
