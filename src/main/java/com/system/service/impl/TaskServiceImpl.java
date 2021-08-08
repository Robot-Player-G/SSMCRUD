package com.system.service.impl;

import com.system.dao.TaskDao;
import com.system.domain.DealRecord;
import com.system.domain.TaskList;
import com.system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Integer findAllTaskCount(String username) {
        return taskDao.findAllTaskCount(username);
    }

    /**
     * 查询愿意分享的任务案例
     * @param username
     * @return
     */
    @Override
    public List<TaskList> findShareTask(String username) {
        return taskDao.findShareTask(username);
    }

    /**
     * 通过task_id查询任务
     * @param task_id
     * @return
     */
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

    @Override
    public String getHashByTaskId(String username, String task_id) {
        return taskDao.getHashByTaskId(username,task_id);
    }

    /**
     * 保存pay_hash
     * @param hash
     */
    public void saveHash(String task_id,String hash){
        TaskList list = taskDao.getTaskInfoByTaskId(task_id);
        DealRecord record = new DealRecord();
        record.setTask_id(task_id);
        record.setTime(list.getFinish_time());
        record.setPublisher(list.getPublisher());
        record.setReceiver(list.getReceiver());
        record.setMoney(list.getPay());
        record.setHash(hash);
        taskDao.saveHash(record);
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
     * 用户撤回任务
     * @param task_id
     */
    @Override
    public void cancelTask(String task_id) {
        taskDao.cancelTask(task_id);
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

    @Override
    public TaskList getTaskInfoByTaskId(String task_id) {
        return taskDao.getTaskInfoByTaskId(task_id);
    }

    /**
     * 完成任务(即通过task_id修改任务的完成时间)
     * @param task_id
     * @param finish_time
     */
    @Override
    public void finishTask(String task_id, Date finish_time) {
        
        taskDao.setFinishTimeByTaskId(task_id,finish_time);
    }

    /**
     * 支付报酬
     * @param task_id
     */
    @Override
    public void updatePaymentStatus(String task_id) {
        TaskList list = taskDao.getTaskInfoByTaskId(task_id);
        Integer RB = taskDao.getBalanceByUsername(list.getReceiver());
        Integer PB = taskDao.getBalanceByUsername(list.getPublisher());
        RB = RB + list.getPay();
        PB = PB - list.getPay();
        taskDao.increaseBalance(list.getReceiver(),RB);
        taskDao.spendMoney(list.getPublisher(),PB);
        taskDao.updatePaymentStatus(task_id);
    }
}
