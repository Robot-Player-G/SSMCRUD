package com.system.domain;

import java.io.Serializable;
import java.util.Date;

public class RechargeRecord implements Serializable {
    private Long recharge_id;
    private String user;
    private Date time;
    private Integer sum;

    public RechargeRecord() {
    }

    public RechargeRecord(Long recharge_id, String user, Date time, Integer sum) {
        this.recharge_id = recharge_id;
        this.user = user;
        this.time = time;
        this.sum = sum;
    }

    public Long getRecharge_id() {
        return recharge_id;
    }

    public void setRecharge_id(Long recharge_id) {
        this.recharge_id = recharge_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "RechargeRecord{" +
                "recharge_id=" + recharge_id +
                ", user='" + user + '\'' +
                ", time=" + time +
                ", sum=" + sum +
                '}';
    }
}
