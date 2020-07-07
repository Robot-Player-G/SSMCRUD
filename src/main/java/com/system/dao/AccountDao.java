package com.system.dao;

import com.system.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的dao接口
 */
@Repository
public interface AccountDao {
    //查询用户信息
    @Select("select *from account")
    public List<Account> findAll();
    //保存用户信息
    @Insert("insert into account (name,money) values (#{name},#{money})")
    public void saveAccount(Account account);
}
