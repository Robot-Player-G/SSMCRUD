package com.system.dao;

import com.system.domain.DealRecord;
import com.system.domain.TaskList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 任务信息持久层
 */
@Repository
public interface TaskDao {
    /**
     * 查询所有任务的task_title,publisher,task_content,publish_time,pay信息
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id and publisher<>#{username} and check_status=1")
    public List<TaskList> findAllTask(String username);

    @Select("select COUNT(*) from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id and publisher<>#{username} and check_status=1")
    public Integer findAllTaskCount(String username);
    /**
     * 查询愿意分享的任务案例
     * @param username
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id and publisher<>#{username} and t.isshare=1")
    public List<TaskList> findShareTask(String username);
    /**
     * 按task_id查询任务
     * @param task_id
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=#{task_id} and ti.task_id=#{task_id}")
    public TaskList findTaskByTaskId(Integer task_id);
    /**
     * 保存任务，即发布任务
     * 分两个阶段输入数据库
     * 先task_tbl，再taskInfo_tbl
     * @param taskList
     */
    @Select("insert into task_tbl(task_id,task_title) values(#{task_id},#{task_title})")
    public void saveTask(TaskList taskList);
    @Select("insert into taskinfo_tbl(task_id,publisher,task_content,publish_time,pay) values(#{task_id},#{publisher},#{task_content},#{publish_time},#{pay})")
    public void saveTaskInfo(TaskList taskList);

    /**
     * 删除任务
     * @param task_id
     */
    @Select("delete from task_tbl where task_id=#{task_id}")
    public void cancelTask(String task_id);

    /**
     * 通过username获取余额
     * @param username
     */
    @Select("select balance from userinfo_tbl where username=#{username}")
    public Integer getBalanceByUsername(String username);

    @Select("insert into deal_record(task_id,time,publisher,receiver,money,hash) values(#{record.task_id},#{record.time},#{record.publisher},#{record.receiver},#{record.money},#{record.hash})")
    public void saveHash(@Param("record") DealRecord record);

    /**
     * 根据task_id更新receiver
     * @param task_id
     * @param receiver
     */
    @Select("update taskinfo_tbl set receiver=#{receiver} where task_id=#{task_id}")
    public void saveReceiver(@Param("receiver") String receiver, @Param("task_id") Integer task_id);
    /**
     * 根据任务接收者查询任务
     * @param receiver
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id=ti.task_id and receiver=#{receiver}")
    public List<TaskList> findTaskInfoByReceiver(String receiver);
    /**
     * 根据任务发布者查询任务
     * @param publisher
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id = ti.task_id and publisher = #{publisher}")
    public List<TaskList> findTaskInfoByPublisher(@Param("publisher") String publisher);

    /**
     * 根据关键字查询任务信息
     * @param keyword
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id = ti.task_id and task_title like #{title}")
    public List<TaskList> getTaskInfoByKeyWord(@Param("title") String keyword);

    /**
     * 根据任务id获取任务信息
     * @param task_id
     * @return
     */
    @Select("select t.task_id,task_title,status,check_status,check_result,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskinfo_tbl as ti where t.task_id = ti.task_id and t.task_id=#{task_id}")
    public TaskList getTaskInfoByTaskId(String task_id);

    /**
     * 通过task_id修改完成时间
     * @param task_id
     * @param finish_time
     */
    @Select("update taskinfo_tbl set finish_time=#{finish_time} where task_id = #{task_id}")
    public void setFinishTimeByTaskId(@Param("task_id") String task_id, @Param("finish_time") Date finish_time);

    /**
     * 获取交易hash
     * @param username
     * @param task_id
     * @return
     */
    @Select("select hash from deal_record where publisher=#{username} and task_id=#{task_id} ")
    public String getHashByTaskId(@Param("username") String username,@Param("task_id") String task_id);

    /**
     * 修改支付状态
     * @param task_id
     */
    @Select("update task_tbl set status=1 where task_id=#{task_id}")
    public void updatePaymentStatus(String task_id);

    /**
     * 接收者增加余额
     * @param username
     * @param balance
     */
    @Select("update userinfo_tbl set balance=#{balance} where username=#{username}")
    public void increaseBalance(@Param("username") String username,@Param("balance") Integer balance);

    /**
     * 花费余额
     * @param username
     */
    @Select("update userinfo_tbl set balance=#{balance} where username=#{username}")
    public void spendMoney(@Param("username") String username,@Param("balance") Integer balance);
}
