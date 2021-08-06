package com.system.service;

import com.system.dao.MessageDao;
import com.system.domain.Message;
import com.system.web.webSocket.WebSocketDemo;
import jnr.ffi.annotations.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("webSocketMessageService")
public class WSMessageService {
    private Logger logger = LoggerFactory.getLogger(WSMessageService.class);
    //声明websocket连接类
    private WebSocketDemo websocketDemo = new WebSocketDemo();

    @Autowired
    private MessageDao messageDao;


    /**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
    public Boolean sendToAllTerminal(String userA,String userB,String message){
        logger.info("向用户{}的消息：{}",userB,message);
        Message msg = new Message(userA,userB,message,new Date(),1);
        if(websocketDemo.getUserStatus(userB)){
            System.out.println("1");
            websocketDemo.sendMessageToUser(userB,message);
            messageDao.saveMessage(msg);
            return true;
        }else{
            System.out.println("2");
            messageDao.saveMessage(msg);
            return false;
        }
    }

    /**
     * 查询用户状态(是否在线)
     * @param userB
     * @return
     */
    public Boolean getUserStatus(String userB){
        if (websocketDemo.getUserStatus(userB)){
            return true;
        }else {
            return false;
        }
    }

    public Integer getUnreadMessageCount(String username) {
        Integer count = messageDao.getUnreadMessageCount(username);
        return count;
    }

    public List<Message> getOldMessages(String username,String from) {
        List<Message> messages = messageDao.getOldMessages(username,from);
        return messages;
    }
}