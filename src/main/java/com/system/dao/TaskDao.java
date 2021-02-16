package com.system.dao;

import com.system.domain.TaskList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务信息持久层
 */
@Repository
public interface TaskDao {
    /**
     * 查询所有任务的task_title,publisher,task_content,publish_time,pay信息
     */
    @Select("select t.task_id,task_title,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskInfo_tbl as ti where t.task_id=ti.task_id and publisher<>#{username}")
    public List<TaskList> findAllTask(String username);

    /**
     * 按task_id查询任务
     * @param task_id
     * @return
     */
    @Select("select t.task_id,task_title,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskInfo_tbl as ti where t.task_id=#{task_id} and ti.task_id=#{task_id}")
    public TaskList findTaskByTaskId(Integer task_id);
    /**
     * 保存任务，即发布任务
     * 分两个阶段输入数据库
     * 先task_tbl，再taskInfo_tbl
     * @param taskList
     */
    @Select("insert into task_tbl(task_id,task_title) values(#{task_id},#{task_title})")
    public void saveTask(TaskList taskList);
    @Select("insert into taskInfo_tbl(task_id,publisher,task_content,publish_time,pay) values(#{task_id},#{publisher},#{task_content},#{publish_time},#{pay})")
    public void saveTaskInfo(TaskList taskList);

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
    @Select("select t.task_id,task_title,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskInfo_tbl as ti where t.task_id=ti.task_id and receiver=#{receiver}")
    public List<TaskList> findTaskInfoByReceiver(String receiver);
    /**
     * 根据任务发布者查询任务
     * @param publisher
     * @return
     */
    @Select("select t.task_id,task_title,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskInfo_tbl as ti where t.task_id = ti.task_id and publisher = #{publisher}")
    public List<TaskList> findTaskInfoByPublisher(@Param("publisher") String publisher);
    @Select("select t.task_id,task_title,publisher,task_content,publish_time,receiver,finish_time,pay from task_tbl as t inner join taskInfo_tbl as ti where t.task_id = ti.task_id and task_title like #{title}")
    public List<TaskList> getTaskInfoByKeyWord(@Param("title") String keyword);
}
