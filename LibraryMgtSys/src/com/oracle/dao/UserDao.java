package com.oracle.dao;

import com.oracle.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return 用户对象集合
     */
    List<User> selectAllUsers();

    /**
     * 根据id更改用户密码
     * @param id 用户id
     * @param pwd 要更改的密码
     * @return 更改语句影响行数
     */
    int updateUserPasswordById(int id, String pwd);

    /**
     * 查询用户借书记录数量
     * @param id 用户id
     * @return 借书记录数量
     */
    int selectUserRecordNum(int id);

    /**
     * 更新用户借书记录数量
     * @param i 操作符，1为加一，0为减一
     * @return 影响行数
     */
    int updateUserRecordNum(int i,int id);

    /**
     * 插入新用户
     * @param userName
     * @param pwd
     * @return 影响行数
     */
    int insertUser(String userName, String pwd);

    /**
     * 根据编号删除用户
     * @param id
     * @return 影响行数
     */
    int deleteUserById(int id);
}
