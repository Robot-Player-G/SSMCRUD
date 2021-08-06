package com.system.controller;

import com.system.domain.TaskList;
import com.system.domain.User;
import com.system.service.AdminService;
import com.system.service.TaskService;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "/jump")
public class JumpController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册跳转页面
     * @return
     */
    @RequestMapping(value = "/toUserRegister")
    public String userRegisterTo() throws Exception {
        return "register";
    }

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
//        if (username==null){
            String backFlag = "taskCenter";
            User userInfo = userService.findOneByName(username);
            mv.addObject("user",userInfo);
            mv.addObject("backFlag",backFlag);
//            usernameFlag = null;
            mv.setViewName("success");
//        }else {
//            usernameFlag = username;
//        }
        return mv;
    }


    @RequestMapping(value = "/backToHomePageFocus")
    public ModelAndView backToHomePageFocus(String username){
        ModelAndView mv = new ModelAndView();
//        if (username==null){
        User userInfo = userService.findOneByName(username);
        mv.addObject("user",userInfo);
        mv.setViewName("success");
//            usernameFlag = null;
//        }else {
//            usernameFlag = username;
//        }
        return mv;
    }

    /**
     * 前往聊天室
     * @param userA
     * @param userB
     * @return
     */
    @RequestMapping("/gotoChatRoom")
    public ModelAndView gotoChatRoom(@RequestParam("userA") String userA, @RequestParam("userB") String userB){
        ModelAndView mv = new ModelAndView();
//        if(userA == null && userB==null){
        mv.addObject("userA",userA);
        mv.addObject("userB",userB);
        mv.setViewName("chatRoom");
//        }else{
//            userAFlag = userA;
//            userBFlag = userB;
//        }
        return mv;
    }

    /**
     * 前往提交方案页面
     * @param task_id
     * @return
     */
    @RequestMapping("/gotoSubmitTask")
    public ModelAndView gotoSubmitTask(String task_id){
        ModelAndView mv = new ModelAndView();
//        if(task_id==null){
            mv.addObject("taskId",task_id);
            mv.setViewName("submitTask");
//            taskIdFlag = null;
//        }else {
//            taskIdFlag = task_id;
//        }
        return mv;
    }

    /**
     * 任务完成回到用户中心
     * @param hash
     * @return
     */
    @RequestMapping("/backToUserCenter")
    public ModelAndView backToUserCenter(String hash){
        ModelAndView mv = new ModelAndView();
//        if(hash==null){
            mv.addObject("hash",hash);
            mv.setViewName("userCenter");
//            hashFlag = null ;
//        }else {
//            hashFlag = hash;
//        }
        return mv;
    }

    /**
     *跳转到管理员后台首页
     * @return
     */
    @RequestMapping("/adminHomePage")
    public String toAdminHomePage(){
        return "adminHomePage";
    }

    /**
     * 跳转到管理员管理用户页面
     * @return
     */
    @RequestMapping("/adminUserManagement")
    public String toAdminUserManagement(){
        return "adminUserManagement";
    }

    /**
     * 跳转到管理员管理任务页面
     * @return
     */
    @RequestMapping("/adminTaskManagement")
    public String toAdminTaskManagement(){
        return "adminTaskManagement";
    }

    /**
     * 跳转到管理员留言页面
     * @return
     */
    @RequestMapping("/adminMessage")
    public String toAdminMessage(){
        return "adminMessage";
    }

    /**
     * 跳转到admin错误界面
     * @return
     */
    @RequestMapping("/adminError")
    public String toAdminError(){
        return "adminError";
    }

    @RequestMapping("/dataNullPage")
    public String dataNullPage(){
        return "dataNull";
    }

    /**
     * 管理员退出
     * @return
     */
    @RequestMapping("/adminSignOut")
    public String adminSignOut(){
        return "redirect:/";
    }
}
