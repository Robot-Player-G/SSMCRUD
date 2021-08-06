package com.system.controller;

import com.system.domain.Message;
import com.system.service.WSMessageService;
import jnr.ffi.annotations.In;
import org.aspectj.bridge.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private WSMessageService wsMessageService;

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    //请求入口
    @RequestMapping(value="/sendMessage",method= RequestMethod.POST)
    @ResponseBody
    public String TestWS(@RequestParam("userA") String userA,
                         @RequestParam(value="userB") String userB,
                         @RequestParam(value="message") String message){
        logger.info("收到发送请求，{}向用户{}的消息：{}",userB,message);
        if(wsMessageService.sendToAllTerminal(userA,userB, message)){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 获取用户状态,是否在线
     * @param userB
     * @return
     */
    @RequestMapping("/getUserStatus")
    @ResponseBody
    public String getUserStatus(@RequestParam("userB")String userB){
        if (wsMessageService.getUserStatus(userB)){
            return "true";
        }else {
            return "false";
        }
    }

    /**
     * 获取未读消息数量
     * @return
     */
    @RequestMapping("/getUnreadMessageCount")
    @ResponseBody
    public Integer getUnreadMessageCount(String username){
        Integer count =wsMessageService.getUnreadMessageCount(username);
        return count;
    }

    /**
     * 获取未读消息数量
     * @return
     */
    @RequestMapping("/getOldMessages")
    @ResponseBody
    public List<Message> getOldMessages(String username,String from){
        List<Message> messages=wsMessageService.getOldMessages(username,from);
        return messages;
    }
}