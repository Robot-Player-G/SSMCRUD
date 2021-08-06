package com.system.service;

import com.system.domain.Message;
import com.system.domain.RechargeRecord;
import com.system.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
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
     *  保存用户账号密码
     * @param username
     * @param password
     */
    public void saveUser(String username,String password);

    /**
     * 通过username查询出对应的账户密码信息
     * @param username
     * @return
     */
    public User findOneByName(String username);

    /**
     * 通过username查询用户名和密码
     * @param username
     * @return
     */
    public User findUserByName(String username);

    /**
     * 通过username修改个人信息
     * @param user
     */
    public void modifyUserInfo(User user);

    /**
     * 根据username获取管理员的留言
     * @param username
     * @return
     */
    public List<Message> getAMessageByUsername(String username);

    /**
     * 用户获取留言
     * @param userA
     * @param userB
     * @return
     */
    public List<Message> getMessage(String userA,String userB);

    /**
     * 用户给用户发送留言
     * @param userA
     * @param userB
     * @param content
     */
    public void leaveMessage(String userA,String userB,String content);

    /**
     * 用户给管理员留言
     * @param message
     */
    public void sendMessageToAdmin(Message message);

    /**
     * 通过username检测用户是否已存在
     * @param username
     * @return
     */
    public boolean isExist(String username);

    /**
     * 通过username获取数据库里的余额
     * @param username
     * @return
     */
    public Integer getBalanceByUsername(String username);

    /**
     * 通过username修改数据库中的余额
     * @param username
     * @param balance
     */
    public void rechargeMoney(String username,Integer balance);

    /**
     * 添加充值记录
     * @param record
     */
    public void addRechargeRecord(RechargeRecord record);

    /**
     * 保存用户信息
     */
    public void saveUserInfo(String username, String nickname, String sex, Date date);
}
