package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClickController {
    /**
     * 登陆页面跳转
     * @return
     */
    @RequestMapping(value = "login")
    public String userClick1(){
        return "login";
    }
    /**
     * 注册页面跳转
     * @return
     */
    @RequestMapping(value = "register")
    public String userClick2(){
        return "register";
    }
}
