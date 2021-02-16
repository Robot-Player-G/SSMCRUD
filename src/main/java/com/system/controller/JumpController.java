package com.system.controller;

import com.system.eth.MyEth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/jump")
public class JumpController {

    //username传值
    private String usernameFlag = null;
    //消息发送方
    private String userAFlag = null;
    //消息接收方
    private String userBFlag = null;

    private String taskIdFlag = null;
    @Autowired
    private MyEth myEth;
    /**
     * 跳转到新手教程
     * @return
     */
    @RequestMapping(value = "/jumpToUserManual")
    public String jumpToUserManual(){
        return "userManual";
    }

    /**
     * 跳转到网站简介
     * @return
     */
    @RequestMapping(value = "/jumpToSiteDescription")
    public String jumpToSiteDescription(){
        return "siteDescription";
    }

    @RequestMapping(value = "/backToTaskCenterFocus")
    public ModelAndView backToTaskCenterFocus(String username){
        ModelAndView mv = new ModelAndView();
        if (username==null){
            String backFlag = "taskCenter";
            mv.addObject("user",usernameFlag);
            mv.addObject("backFlag",backFlag);
            mv.setViewName("success");
        }else {
            usernameFlag = username;
        }
        return mv;
    }

    @RequestMapping(value = "/backToHomePageFocus")
    public ModelAndView backToHomePageFocus(String username){
        ModelAndView mv = new ModelAndView();
        if (username==null){
            mv.addObject("user",usernameFlag);
            mv.setViewName("success");
        }else {
            usernameFlag = username;
        }
        return mv;
    }

    @RequestMapping("/gotoChatRoom")
    public ModelAndView gotoChatRoom(String userA,String userB){
        ModelAndView mv = new ModelAndView();
        if(userA == null && userB==null){
            mv.addObject("userA",userAFlag);
            mv.addObject("userB",userBFlag);
            mv.setViewName("chatRoom");
        }else{
            userAFlag = userA;
            userBFlag = userB;
        }
        return mv;
    }

    @RequestMapping("/gotoSubmitTask")
    public ModelAndView gotoSubmitTask(String task_id){
        ModelAndView mv = new ModelAndView();
        if(task_id==null){
            mv.addObject("taskId",taskIdFlag);
            mv.setViewName("submitTask");
        }else {
            taskIdFlag = task_id;
        }
        return mv;
    }

    @RequestMapping("/getHashStr")
    @ResponseBody
    public String getHashStr() throws Exception{

        return myEth.getHash();
    }
}
