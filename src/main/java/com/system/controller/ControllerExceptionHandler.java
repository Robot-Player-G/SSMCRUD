package com.system.controller;


import com.system.exception.LoginException;
import com.system.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 登录注册异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = LoginException.class)
    public ModelAndView loginExceptionHandler(LoginException e) {
        ModelAndView mv = new ModelAndView();
        LOG.warn("出现异常：{}", e.getMessage());
        mv.addObject("msg",e.getMessage());
        mv.addObject("code",0);
        mv.setViewName("error");
        return mv;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public CommonResp globalExceptionHandler(Exception e){
        LOG.warn("全局的异常处理器接收到异常:{}",e);
        CommonResp commonResp = new CommonResp();
        commonResp.setMessage(getStackTrace(e));
        commonResp.setSuccess(false);
        return commonResp;
    }

    public static String getStackTrace(Throwable e) {
        ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        e.printStackTrace(new java.io.PrintWriter(buf, true));
        String expMessage = buf.toString();
        try {
            buf.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return expMessage;
    }
}