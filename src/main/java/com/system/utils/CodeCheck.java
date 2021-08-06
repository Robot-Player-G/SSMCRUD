package com.system.utils;

import org.springframework.stereotype.Component;

/**
 * 校验验证码
 */
@Component
public class CodeCheck {
    private String flag;
    public String codeCheck(String randomCode,String userCode){
        if(randomCode.equalsIgnoreCase(userCode)){
            return flag="success";
        }else {
            return flag="false";
        }
    }
}
