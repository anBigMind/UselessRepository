package com.oracle.service.impl;

import com.oracle.dao.UserDao;
import com.oracle.dao.impl.UserDaoImpl;
import com.oracle.entity.User;
import com.oracle.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    /**
     * 获取所有用户对象
     * @return 用户对象集合
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    /**
     * 用户登录验证
     * @param userName 用户名
     * @param userPassword 密码
     * @return 登录的用户对象，输入错误返回null
     */
    @Override
    public User userLogin(String userName, String userPassword) {
        List<User> userList = userDao.selectAllUsers();
        if (userList != null)
            for (User u:userList)
                if (u.getUserName().equals(userName) && u.getPassword().equals(userPassword))
                    return u;
        return null;
    }

    /**
     * 修改用户密码
     * @param id 要修改密码的用户id
     * @param pwd 要修改的密码
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean modifyUserPasswordById(int id, String pwd) {
        int res = userDao.updateUserPasswordById(id, pwd);
        return res > 0 ? true : false;
    }

    /**
     * 获取用户借书数量
     * @param id 用户id
     * @return 借书数量
     */
    @Override
    public int getUserRecordNumById(int id) {
        return userDao.selectUserRecordNum(id);
    }

    /**
     * 判断用户名是否存在
     * @param userName
     * @return 存在返回true，不存在返回false
     */
    @Override
    public boolean isNameExists(String userName) {
        List<User> allUsers = getAllUsers();
        for (User u:allUsers) {
            if (u.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 创建新用户
     * @param userName 用户名
     * @param pwd 密码
     * @return 影响行数
     */
    @Override
    public int createNewUser(String userName, String pwd) {
        return userDao.insertUser(userName,pwd);
    }

    /**
     * 根据编号删除用户
     * @param id
     * @return 影响行数
     */
    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }
}
