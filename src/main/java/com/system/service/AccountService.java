package com.system.service;

import com.system.domain.Account;

import java.util.List;
/**
 * 账户的service接口
 */
public interface AccountService {
    //查询用户信息
    public List<Account> findAll();
    //保存用户信息
    public void saveAccount(Account account);
}
