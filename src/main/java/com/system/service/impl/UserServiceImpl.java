package com.system.service.impl;

import com.system.dao.UserDao;
import com.system.domain.Message;
import com.system.domain.RechargeRecord;
import com.system.domain.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
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
     * @param username
     * @param password
     */
    @Override
    public void saveUser(String username,String password) {
        userDao.saveUser(username,password);
    }


    @Override
    public User findOneByName(String username) {
        User user = userDao.findUserInfoByName(username);
        return user;
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public void modifyUserInfo(User user) {
        userDao.modifyUserInfo(user);
    }

    /**
     * 根据username获取管理员的留言
     * @param username
     * @return
     */
    @Override
    public List<Message> getAMessageByUsername(String username) {
        return userDao.getAMessageByUsername(username);
    }

    @Override
    public List<Message> getMessage(String userA, String userB) {
        return userDao.getMessage(userA,userB);
    }

    @Override
    public void leaveMessage(String userA, String userB, String content) {
        Message message = new Message();
        message.setFrom(userA);
        message.setTo(userB);
        message.setContent(content);
        message.setTime(new Date());
        message.setType(1);
        userDao.leaveMessage(message);
    }

    @Override
    public void sendMessageToAdmin(Message message) {
        userDao.sendMessageToAdmin(message);
    }

    @Override
    public boolean isExist(String username) {
        if (userDao.isExist(username)!= null){
            return true;
        }
        return false;
    }

    @Override
    public Integer getBalanceByUsername(String username) {
        return userDao.getBalanceByUsername(username);
    }

    @Override
    public void rechargeMoney(String username, Integer balance) {
        Integer SqlBalance = getBalanceByUsername(username);
        Integer balance1 = balance + SqlBalance;
        userDao.addBalanceByUsername(username,balance1);
    }

    @Override
    public void addRechargeRecord(RechargeRecord record) {
        userDao.addRechargeRecord(record);
    }

    @Override
    public void saveUserInfo(String username, String nickname, String sex, Date date) {
        userDao.saveUserInfo(username,nickname,sex,date,0);
    }
}
