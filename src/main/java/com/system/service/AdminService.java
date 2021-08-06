package com.system.service;

import com.system.domain.*;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminService {
    /**
     * 校验管理员账号密码
     * @param admin
     * @return
     */
    public Boolean checkAdmin(Admin admin);

    /**
     * 获取所有用户信息条数
     * @return
     */
    public Integer getAllUserCount();

    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> getAllUser();

    /**
     * 管理员获取用户充值记录总条数
     * @return
     */
    public Integer getRechargeRecordCount();

    /**
     * 管理员获取用户充值记录
     * @return
     */
    public List<RechargeRecord> getRechargeRecord();

    /**
     * 通过username删除用户
     * @return
     */
    public Boolean deleteUserByName(String username);

    /**
     * 管理员通过充值记录id删除充值记录
     */
    public Boolean deleteRechargeById(String recharge_id);

    /**
     * 获取所有任务信息条数
     * @return
     */
    public Integer getAllTaskCount();

    /**
     * 获取所有任务信息
     * @return
     */
    public List<TaskList> getAllTask();

    /**
     * 管理员获取用户交易记录总条数
     * @return
     */
    public Integer getDealRecordCount();

    /**
     * 管理员获取用户交易记录
     * @return
     */
    public List<DealRecord> getDealRecord();

    /**
     * 审核任务
     * @param task_id
     * @return
     */
    public void checkTask(String task_id,String reason);

    /**
     * 管理员通过交易记录id删除交易记录
     */
    public Boolean deleteDealById(String deal_id);

    /**
     * 管理员获取所有用户的留言
     * @param
     * @return
     */
    public List<Message> getAdminMessage();

    /**
     * 管理员发送留言
     * @param to
     * @param content
     */
    public void adminSendMessage(String to ,String content);
}
