package com.system.service.impl;

import com.system.dao.AdminDao;
import com.system.domain.*;
import com.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Boolean checkAdmin(Admin admin) {
        Admin SqlAdmin = adminDao.findAdminByName(admin.getAdmin());
        if (SqlAdmin == null){
            return false;
        }else {
            if (SqlAdmin.getAdmin().equals(admin.getAdmin())&&SqlAdmin.getAdminpswd().equals(admin.getAdminpswd())) {
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public Integer getAllUserCount() {
        return adminDao.getAllUserCount();
    }

    @Override
    public List<User> getAllUser() {
        return adminDao.getAllUser();
    }

    @Override
    public Integer getRechargeRecordCount() {
        return adminDao.getRechargeRecordCount();
    }

    @Override
    public List<RechargeRecord> getRechargeRecord() {
        return adminDao.getRechargeRecord();
    }

    @Override
    public Boolean deleteUserByName(String username) {
        adminDao.deleteUserByName(username);
        return true;
    }

    @Override
    public Boolean deleteRechargeById(String recharge_id) {
        adminDao.deleteRechargeById(recharge_id);
        return true;
    }

    @Override
    public Integer getAllTaskCount() {
        return adminDao.getAllTaskCount();
    }

    @Override
    public List<TaskList> getAllTask() {
        return adminDao.getAllTask();
    }

    @Override
    public Integer getDealRecordCount() {
        return adminDao.getDealRecordCount();
    }

    @Override
    public List<DealRecord> getDealRecord() {
        return adminDao.getDealRecord();
    }

    @Override
    public void checkTask(String task_id,String reason) {
        Integer status;
        if (reason==""){
            status = 1;
        }else {
            status = 2;
        }
        adminDao.checkTask(task_id,status,reason);
    }

    @Override
    public Boolean deleteDealById(String deal_id) {
        adminDao.deleteDealById(deal_id);
        return true;
    }

    @Override
    public List<Message> getAdminMessage() {
        return adminDao.getAdminMessage();
    }

    @Override
    public void adminSendMessage(String to, String content) {
        Message message = new Message();
        message.setFrom("admin");
        message.setTo(to);
        message.setContent(content);
        message.setTime(new Date());
        message.setType(0);
        adminDao.adminSendMessage(message);
    }

}
