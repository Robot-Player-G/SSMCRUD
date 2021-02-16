package com.system.service;

import com.system.web.webSocket.WebSocketDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("webSocketMessageService")
public class WSMessageService {
    private Logger logger = LoggerFactory.getLogger(WSMessageService.class);
    //声明websocket连接类
    private WebSocketDemo websocketDemo = new WebSocketDemo();

    /**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
    public Boolean sendToAllTerminal(String userId,String message){
        logger.debug("向用户{}的消息：{}",userId,message);
        if(websocketDemo.sendMessageToUser(userId,message)){
            return true;
        }else{
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
}