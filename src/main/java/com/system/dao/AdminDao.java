package com.system.dao;

import com.system.domain.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {
    /**
     * 通过管理员名称查询管理员实体信息
     * @param admin
     * @return
     */
    @Select("select admin,adminpswd from admin_tbl where admin=#{admin}")
    public Admin findAdminByName(String admin);

    /**
     * 管理员获取所有用户信息条数
     * @return
     */
    @Select("select COUNT(*) from user_tbl as u inner join userinfo_tbl as ui where u.username=ui.username")
    public Integer getAllUserCount();

    /**
     * 管理员获取所有用户信息
     * @return
     */
    @Select("select u.username,password,nickname,sex,birthday,balance from user_tbl as u inner join userinfo_tbl as ui where u.username=ui.username")
    public List<User> getAllUser();

    /**
     * 管理员获取用户充值记录总条数
     * @return
     */
    @Select("select COUNT(*) from recharge_record")
    public Integer getRechargeRecordCount();

    /**
     * 管理员获取用户充值记录
     * @return
     */
    @Select("select *from recharge_record")
    public List<RechargeRecord> getRechargeRecord();

    /**
     * 管理员通过username删除用户
     */
    @Select("delete from user_tbl where username=#{username}")
    public void deleteUserByName(String username);

    /**
     * 管理员通过充值记录id删除充值记录
     */
    @Select("delete from recharge_record where recharge_id=#{recharge_id}")
    public void deleteRechargeById(String recharge_id);

    /**
     * 管理员获取所有任务信息条数
     * @return
     */
    @Select("select COUNT(*) from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id")
    public Integer getAllTaskCount();

    /**
     * 管理员获取所有任务信息
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id and t.check_status=0")
    public List<TaskList> getAllTask();

    /**
     * 管理员获取用户交易记录总条数
     * @return
     */
    @Select("select COUNT(*) from deal_record")
    public Integer getDealRecordCount();

    /**
     * 管理员获取用户交易记录
     * @return
     */
    @Select("select *from deal_record")
    public List<DealRecord> getDealRecord();

    /**
     * 管理员审核任务
     */
    @Select("update task_tbl set check_status=#{status},check_result=#{reason} where task_id=#{task_id}")
    public void checkTask(@Param("task_id") String task_id, @Param("status") Integer status, @Param("reason") String reason);

    /**
     * 管理员通过交易记录id删除交易记录
     */
    @Select("delete from deal_record where deal_id=#{deal_id}")
    public void deleteDealById(String deal_id);

    /**
     * 管理员获取所有用户的留言
     * @return
     */
    @Select("select *from message where `to`='admin'")
    public List<Message> getAdminMessage();

    /**
     * 管理员发送留言
     * @param message
     */
    @Select("insert into message(`from`,`to`,content,time,type) values(#{from},#{to},#{content},#{time},#{type})")
    public void adminSendMessage(Message message);

}
