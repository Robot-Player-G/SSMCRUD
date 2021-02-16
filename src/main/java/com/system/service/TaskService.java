package com.system.service;

import com.system.domain.TaskList;
import org.apache.ibatis.annotations.Param;

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
     * 根据关键字模糊查询
     * @param keyword
     * @return
     */
    public List<TaskList> getTaskInfoByKeyWord(String keyword);
}
