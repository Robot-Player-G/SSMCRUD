package com.system.controller;

import com.system.service.WSMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private WSMessageService wsMessageService;

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    //请求入口
    @RequestMapping(value="/TestWS",method= RequestMethod.GET)
    @ResponseBody
    public String TestWS(@RequestParam(value="userId") String userId,
                         @RequestParam(value="message") String message){
        logger.warn("收到发送请求，向用户{}的消息：{}",userId,message);
        if(wsMessageService.sendToAllTerminal(userId, message)){
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
}