package com.system.domain;

import java.io.Serializable;
import java.util.Date;

public class DealRecord implements Serializable {
    private Integer deal_id;
    private String task_id;
    private Date time;
    private String publisher;
    private String receiver;
    private Integer money;
    private String hash;

    public Integer getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(Integer deal_id) {
        this.deal_id = deal_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    @Override
    public String toString() {
        return "DealRecord{" +
                "deal_id=" + deal_id +
                ", task_id='" + task_id + '\'' +
                ", time=" + time +
                ", publisher='" + publisher + '\'' +
                ", receiver='" + receiver + '\'' +
                ", money=" + money +
                ", hash='" + hash + '\'' +
                '}';
    }
}
