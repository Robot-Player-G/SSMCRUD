package com.system.service;

import com.system.domain.TaskList;

import java.util.List;

/**
 * 任务信息业务层
 */
public interface TaskService {
    /**
     * 查询所有任务信息
     * @return
     */
    public List<TaskList> findAllTask();
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
}
