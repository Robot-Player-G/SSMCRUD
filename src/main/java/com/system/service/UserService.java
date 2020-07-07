package com.system.service;

import com.system.domain.User;

import java.util.List;

/**
 *用户的service接口
 */
public interface UserService {
    /**
     * 查询所有用户账号密码
     * @return
     */
    public List<User> findAll();

    /**
     * 保存用户账号密码
     * @param user
     */
    public void saveUser(User user);

    /**
     * 通过username查询数据库，看是否已存在同名用户，若存在返回true,反之则返回false
     * @param username
     * @return
     */
    public boolean checkByUserName(String username);

    /**
     * 通过username查询出对应的账户密码信息
     * @param username
     * @return
     */
    public User findOneByName(String username);
}
