package com.system.service;

import com.system.domain.TaskList;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 任务信息业务层
 */
public interface TaskService {
    /**
     * 查询所有任务信息
     * @return
     */
    public List<TaskList> findAllTask(String username);

    /**
     * 查询所有任务总数
     * @param username
     * @return
     */
    public Integer findAllTaskCount(String username);
    /**
     * 查询愿意分享的任务
     * @param username
     * @return
     */
    public List<TaskList> findShareTask(String username);
    /**
     * 按task_id查询任务
     * @param task_id
     * @return
     */
    public TaskList findTaskByTaskId(Integer task_id);
    /**
     * 保存任务，即发布任务
     * 第一阶段
     * task_tbl
     * @param taskList
     */
    public void saveTask(TaskList taskList);

    /**
     * 保存任务，即发布任务
     * 第二阶段
     * taskInfo_tbl
     * @param taskList
     */
    public void saveTaskInfo(TaskList taskList);

    /**
     * 获取交易hash
     * @param username
     * @param task_id
     * @return
     */
    public String getHashByTaskId(String username,String task_id);

    /**
     * 保存pay_hash
     * @param task_id
     * @param hash
     */
    public void saveHash(String task_id,String hash);

    /**
     * 根据task_id更新receiver
     * @param task_id
     * @param receiver
     */
    public void saveReceiver(Integer task_id,String receiver);

    /**
     * 根据接取任务的用户查询任务
     * @param receiver
     * @return
     */
    public List<TaskList> findTaskInfoByReceiver(String receiver);

    /**
     * 根据任务发布者查询任务
     * @param publisher
     * @return
     */
    public List<TaskList> findTaskInfoByPublisher(String publisher);

    /**
     * 用户撤回任务
     * @param task_id
     */
    public void cancelTask(String task_id);

    /**
     * 根据关键字模糊查询
     * @param keyword
     * @return
     */
    public List<TaskList> getTaskInfoByKeyWord(String keyword);

    /**
     * 根据任务id获取任务信息
     * @param task_id
     * @return
     */
    public TaskList getTaskInfoByTaskId(String task_id);

    /**
     * 完成任务(即通过task_id修改任务的完成时间)
     * @param task_id
     * @param finish_time
     */
    public void finishTask(String task_id, Date finish_time);

    /**
     * 支付报酬
     */
    public void updatePaymentStatus(String task_id);
}
