package com.system.dao;

import com.system.domain.Admin;
import com.system.domain.Message;
import com.system.domain.RechargeRecord;
import com.system.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.web3j.abi.datatypes.Int;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao {
    /**
     * 查询所有用户账号密码
     * @return
     */
    @Select("select *from user_tbl")
    public List<User> findAll();

    /**
     * 保存用户账号密码
     * @param username
     * @param password
     */
    @Select("insert into user_tbl(username,password) values(#{username},#{password})")
    public void saveUser(@Param("username")String username,@Param("password")String password);

    /**
     * 通过username查询完整用户信息
     * @param username
     * @return
     */
    @Select("select u.username,`password`,nickname,sex,birthday,balance from user_tbl as u INNER JOIN userinfo_tbl as ui where u.username=#{username} AND ui.username=#{username};")
    public User findUserInfoByName(String username);

    /**
     * 通过username查询用户名和密码
     * @param username
     * @return
     */
    @Select("select username,`password` from user_tbl where username = #{username}")
    public User findUserByName(String username);

    /**
     * 根据username修改个人信息
     * @param user
     */
    @Select("update userinfo_tbl set nickname=#{nickname},sex=#{sex},birthday=#{birthday} where username=#{username}")
    public void modifyUserInfo(User user);

    /**
     * 根据username获取管理员的留言
     * @param username
     * @return
     */
    @Select("select *from message where `to`=#{username} and type=0")
    public List<Message> getAMessageByUsername(String username);

    /**
     * 用户获取留言
     * @param userA
     * @param userB
     * @return
     */
    @Select("select *from message where `from`=#{userB} and `to`=#{userA}")
    public List<Message> getMessage(@Param("userA") String userA,@Param("userB") String userB);

    /**
     * 用户给用户发送留言
     * @param message
     */
    @Select("insert into message(`from`,`to`,content,time,type) values(#{from},#{to},#{content},#{time},#{type})")
    public void leaveMessage(Message message);
    /**
     * 用户给管理员留言
     * @param m
     */
    @Select("insert into message(`from`,`to`,content,time,type) values(#{from},#{to},#{content},#{time},#{type})")
    public void sendMessageToAdmin(Message m);

    /**
     * 检测用户是否存在
     * @param username
     * @return
     */
    @Select("select *from user_tbl where username=#{username}")
    public User isExist(String username);
    /**
     * 通过username获取余额
     * @param username
     * @return
     */
    @Select("select balance from userinfo_tbl where username=#{username}")
    public Integer getBalanceByUsername(String username);
    /**
     * 通过username充值余额
     * @param username
     * @param balance
     */
    @Select("update userinfo_tbl set balance=#{balance} where username=#{username}")
    public void addBalanceByUsername(@Param("username") String username,@Param("balance") Integer balance);
    /**
     * 添加充值记录
     * @param record
     */
    @Select("insert into recharge_record(recharge_id,user,time,sum) values(#{recharge_id},#{user},#{time},#{sum})")
    public void addRechargeRecord(RechargeRecord record);

    /**
     * 保存用户信息
     */
    @Select("insert into userinfo_tbl(username,nickname,sex,birthday,balance) values(#{username},#{nickname},#{sex},#{birthday},#{balance})")
    public void saveUserInfo(@Param("username") String username, @Param("nickname") String nickname, @Param("sex") String sex, @Param("birthday") Date birthday, @Param("balance")Integer balance);
}
