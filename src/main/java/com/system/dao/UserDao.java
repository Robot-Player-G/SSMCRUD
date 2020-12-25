package com.system.dao;

import com.system.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
     * @param user
     */
    @Select("insert into user_tbl(username,password) values(#{username},#{password})")
    public void saveUser(User user);

    /**
     * 通过完整username查询
     * @param username
     * @return
     */
    @Select("select u.username,`password`,nickname,sex,birthday from user_tbl as u INNER JOIN userinfo_tbl as ui where u.username=#{username} AND ui.username=#{username};")
    public User findByUserName(String username);

    /**
     * 根据username修改个人信息
     * @param user
     */
    @Select("update userinfo_tbl set nickname=#{nickname},sex=#{sex},birthday=#{birthday} where username=#{username}")
    public void modifyUserInfo(User user);
}
