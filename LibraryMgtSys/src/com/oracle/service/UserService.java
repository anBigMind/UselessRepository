package com.oracle.service;

import com.oracle.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有的用户对象
     * @return 用户对象集合
     */
    List<User> getAllUsers();

    /**
     * 用户注册验证
     * @param userName 用户名
     * @param userPassword 密码
     * @return 返回登录的用户对象，用户名或密码错误返回null
     */
    User userLogin(String userName,String userPassword);

    /**
     * 修改用户密码
     * @param id 要修改密码的用户
     * @param pwd 要修改的密码
     * @return 成功返回true，失败返回false
     */
    boolean modifyUserPasswordById(int id, String pwd);

    /**
     * 获取用户借书数量
     * @param id 用户id
     * @return 借书数量
     */
    int getUserRecordNumById(int id);

    /**
     * 判断用户名是否已存在
     * @return 存在返回true，不存在返回false
     */
    boolean isNameExists(String userName);

    /**
     * 创建新用户
     * @param userName 用户名
     * @param pwd 密码
     * @return 影响行数
     */
    int createNewUser(String userName, String pwd);

    /**
     * 根据编号删除用户
     * @param id
     * @return 影响行数
     */
    int deleteUserById(int id);

    /**
     * 根据用户名模糊查询用户
     * @param userName
     * @return 查询到的用户集合
     */
    List<User> getUserByName(String userName);
}
