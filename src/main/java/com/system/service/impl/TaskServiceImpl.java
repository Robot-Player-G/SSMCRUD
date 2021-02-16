package com.system.service.impl;

import com.system.dao.TaskDao;
import com.system.domain.TaskList;
import com.system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务信息业务层实现类
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    /**
     * 查询所有任务信息
     * @return
     */
    @Override
    public List<TaskList> findAllTask(String username) {
        return taskDao.findAllTask(username);
    }

    @Override
    public TaskList findTaskByTaskId(Integer task_id) {
        return taskDao.findTaskByTaskId(task_id);
    }

    /**
     * 保存任务第一阶段，task_tbl
     * @param taskList
     */
    @Override
    public void saveTask(TaskList taskList) {
        taskDao.saveTask(taskList);
    }

    /**
     * 保存任务第二阶段,taskInfo_tbl
     * @param taskList
     */
    @Override
    public void saveTaskInfo(TaskList taskList) {
        taskDao.saveTaskInfo(taskList);
    }

    /**
     * 根据task_id更新receiver
     * @param task_id
     * @param receiver
     */
    @Override
    public void saveReceiver(Integer task_id,String receiver) {
        taskDao.saveReceiver(receiver,task_id);
    }

    /**
     * 根据任务接受者查询任务
     * @param receiver
     * @return
     */
    @Override
    public List<TaskList> findTaskInfoByReceiver(String receiver) {
        return taskDao.findTaskInfoByReceiver(receiver);
    }

    /**
     * 根据任务发布者查询任务
     * @param publisher
     * @return
     */
    @Override
    public List<TaskList> findTaskInfoByPublisher(String publisher) {
        return taskDao.findTaskInfoByPublisher(publisher);
    }

    /**
     * 根据关键字模糊查询
     * @param keyword
     * @return
     */
    @Override
    public List<TaskList> getTaskInfoByKeyWord(String keyword) {
        return taskDao.getTaskInfoByKeyWord(keyword);
    }
}
