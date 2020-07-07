package com.system.service.impl;

import com.system.dao.UserDao;
import com.system.domain.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 实现查询所有
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 实现保存
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean checkByUserName(String username) {
        User user= userDao.findByUserName(username);
        if(user!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User findOneByName(String username) {
        User user = userDao.findByUserName(username);
        return user;
    }
}
