package com.system.controller;


import com.system.exception.LoginException;
import com.system.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 校验异常统一处理
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


}