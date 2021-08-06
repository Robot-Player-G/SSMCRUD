package com.system.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务信息实体类
 */
public class TaskList implements Serializable {
    //任务id，唯一标识
    private Integer task_id;
    //任务标题
    private String task_title;
    //支付状态
    private Integer status;
    private Integer check_status;
    private String check_result;
    //任务发布者
    private String publisher;
    //任务内容
    private String task_content;
    //任务发布时间
    private Date publish_time;
    //任务接取者，没人接即为空
    private String receiver;
    //任务完成时间，如果未完成即为空
    private Date finish_time;
    //任务报酬
    private Integer pay;

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    public String getCheck_result() {
        return check_result;
    }

    public void setCheck_result(String check_result) {
        this.check_result = check_result;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTask_content() {
        return task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "task_id=" + task_id +
                ", task_title='" + task_title + '\'' +
                ", status=" + status +
                ", check_status=" + check_status +
                ", check_result='" + check_result + '\'' +
                ", publisher='" + publisher + '\'' +
                ", task_content='" + task_content + '\'' +
                ", publish_time=" + publish_time +
                ", receiver='" + receiver + '\'' +
                ", finish_time=" + finish_time +
                ", pay=" + pay +
                '}';
    }
}
